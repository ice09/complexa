package tech.indus340.complexa.service;

import io.github.sashirestela.openai.SimpleOpenAI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TranscriptionServiceConfiguration {

    @Value("${openai.key}")
    private String openAIKey;

    @Bean
    public SimpleOpenAI openAiClient() {
        return SimpleOpenAI.builder()
                .apiKey(openAIKey)
                .build();
    }
}
