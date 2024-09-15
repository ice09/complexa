package tech.indus340.complexa.service;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.audio.AudioResponseFormat;
import io.github.sashirestela.openai.domain.audio.TranscriptionRequest;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TranscriptionService {

    private final SimpleOpenAI simpleOpenAI;

    public TranscriptionService(SimpleOpenAI simpleOpenAI) {
        this.simpleOpenAI = simpleOpenAI;
    }

    public String transcribe(File wavFile) {
        var audioRequest = TranscriptionRequest.builder()
                .file(wavFile.toPath())
                .model("whisper-1")
                .responseFormat(AudioResponseFormat.VERBOSE_JSON)
                .temperature(0.2)
                .timestampGranularity(TranscriptionRequest.TimestampGranularity.WORD)
                .timestampGranularity(TranscriptionRequest.TimestampGranularity.SEGMENT)
                .build();
        var futureAudio = simpleOpenAI.audios().transcribe(audioRequest);
        var audioResponse = futureAudio.join();
        System.out.println(audioResponse);
        return audioResponse.getText();
    }
}
