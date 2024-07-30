package tech.indus340.complexa;

import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class AudioCaptureService {

    private static final int RECORD_TIME = 3000; // 1 second
    private int counter;
    public File captureAudio() {
        File wavFile = new File("recorded_audio" + counter++ + ".wav");
        AudioFormat format = new AudioFormat(16000, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            System.err.println("Line not supported");
            return null;
        }

        try (TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info)) {
            line.open(format);
            line.start();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            long endTime = System.currentTimeMillis() + RECORD_TIME;

            while (System.currentTimeMillis() < endTime) {
                int bytesRead = line.read(buffer, 0, buffer.length);
                out.write(buffer, 0, bytesRead);
            }

            line.stop();
            line.close();

            byte[] audioData = out.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
            AudioInputStream ais = new AudioInputStream(bais, format, audioData.length / format.getFrameSize());

            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, wavFile);
        } catch (LineUnavailableException | IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return wavFile;
    }
}
