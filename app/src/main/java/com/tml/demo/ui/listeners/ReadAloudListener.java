package com.tml.demo.ui.listeners;


import com.tml.demo.utils.TextToSpeechUtils;

/**
 * Listener for the read aloud to keep track of the UI animation and finish
 * Created by 1021354 on 23-10-2015.
 */
public interface ReadAloudListener {
    /**
     * On completion of the text reading
     */
    void onCompleteOneText();

    /**
     * For UI animation
     *
     * @param charSequence
     */
    void putAnimationToUI(CharSequence charSequence, TextToSpeechUtils.TTSState state);

    /**
     * For error
     */
    void onError();
}
