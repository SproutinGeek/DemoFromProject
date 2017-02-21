package com.tml.demo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tml.demo.R;
import com.tml.demo.exception.ErrorMessageFactory;
import com.tml.demo.ui.fragment.DemoFragment;
import com.tml.demo.ui.listeners.OnBackPressedListener;
import com.tml.demoframwork.exception.ErrorBundle;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private OnBackPressedListener onBackPressedListener;
    @Bind(R.id.container_frame)
    protected FrameLayout containerFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // load saved navigation state if present
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container_frame,
                    DemoFragment.newInstance(), DemoFragment.class.getSimpleName())
                    .commit();
        }

    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    /**
     * showing error message
     *
     * @param errorBundle {@link ErrorBundle}
     */
    public void showErrorMessage(ErrorBundle errorBundle){
        Toast.makeText(this, ErrorMessageFactory.create(this, errorBundle.getException()), Toast
                .LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if(onBackPressedListener == null || onBackPressedListener.onBackPressed()){
            super.onBackPressed();
        }
    }
}
