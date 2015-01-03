package com.squareup.timessquare.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView mTextView;
    private TextView mTextViewTarget;
    AnimUtils animUtils = new AnimUtils();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mTextView = (TextView) findViewById(R.id.textView);
//        mTextViewTarget = (TextView) findViewById(R.id.textView_target);
//        mTextViewTarget.setTranslationY(-300);
//        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (count%2==0){
            animUtils.showAnimate(mTextViewTarget);
        }else{
            animUtils.dismissAnimate(mTextViewTarget);
        }
        count++;
    }


}
