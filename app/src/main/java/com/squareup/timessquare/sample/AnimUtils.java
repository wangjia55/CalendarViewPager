package com.squareup.timessquare.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Package : com.squareup.timessquare.sample
 * Author : jacob
 * Date : 15-1-3
 * Description : 这个类是用来xxx
 */
public class AnimUtils {


    public void showAnimate(final View view) {
        final ViewGroup parentView = (ViewGroup) view.getParent();
        final int positionView = parentView.indexOfChild(view);

        final FrameLayout slideInFrame = new FrameLayout(view.getContext());
        slideInFrame.setLayoutParams(view.getLayoutParams());
        slideInFrame.setClipChildren(true);

        parentView.removeView(view);
        slideInFrame.addView(view);
        parentView.addView(slideInFrame, positionView);


        ObjectAnimator slideInAnim = null;
        final float viewHeight = view.getHeight();

        view.setVisibility(View.VISIBLE);
        view.setTranslationY(-viewHeight);

        slideInAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y,
                slideInFrame.getY());

        slideInAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        slideInAnim.setDuration(300);
        slideInAnim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                view.setTranslationY(-viewHeight);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                slideInFrame.removeAllViews();
                view.setLayoutParams(slideInFrame.getLayoutParams());
                parentView.removeView(slideInFrame);
                parentView.addView(view, positionView);
            }
        });
        slideInAnim.start();
    }


    public void dismissAnimate(final View view) {
        final ViewGroup parentView = (ViewGroup) view.getParent();

        final FrameLayout slideOutFrame = new FrameLayout(view.getContext());
        final int positionView = parentView.indexOfChild(view);
        slideOutFrame.setLayoutParams(view.getLayoutParams());
        slideOutFrame.setClipChildren(true);

        parentView.removeView(view);
        slideOutFrame.addView(view);
        parentView.addView(slideOutFrame, positionView);

        final ObjectAnimator slideAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y,
                view.getTranslationY() - view.getHeight());

        slideAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        slideAnim.setDuration(200);
        slideAnim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                slideOutFrame.removeAllViews();
                parentView.removeView(slideOutFrame);
                parentView.addView(view, positionView);
            }
        });
        slideAnim.start();
    }
}
