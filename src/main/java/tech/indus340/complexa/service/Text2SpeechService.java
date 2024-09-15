package tech.indus340.complexa.service;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.audio.SpeechRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class Text2SpeechService {

    private static final Logger log = LoggerFactory.getLogger(Text2SpeechService.class);

    private final SimpleOpenAI simpleOpenAI;

    public Text2SpeechService(SimpleOpenAI simpleOpenAI) {
        this.simpleOpenAI = simpleOpenAI;
    }

    public void tts(String text) {
        var speechRequest = SpeechRequest.builder()
                .model("tts-1")
                .input(text)
                .voice(SpeechRequest.Voice.ONYX)
                .responseFormat(SpeechRequest.SpeechResponseFormat.WAV)
                .speed(1.0)
                .build();
        var futureSpeech = simpleOpenAI.audios().speak(speechRequest);
        var speechResponse = futureSpeech.join();
        try {
            var audioFile = new FileOutputStream("response.wav");
            audioFile.write(speechResponse.readAllBytes());
            log.info("file size: " + audioFile.getChannel().size() + " bytes");
            audioFile.close();
        } catch (Exception e) {
            log.error("Error during TTS", e);
        }
    }
}
