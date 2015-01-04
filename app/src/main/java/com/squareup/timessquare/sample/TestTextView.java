package com.squareup.timessquare.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

/**
 * Package : com.squareup.timessquare.sample
 * Author : jacob
 * Date : 15-1-4
 * Description : 这个类是用来xxx
 */
public class TestTextView extends TextView {
    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        ObjectAnimator slideInAnim = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y,
                getY());

        slideInAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        slideInAnim.setDuration(400);
        slideInAnim.start();
        super.onAttachedToWindow();
    }
}
