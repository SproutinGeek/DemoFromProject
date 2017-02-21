package com.tml.demo.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tml.demo.R;
import com.tml.demo.compat.ResourceCompat;
import com.tml.demo.ui.activity.MainActivity;
import com.tml.demo.ui.listeners.OnBackPressedListener;
import com.tml.demo.ui.listeners.ReadAloudListener;
import com.tml.demo.ui.listeners.TTSAudioFeedbackListener;
import com.tml.demo.ui.widget.ScrollObservableScrollView;
import com.tml.demo.utils.DemoUtitities;
import com.tml.demo.utils.TextToSpeechUtils;
import com.tml.demoframwork.QuickGuideBL;
import com.tml.demoframwork.dto.DetailManualDTO;
import com.tml.demoframwork.dto.ImageDTO;
import com.tml.demoframwork.dto.SubTitleDTO;
import com.tml.demoframwork.dto.TitleDTO;
import com.tml.demoframwork.exception.ErrorBundle;
import com.tml.demoframwork.listeners.DetailmanualDataListener;
import com.tml.demoframwork.utils.FrameworkConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arun Pillai on 2/9/2017.
 */

public class DemoFragment extends Fragment implements OnBackPressedListener , ScrollObservableScrollView.OnScrollChangedListener{
    private Context _mContext;
    private View _rootView;
    private ProgressDialog _mProgressDialog;
    @Bind(R.id.details_frame)
    protected LinearLayout detailsFrame;

    @Bind(R.id.detail_manual_layout_scroll)
    protected ScrollObservableScrollView detailManualScroll;
    private int _totalChildCount;
    private int _childPosition;
    private boolean _isReading;
    private boolean _isPaused;
    private boolean _isResumed;
    private AlertDialog _alertDialog;

    public static DemoFragment newInstance(){
        return new DemoFragment();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity) _mContext).setOnBackPressedListener(null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _mContext = context;
        ((MainActivity) _mContext).setOnBackPressedListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _rootView = inflater.inflate(R.layout.fragment_demo, container, false);
        ButterKnife.bind(this, _rootView);
        setHasOptionsMenu(true);
        detailManualScroll.setOnScrollChangedListener(this);
        new QuickGuideBL(_mContext).getDetailManualDetailsByID(0, new DetailmanualDataListener() {
            @Override
            public void databaseDetailManualDTOReceiver(DetailManualDTO detailManualDTO,
                                                        ErrorBundle errorBundle) {
                if(errorBundle != null){
                    ((MainActivity) _mContext).showErrorMessage(errorBundle);
                }
                if(detailManualDTO != null){
                    populateValuesToUI(detailManualDTO);
                }

            }
        });
        return _rootView;
    }

    /**
     * Show the values to the screen.
     */
    private void populateValuesToUI(DetailManualDTO detailManualDTO) {
        if (detailManualDTO.getContentHeading() != null) {
            TextView textView = new TextView(_mContext);
            textView.setText(detailManualDTO.getContentHeading());
            textView.setTextSize(20f);
            detailsFrame.addView(textView);
        }
        if (detailManualDTO.getTitleDTOs() != null && !detailManualDTO.getTitleDTOs().isEmpty()) {
            for (TitleDTO titleDTO : detailManualDTO.getTitleDTOs()) {
                if (!TextUtils.isEmpty(titleDTO.getTitleValue())) {
                    TextView textView = new TextView(_mContext);
                    textView.setText(titleDTO.getTitleValue());
                    textView.setTextSize(18f);
                    detailsFrame.addView(textView);
                }
                if (!TextUtils.isEmpty(titleDTO.getIntroduction())) {
                    TextView textView = new TextView(_mContext);
                    textView.setText(titleDTO.getIntroduction());
                    detailsFrame.addView(textView);
                }
                if (titleDTO.getSubTitleDTOs() != null && !titleDTO.getSubTitleDTOs().isEmpty()) {
                    for (SubTitleDTO subTitleDTO : titleDTO.getSubTitleDTOs()) {
                        if (!TextUtils.isEmpty(subTitleDTO.getTitleValue())) {
                            TextView textView = new TextView(_mContext);
                            textView.setText(subTitleDTO.getTitleValue());
                            textView.setTextSize(16f);
                            detailsFrame.addView(textView);
                        }

                        if (subTitleDTO.getContentImage() != null) {
                            final ImageDTO imageDTO = subTitleDTO.getContentImage();
                            if (!TextUtils.isEmpty(imageDTO.getFilePath())) {
                                final ImageView imageView = new ImageView(_mContext);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout
                                        .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(0, 0,
                                        0, 0);
                                imageView.setLayoutParams(layoutParams);
                                imageView.setAdjustViewBounds(true);
                                imageView.setTag(imageDTO.getFilePath());
                                /*ImageLoader.getInstance().loadBitmap(String.valueOf
                                        (detailManualDTO.getContent_Id()), _mContext,
                                        imageView, DisplayMetrics.DENSITY_XHIGH, -1,
                                        FrameworkConstants.ACTUAL_IMAGE_FILES_FOLDER + imageDTO.getFilePath(), null);*/
                                detailsFrame.addView(imageView);
                            }
                            if (!TextUtils.isEmpty(imageDTO.getImageCaption())) {
                                TextView textView = new TextView(_mContext);
                                textView.setText(imageDTO.getImageCaption());
                                detailsFrame.addView(textView);
                            }
                            if (!TextUtils.isEmpty(imageDTO.getImageDescription())) {
                                TextView textView = new TextView(_mContext);
                                textView.setText(imageDTO.getImageDescription());
                                detailsFrame.addView(textView);
                            }
                        }

                    }

                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tts_read_out, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_read:
                onClickReadOutMenu();
                return true;
            default:
                break;
        }

        return false;
    }
    /**
     * Read aloud
     * */
    private void onClickReadOutMenu() {
        _mProgressDialog = new ProgressDialog(_mContext);
        _mProgressDialog.setMessage(_mContext.getString(R.string.please_wait));
        _mProgressDialog.setCancelable(false);
        _mProgressDialog.show();
        _totalChildCount = detailsFrame.getChildCount();
        if (!_isPaused) {
            _childPosition = 0;
        }
        readAloud();
    }

    /**
     * Read the contents of the screens.
     */
    private void readAloud() {
        if (_childPosition < _totalChildCount) {
            final View viewTemp = detailsFrame.getChildAt(_childPosition);
            CharSequence textToRead = null;
            if (viewTemp instanceof TextView) {
                textToRead = ((TextView) viewTemp).getText();
            } /*else if (viewTemp instanceof NotesAndWarningWidget) {
                textToRead = ((NotesAndWarningWidget) viewTemp).getDataText()
                        .toString();
            }*/ /*else if (viewTemp instanceof ImageView) {

            }*/

            if (!DemoUtitities.isViewInScreeRect(viewTemp, detailManualScroll)) {
                ((Activity) _mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        detailManualScroll.scrollTo(0, ((int) viewTemp.getY()));
                    }
                });
            }
            new ReadAloudAsyncTask(viewTemp, textToRead, _mContext).execute();
            _isReading = true;
        }

    }

    /**
     * Create Alert Dialogue
     */
    private void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(_mContext);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(R.layout.read_aloud_popup);
        } else {
            builder.setView(LayoutInflater.from(_mContext).inflate(R.layout.read_aloud_popup, null));
        }
        _alertDialog = builder.create();
        _alertDialog.setCancelable(false);
        _alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        _alertDialog.getWindow().getAttributes().gravity = Gravity.TOP;
        _alertDialog.getWindow().getAttributes().y = 0;   //y position
        _alertDialog.show();
    }

    /**
     * Show popup for the pause and stop of read aloud
     */
    private void showPopUpWithStopReading() {
        createAlertDialog();
        ButterKnife.findById(_alertDialog, R.id.btn_text_tts_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) _mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        _isPaused = false;
                        onStopTTSTask();
                        _isReading = false;
                    }
                });
            }
        });
        ButterKnife.findById(_alertDialog, R.id.btn_text_tts_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) _mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        _isPaused = true;
                        onStopTTSTask();
                        _isReading = false;
                    }
                });
            }
        });
    }

    /**
     * Stop TTS task
     */
    private void onStopTTSTask() {
        TextToSpeechUtils.shutdownTextToSpeech(_isPaused);
        final View viewTemp = detailsFrame.getChildAt(_childPosition);
        String textToRead;
        if (viewTemp instanceof TextView) {
            textToRead = ((TextView) viewTemp).getText()
                    .toString();
            Spannable spanText = Spannable.Factory.getInstance().newSpannable(textToRead);
            int color = ResourceCompat.getColor(_mContext, android.R.color.transparent);
            spanText.setSpan(new BackgroundColorSpan(color), 0, textToRead.lastIndexOf(textToRead), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((TextView) viewTemp).setText(spanText);
        } /*else if (viewTemp instanceof NotesAndWarningWidget) {
            textToRead = ((NotesAndWarningWidget) viewTemp).getDataText()
                    .toString();
            Spannable spanText = Spannable.Factory.getInstance().newSpannable(textToRead);
            int color = ResourceCompat.getColor(mContextActivity, android.R.color.transparent);
            spanText.setSpan(new BackgroundColorSpan(color), 0, textToRead.lastIndexOf(textToRead), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((NotesAndWarningWidget) viewTemp).setDataText(spanText);
        }*/
        if (_alertDialog != null && _alertDialog.isShowing()) {
            alertDialogClose();
        }
    }

    /**
     * Close alert dialog
     */
    private void alertDialogClose() {
        _alertDialog.dismiss();
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void onScrollChanged(ScrollObservableScrollView v, int l, int t, int oldl, int oldt) {

    }


    /**
     * AsyncTask For Read aloud for not to make the UI Stuck
     */
    private class ReadAloudAsyncTask extends AsyncTask<Void, Void, Void> {

        private View viewToRead;
        private CharSequence textToRead;
        private Context context;
        private ReadAloudListenerImpl readAloudListener;

        ReadAloudAsyncTask(View viewToRead, CharSequence textToRead, Context context) {
            this.viewToRead = viewToRead;
            this.textToRead = textToRead;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            readAloudListener = new ReadAloudListenerImpl(viewToRead);
            if (!TextUtils.isEmpty(textToRead)) {
                TextToSpeechUtils.readAloudText(context, textToRead,
                        new ReadAloudListenerImpl(viewToRead),
                        (_childPosition == (_totalChildCount - 1)),
                        new TTSAudioFeedbackListenerImpl());
            } else {
                readAloudListener.onCompleteOneText();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    /**
     * listens to the UI related event while the Text To speech utility is working
     */
    public class ReadAloudListenerImpl implements ReadAloudListener {

        private View viewToSelect;

        ReadAloudListenerImpl(View view) {
            this.viewToSelect = view;
        }

        @Override
        public void onCompleteOneText() {
            if ((_childPosition == (_totalChildCount - 2))) {
                alertDialogClose();
            }
            _childPosition++;
            readAloud();
        }

        @Override
        public void putAnimationToUI(final CharSequence charSequence, final TextToSpeechUtils.TTSState state) {
            ((Activity) _mContext).runOnUiThread(new Runnable() {
                public void run() {
                    if (state == TextToSpeechUtils.TTSState.ACTIVE) {
                        if (!(_alertDialog != null && _alertDialog.isShowing())) {
                            showPopUpWithStopReading();
                        }
                        _mProgressDialog.dismiss();
                    }
                    if (viewToSelect instanceof TextView) {
                        ((TextView) viewToSelect).setText(charSequence);
                    } /*else if (viewToSelect instanceof NotesAndWarningWidget) {
                        ((NotesAndWarningWidget) viewToSelect).setDataText(charSequence);
                    }*/

                }

            });
        }

        @Override
        public void onError() {
            _isReading = false;
            _mProgressDialog.dismiss();
            Toast.makeText(_mContext, R.string.please_try, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * listens to the UI related event while the Text To speech utility is working
     */
    public class TTSAudioFeedbackListenerImpl implements TTSAudioFeedbackListener {

        @Override
        public void stopTTSListener() {
            _isPaused = false;
            onStopTTSTask();
            _isReading = false;
        }

        @Override
        public void startTTSListener() {
            _totalChildCount = detailsFrame.getChildCount();
            if (_isResumed) {
                if (_isPaused) {
                    readAloud();
                } else {
                    _childPosition = 0;
                    readAloud();
                }
            }

        }

        @Override
        public void pauseTTSListener() {
            _isPaused = true;
            onStopTTSTask();
            _isReading = false;
        }
    }
}
