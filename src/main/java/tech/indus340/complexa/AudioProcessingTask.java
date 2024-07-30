package tech.indus340.complexa;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AudioProcessingTask {

    private final AudioCaptureService audioCaptureService;

    public AudioProcessingTask(AudioCaptureService audioCaptureService) {
        this.audioCaptureService = audioCaptureService;
    }

    @Scheduled(fixedRate = 3000) // Run every 2 seconds to allow time for recording and processing
    public void processAudio() {
        File audioFile = audioCaptureService.captureAudio();
        if (audioFile != null) {
            System.out.println("Audio recorded and saved as " + audioFile.getAbsolutePath());
        }
    }
}
