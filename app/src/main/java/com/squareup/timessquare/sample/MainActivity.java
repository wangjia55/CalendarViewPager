package com.squareup.timessquare.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends FragmentActivity{
    int count = 0;
    private TextView mTextViewTarget;
    private AnimUtils animUtils = new AnimUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mTextViewTarget = (TextView) findViewById(R.id.textView3);
//        mTextViewTarget.setTranslationY(-500);
//        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (count%2==0){
//                    animUtils.showAnimate(mTextViewTarget);
//                }else{
//                    animUtils.dismissAnimate(mTextViewTarget);
//                }
//                count++;
//            }
//        });
    }


}
