package tech.indus340.complexa.service;

import org.springframework.stereotype.Service;
import tech.indus340.complexa.utils.AudioUtils;

import java.io.File;

@Service
public class NoiseDetectorService {

    // Use AudioUtils to check for noise in a file
    public boolean detectNoise(File wavFile) {
        return AudioUtils.hasNoise(wavFile, AudioUtils.DEFAULT_SILENCE_THRESHOLD);
    }
}