package com.giants3.hd.android.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.giants3.hd.android.R;

import butterknife.Bind;

public class LongTextActivity extends BaseActivity {


    public static final String PARAM_TITLE = "TITLE";
    public static final String PARAM_CONTENT = "CONTENT";


    @Bind(R.id.text)
    TextView textView;
    @Bind(R.id.title)
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_text);


        String title = getIntent().getStringExtra(PARAM_TITLE);

        titleView.setText(title);
        String content = getIntent().getStringExtra(PARAM_CONTENT);
        textView.setText(content);

        textView.setMovementMethod(new ScrollingMovementMethod());

    }

}
