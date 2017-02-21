package com.tml.demo.ui.listeners;

/**
 * Listener for the tts read aloud to keep track audio foucus
 * Created by 677271 on 12/3/2015.
 */
public interface TTSAudioFeedbackListener {
    /**
     * listener to stop tts on audio focus loss
     */
    void stopTTSListener();

    /**
     * listener to start tts on audio focus gain
     */
    void startTTSListener();

    /**
     * listener to stop tts on transient audio focus loss
     */
    void pauseTTSListener();
}
