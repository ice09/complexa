package tech.indus340.complexa.utils;

import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@Service
public class WavPlayer {

    public void play(File wavFile) {
        AudioInputStream audioInputStream = null;
        SourceDataLine sourceLine = null;

        try {
            // Obtain an AudioInputStream from the provided file
            audioInputStream = AudioSystem.getAudioInputStream(wavFile);

            // Get the format of the audio file
            AudioFormat audioFormat = audioInputStream.getFormat();

            // Create a data line info object for the SourceDataLine
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            // Obtain a SourceDataLine that matches the audio format
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
            sourceLine.start();

            // Buffer for reading audio data
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];

            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                // Write the read data to the SourceDataLine, which plays it
                sourceLine.write(buffer, 0, bytesRead);
            }

            // Finish playing by draining the SourceDataLine
            sourceLine.drain();
            System.out.println("Playback finished.");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } finally {
            // Ensure the line and stream are closed properly
            if (sourceLine != null) {
                sourceLine.close();
            }
            if (audioInputStream != null) {
                try {
                    audioInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void playAcc() {
        File wavAcc = new File("whatsup.wav");
        play(wavAcc);
    }

    public void playResponse() {
        File resWav = new File("response.wav");
        play(resWav);
    }
}
