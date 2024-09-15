package tech.indus340.complexa;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.indus340.complexa.chatbot.Assistant;
import tech.indus340.complexa.service.*;
import tech.indus340.complexa.utils.WavPlayer;

import java.io.File;

@Service
public class AudioProcessorThread {

    private final AudioCaptureService audioCaptureService;
    private final NoiseDetectorService noiseDetectorService;
    private final TranscriptionService transcriptionService;
    private final AudioRecorderService audioRecorderService;
    private final WavPlayer wavPlayer;
    private final Assistant assistant;
    private final Text2SpeechService text2SpeechService;
    private long timeSinceLastResponse;

    public AudioProcessorThread(AudioCaptureService audioCaptureService, NoiseDetectorService noiseDetectorService, AudioRecorderService audioRecorderService, TranscriptionService transcriptionService, WavPlayer wavPlayer, Assistant assistant, Text2SpeechService text2SpeechService) {
        this.audioCaptureService = audioCaptureService;
        this.noiseDetectorService = noiseDetectorService;
        this.transcriptionService = transcriptionService;
        this.audioRecorderService = audioRecorderService;
        this.wavPlayer = wavPlayer;
        this.assistant = assistant;
        this.text2SpeechService = text2SpeechService;
    }

    @Scheduled(fixedRate = 2000) // Run every 2 seconds to allow time for recording and processing
    public void processAudio() {
        System.out.println("scanning");
        File audioFile = audioCaptureService.captureAudio();
        if (noiseDetectorService.detectNoise(audioFile)) {
            String transscribed = transcriptionService.transcribe(audioFile);
            boolean timeElapsedTillLastResponse = System.currentTimeMillis() < timeSinceLastResponse + 10000;
            if (transscribed.toLowerCase().contains("omplex") || timeElapsedTillLastResponse) {
                File instructionsWav = null;
                if (!timeElapsedTillLastResponse) {
                    wavPlayer.playAcc();
                    System.out.println("recording started");
                    instructionsWav = audioRecorderService.recordAudio();
                    System.out.println("recording stopped");
                } else {
                    instructionsWav = audioFile;
                }
                String message = transcriptionService.transcribe(instructionsWav);
                String complexaResponse = assistant.chat(message);
                System.out.println(complexaResponse);
                text2SpeechService.tts(complexaResponse);
                wavPlayer.playResponse();
                timeSinceLastResponse = System.currentTimeMillis();
            }
        }
    }
}
