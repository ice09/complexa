package tech.indus340.complexa.utils;

import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;

@Service
public class WavMerger {

    public File merge(File file1, File file2) {
        File outputWavFile = new File("merged.wav");

        try {
            // Obtain audio input streams from both WAV files
            AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);

            // Check if the audio formats of both files are the same
            AudioFormat format1 = audioStream1.getFormat();
            AudioFormat format2 = audioStream2.getFormat();
            if (!format1.matches(format2)) {
                System.out.println("Audio formats do not match.");
                return null;
            }

            // Concatenate the audio streams
            AudioInputStream appendedStream = 
                new AudioInputStream(
                    new SequenceInputStream(audioStream1, audioStream2),
                    format1, 
                    audioStream1.getFrameLength() + audioStream2.getFrameLength()
                );

            // Write the result to a new WAV file
            AudioSystem.write(appendedStream, AudioFileFormat.Type.WAVE, outputWavFile);

            // Close the streams
            audioStream1.close();
            audioStream2.close();
            appendedStream.close();
            
            System.out.println("WAV files merged successfully.");
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return outputWavFile;
    }
}
