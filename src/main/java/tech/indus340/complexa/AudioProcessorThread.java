package tech.indus340.complexa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.indus340.complexa.chatbot.Assistant;
import tech.indus340.complexa.service.*;
import tech.indus340.complexa.utils.WavMerger;
import tech.indus340.complexa.utils.WavPlayer;

import java.io.File;

@Service
public class AudioProcessorThread {

    public static final int CONTINUOUS_DIALOG_THRESHOLD = 7000; // is dependent on schedule rate
    private static final Logger log = LoggerFactory.getLogger(AudioProcessorThread.class);

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

    @Scheduled(fixedRate = AudioCaptureService.RECORD_TIME)
    public void processAudio() {
        log.info("scanning");
        File audioFile = audioCaptureService.captureAudio();
        if (noiseDetectorService.detectNoise(audioFile)) {
            boolean continuousDialogueMode = System.currentTimeMillis() < timeSinceLastResponse + CONTINUOUS_DIALOG_THRESHOLD;
            File instructionsWav;
            if (!continuousDialogueMode) {
                String transscribed = transcriptionService.transcribe(audioFile);
                // take most stable part of word (C/K)omplex(a/er)
                if (transscribed.toLowerCase().contains("omplex")) {
                    wavPlayer.playAcc();
                    log.info("recording intention started");
                    instructionsWav = audioRecorderService.recordAudioIntention();
                    log.info("recording intention stopped");
                } else {
                    return;
                }
            } else {
                log.info("recording dialog mode started");
                instructionsWav = audioRecorderService.recordAudioContinuous();
                log.info("recording dialog mode stopped");
                instructionsWav = wavMerger.merge(audioFile, instructionsWav);
            }
            String message = transcriptionService.transcribe(instructionsWav);
            String complexaResponse = assistant.chat(message);
            log.info("lc4j response: " + complexaResponse);
            text2SpeechService.tts(complexaResponse);
            wavPlayer.playResponse();
            timeSinceLastResponse = System.currentTimeMillis();
        }
    }
}
