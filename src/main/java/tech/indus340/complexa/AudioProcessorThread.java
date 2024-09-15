package tech.indus340.complexa;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.indus340.complexa.chatbot.Assistant;
import tech.indus340.complexa.service.*;
import tech.indus340.complexa.utils.WavMerger;
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
    private final WavMerger wavMerger;
    private long timeSinceLastResponse;

    public AudioProcessorThread(AudioCaptureService audioCaptureService, NoiseDetectorService noiseDetectorService, AudioRecorderService audioRecorderService, TranscriptionService transcriptionService, WavPlayer wavPlayer, Assistant assistant, Text2SpeechService text2SpeechService, WavMerger wavMerger) {
        this.audioCaptureService = audioCaptureService;
        this.noiseDetectorService = noiseDetectorService;
        this.transcriptionService = transcriptionService;
        this.audioRecorderService = audioRecorderService;
        this.wavPlayer = wavPlayer;
        this.assistant = assistant;
        this.text2SpeechService = text2SpeechService;
        this.wavMerger = wavMerger;
    }

    @Scheduled(fixedRate = 2000) // Run every 2 seconds to allow time for recording and processing
    public void processAudio() {
        System.out.println("scanning");
        File audioFile = audioCaptureService.captureAudio();
        if (noiseDetectorService.detectNoise(audioFile)) {
            boolean continuousDialogueMode = System.currentTimeMillis() < timeSinceLastResponse + 5000;
            File instructionsWav;
            if (!continuousDialogueMode) {
                String transscribed = transcriptionService.transcribe(audioFile);
                if (transscribed.toLowerCase().contains("omplex")) {
                    wavPlayer.playAcc();
                    System.out.println("recording intention started");
                    instructionsWav = audioRecorderService.recordAudioIntention();
                    System.out.println("recording intention stopped");
                } else {
                    return;
                }
            } else {
                System.out.println("recording dialog mode started");
                instructionsWav = audioRecorderService.recordAudioContinuous();
                System.out.println("recording dialog mode stopped");
                instructionsWav = wavMerger.merge(audioFile, instructionsWav);
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
