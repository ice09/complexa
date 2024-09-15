package tech.indus340.complexa.service;

import org.springframework.stereotype.Service;
import tech.indus340.complexa.utils.AudioUtils;

import javax.sound.sampled.*;
import java.io.*;

@Service
public class AudioRecorderService {

    // Use AudioUtils to record audio with the default format and threshold values
    public File recordAudioIntention() {
        AudioFormat format = AudioUtils.getDefaultAudioFormat();
        return AudioUtils.recordAudio(format, AudioUtils.DEFAULT_SILENCE_THRESHOLD, AudioUtils.DEFAULT_SILENCE_THRESHOLD_MS);
    }

    // Use AudioUtils to record audio with the default format and threshold values
    public File recordAudioContinuous() {
        AudioFormat format = AudioUtils.getDefaultAudioFormat();
        return AudioUtils.recordAudio(format, AudioUtils.DEFAULT_SILENCE_THRESHOLD, AudioUtils.DEFAULT_SILENCE_CONTINUOUS_THRESHOLD_MS);
    }
}
