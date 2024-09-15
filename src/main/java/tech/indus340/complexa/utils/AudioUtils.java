package tech.indus340.complexa.utils;

import javax.sound.sampled.*;
import java.io.*;

public class AudioUtils {

    public static final double DEFAULT_SILENCE_THRESHOLD = 0.02; // Default threshold for detecting noise
    public static final int DEFAULT_BUFFER_SIZE = 1024; // Default buffer size
    public static final long DEFAULT_SILENCE_THRESHOLD_MS = 2000; // Silence threshold duration in milliseconds
    private static long silenceStartTime;

    // Public method for recording audio until a certain duration of silence is detected
    public static File recordAudio(AudioFormat format, double silenceThreshold, long silenceThresholdMs) {
        ByteArrayOutputStream audioOutputStream = new ByteArrayOutputStream();

        try (TargetDataLine microphone = AudioSystem.getTargetDataLine(format)) {
            startMicrophone(microphone, format);

            boolean isRecording = true;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

            while (isRecording) {
                int bytesRead = microphone.read(buffer, 0, buffer.length);
                if (bytesRead > 0) {
                    audioOutputStream.write(buffer, 0, bytesRead);
                    isRecording = processAudioLevel(buffer, bytesRead, format, silenceThreshold, silenceThresholdMs);
                }
            }

            return saveRecordingToFile(audioOutputStream.toByteArray(), format);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            silenceStartTime = 0;
        }
    }

    // Start microphone recording
    private static void startMicrophone(TargetDataLine microphone, AudioFormat format) throws LineUnavailableException {
        microphone.open(format);
        microphone.start();
    }

    // Process audio level and detect silence
    private static boolean processAudioLevel(byte[] buffer, int bytesRead, AudioFormat format, double silenceThreshold, long silenceThresholdMs) {
        double level = calculateRMSLevel(buffer, bytesRead, format);

        if (level < silenceThreshold) {
            if (silenceStartTime == 0) {
                silenceStartTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - silenceStartTime > silenceThresholdMs) {
                return false;  // Silence detected, stop recording
            }
        } else {
            silenceStartTime = 0;  // Reset silence timer if noise is detected
        }

        return true;  // Continue recording
    }

    // Save the recorded audio to a WAV file
    private static File saveRecordingToFile(byte[] audioData, AudioFormat format) throws IOException {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(audioData);
        AudioInputStream audioStream = new AudioInputStream(byteInputStream, format, audioData.length / format.getFrameSize());
        File wavFile = new File("recording_" + System.currentTimeMillis() + ".wav");

        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavFile);
        System.out.println("Recording saved as " + wavFile.getName());

        return wavFile;
    }

    // Public method for detecting noise in an audio file
    public static boolean hasNoise(File wavFile, double silenceThreshold) {
        try (AudioInputStream audioInputStream = getAudioInputStream(wavFile)) {
            AudioFormat format = audioInputStream.getFormat();
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

            return detectNoiseInStream(audioInputStream, buffer, format, silenceThreshold);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return false; // Return false if an exception occurred or no noise detected
    }

    // Helper method to get the AudioInputStream from the file
    private static AudioInputStream getAudioInputStream(File wavFile) throws UnsupportedAudioFileException, IOException {
        return AudioSystem.getAudioInputStream(wavFile);
    }

    // Detect noise in the audio stream by reading the buffer and checking RMS levels
    private static boolean detectNoiseInStream(AudioInputStream audioInputStream, byte[] buffer, AudioFormat format, double silenceThreshold) throws IOException {
        int bytesRead;

        while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
            if (isNoiseDetected(buffer, bytesRead, format, silenceThreshold)) {
                return true; // Noise detected
            }
        }
        return false; // No noise detected
    }

    // Check if the RMS level exceeds the silence threshold, indicating noise
    private static boolean isNoiseDetected(byte[] buffer, int bytesRead, AudioFormat format, double silenceThreshold) {
        double rmsLevel = calculateRMSLevel(buffer, bytesRead, format);
        return rmsLevel > silenceThreshold;
    }

    // Calculate RMS level (remains the same as before)
    public static double calculateRMSLevel(byte[] audioData, int bytesRead, AudioFormat format) {
        long sum = 0;
        int sampleSizeInBytes = format.getSampleSizeInBits() / 8;
        boolean bigEndian = format.isBigEndian();

        for (int i = 0; i < bytesRead; i += sampleSizeInBytes) {
            int value;
            if (sampleSizeInBytes == 2) {
                value = bigEndian
                        ? (audioData[i] << 8) | (audioData[i + 1] & 0xFF)
                        : (audioData[i + 1] << 8) | (audioData[i] & 0xFF);
            } else {
                value = audioData[i];
            }
            sum += value * value;
        }

        double rms = Math.sqrt(sum / (bytesRead / sampleSizeInBytes));
        return rms / (1 << (format.getSampleSizeInBits() - 1));
    }

    // Helper method for generating default audio format
    public static AudioFormat getDefaultAudioFormat() {
        return new AudioFormat(16000, 16, 1, true, true);
    }
}
