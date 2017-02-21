package com.tml.demo.utils;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;

import com.tml.demo.DemoApplication;
import com.tml.demo.R;
import com.tml.demo.compat.ResourceCompat;
import com.tml.demo.ui.listeners.ReadAloudListener;
import com.tml.demo.ui.listeners.TTSAudioFeedbackListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Utilities for text to speech in the application
 * Created by 844642 on 7/13/2015.
 */
public class TextToSpeechUtils {
    public static final int REQ_CODE_SPEECH_INPUT = 100;
    private static final HashMap<String, String> myHashUtteranceId = new HashMap<>();
    public static int languageSupportCheck;
    private static TTSAudioFeedbackListener ttsAudioFeedbackListener;
    /**
     * AudioManager listener to gain audio focus from any other audio source
     */
    static AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                if (ttsAudioFeedbackListener != null) {
                    ttsAudioFeedbackListener.pauseTTSListener();
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback
                if (ttsAudioFeedbackListener != null) {
                    ttsAudioFeedbackListener.startTTSListener();
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Stop playback
                if (ttsAudioFeedbackListener != null) {
                    ttsAudioFeedbackListener.stopTTSListener();
                }
            }
        }
    };
    private static AudioManager audioManager;
    private static TextToSpeech textToSpeechForReadAloud;
    private static String[] splitTextFinal;
    private static int splitIndex = 0;

    /**
     * Closing text to speech
     *
     * @param isPaused
     */
    public static void shutdownTextToSpeech(boolean isPaused) {
        if (textToSpeechForReadAloud != null) {
            textToSpeechForReadAloud.stop();
            textToSpeechForReadAloud.shutdown();
            textToSpeechForReadAloud = null;
            if (!isPaused) {
                audioManager.abandonAudioFocus(afChangeListener);
            }
        }
    }

    /**
     * Read the text aloud
     *
     * @param context
     * @param text
     * @param readAloudListener
     */
    public static void readAloudText(final Context context, final CharSequence text, final ReadAloudListener readAloudListener, final Boolean lastText, TTSAudioFeedbackListener ttsFeedbackListener) {
        splitIndex = 0;
        ttsAudioFeedbackListener = ttsFeedbackListener;
        if (audioManager == null) {
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        textToSpeechForReadAloud = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        readByTTS(text.toString());
                    } else {
                        ttsAudioFeedbackListener = null;
                        readAloudListener.onError();
                    }
                }
            }
        });

        textToSpeechForReadAloud.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                putSelectionTOUI(text, utteranceId, readAloudListener);
            }

            @Override
            public void onDone(String utteranceId) {
                boolean isFinished = doneReadSentence(context, text, lastText, utteranceId, readAloudListener);
                if (!isFinished) {
                    splitIndex++;
                    readByTTS(text.toString());
                }
            }

            @Override
            public void onError(String utteranceId) {

            }
        });

    }

    /**
     * Configure the text to read and read the text using TTS
     *
     * @param text
     */
    private static synchronized void readByTTS(String text) {
        if (textToSpeechForReadAloud != null) {
            splitTextFinal = text.split("\n|\\. ");
            languageSupportCheck = textToSpeechForReadAloud.setLanguage(Locale.US);
            myHashUtteranceId.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, splitTextFinal[splitIndex]);
            myHashUtteranceId.put(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC + "");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeechForReadAloud.speak(splitTextFinal[splitIndex], TextToSpeech.QUEUE_ADD, null, splitTextFinal[splitIndex]);
            } else {
                textToSpeechForReadAloud.speak(splitTextFinal[splitIndex], TextToSpeech.QUEUE_ADD, myHashUtteranceId);
            }
        }
    }

    /**
     * Put the Selection of text in UI while the text to speech starts
     *
     * @param text
     * @param utteranceId
     * @param readAloudListener
     */
    private static synchronized void putSelectionTOUI(CharSequence text, String utteranceId, ReadAloudListener readAloudListener) {
        Spannable spanText = Spannable.Factory.getInstance().newSpannable(text);
        int stringPosition = text.toString().indexOf(utteranceId);
        spanText.setSpan(new BackgroundColorSpan(ResourceCompat.getColor(DemoApplication.getKycApplicationContext(), R.color.colorAccent)), stringPosition, stringPosition + utteranceId.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        readAloudListener.putAnimationToUI(spanText, TTSState.ACTIVE);
    }

    /**
     * On Done reading the sentence
     *
     * @param context
     * @param text
     * @param lastText
     * @param utteranceId
     * @param readAloudListener
     */
    private static synchronized boolean doneReadSentence(Context context, CharSequence text, boolean lastText,
                                                         String utteranceId, ReadAloudListener readAloudListener) {
        Spannable spanText = Spannable.Factory.getInstance().newSpannable(text);
        ArrayList arrayList = new ArrayList(Arrays.asList(splitTextFinal));
        int stringPosition = text.toString().indexOf(utteranceId);
        int color = -1;
        color = ResourceCompat.getColor(context, android.R.color.transparent);
        spanText.setSpan(new BackgroundColorSpan(color), 0, stringPosition + utteranceId.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        readAloudListener.putAnimationToUI(spanText, TTSState.INACTIVE);
        if (lastText) {
            audioManager.abandonAudioFocus(afChangeListener);
        }
        if (arrayList.indexOf(utteranceId) == (arrayList.size() - 1)) {
            readAloudListener.onCompleteOneText();
        }
        return arrayList.indexOf(utteranceId) == (arrayList.size() - 1);
    }

    public enum TTSState {
        ACTIVE,
        INACTIVE
    }
}
