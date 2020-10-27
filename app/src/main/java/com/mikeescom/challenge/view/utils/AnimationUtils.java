package com.mikeescom.challenge.view.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.mikeescom.challenge.R;

public class AnimationUtils {
    private static SparseArray<Animation> animationCache = new SparseArray<>();

    private static final int FADE_IN = 1;
    private static final int FADE_OUT = 2;

    private static int FADE_DELAY = 1000;
    private static int TRANS_DELAY = 1000;
    private static int COLLEXAP_DELAY = 1000;

    public static void translateUp(View view, int padding) {
        int[] coords = {0,0};
        view.getLocationOnScreen(coords);
        int y = coords[1]-padding;
        TranslateAnimation toTop = new TranslateAnimation(0, 0, 0, -y);
        toTop.setDuration(TRANS_DELAY);
        toTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                view.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(toTop);
        //view.setVisibility(View.VISIBLE);
    }

    public static void translateDown(View view, int padding) {
        int[] coords = {0,0};
        view.getLocationOnScreen(coords);
        int y = coords[1]+padding;
        TranslateAnimation toDown = new TranslateAnimation(0, 0, 0, y);
        toDown.setDuration(TRANS_DELAY);
        toDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                view.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(toDown);
        //view.setVisibility(View.VISIBLE);
    }

    public static void fadeIn(Context context, View view, boolean isFlightLayout) {
        Animation animation = animationCache.get(FADE_IN);
        if (animation == null) {
            animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_in);
            animationCache.put(FADE_IN, animation);
        }

        animation.setDuration(FADE_DELAY);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(isFlightLayout ? View.GONE:View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public static void fadeOut(Context context, View view, boolean isFlightLayout) {
        Animation animation = animationCache.get(FADE_OUT);
        if (animation == null) {
            animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_out);
            animationCache.put(FADE_OUT, animation);
        }

        animation.setDuration(FADE_DELAY);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(isFlightLayout ? View.GONE:View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public static void expandView(Context context, View other, View view1, View view2, View view3, View view4, View view5) {
        AnimationSet set = new AnimationSet(true);

        Animation otherAnim = animationCache.get(FADE_OUT);
        if (otherAnim == null) {
            otherAnim = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_out);
            animationCache.put(FADE_OUT, otherAnim);
        }

        otherAnim.setDuration(FADE_DELAY);
        otherAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                other.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                other.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        final int targetHeight1 = view1.getMeasuredHeight();
        final int targetHeight2 = view1.getMeasuredHeight();
        final int targetHeight3 = view1.getMeasuredHeight();
        final int targetHeight4 = view1.getMeasuredHeight();
        final int targetHeight5 = view1.getMeasuredHeight();

        view1.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view1.getLayoutParams().height = 0;
        view1.setVisibility(View.VISIBLE);
        Animation animation1 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view1.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight1 * interpolatedTime);
                view1.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        view2.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view2.getLayoutParams().height = 0;
        view2.setVisibility(View.VISIBLE);
        Animation animation2 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view2.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight2 * interpolatedTime);
                view2.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        view3.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view3.getLayoutParams().height = 0;
        view3.setVisibility(View.VISIBLE);
        Animation animation3 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view3.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight3 * interpolatedTime);
                view3.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        view4.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view4.getLayoutParams().height = 0;
        view4.setVisibility(View.VISIBLE);
        Animation animation4 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view4.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight4 * interpolatedTime);
                view4.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        view5.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view5.getLayoutParams().height = 0;
        view5.setVisibility(View.VISIBLE);
        Animation animation5 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view5.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight5 * interpolatedTime);
                view5.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        set.addAnimation(otherAnim);
        set.addAnimation(animation1);
        set.addAnimation(animation2);
        set.addAnimation(animation3);
        set.addAnimation(animation4);
        set.addAnimation(animation5);

        set.start();
    }

    public static void collapseView(Context context, View other, View view1, View view2, View view3, View view4, View view5) {
        Animation otherAnim = animationCache.get(FADE_IN);
        if (otherAnim == null) {
            otherAnim = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_in);
            animationCache.put(FADE_IN, otherAnim);
        }

        otherAnim.setDuration(FADE_DELAY);
        otherAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                other.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                other.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        AnimationSet set = new AnimationSet(true);

        final int initialHeight1 = view1.getMeasuredHeight();
        final int initialHeight2 = view2.getMeasuredHeight();
        final int initialHeight3 = view3.getMeasuredHeight();
        final int initialHeight4 = view4.getMeasuredHeight();
        final int initialHeight5 = view5.getMeasuredHeight();

        Animation animation1 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view1.setVisibility(View.GONE);
                } else {
                    view1.getLayoutParams().height = initialHeight1 - (int)(initialHeight1 * interpolatedTime);
                    view1.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        Animation animation2 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view2.setVisibility(View.GONE);
                } else {
                    view2.getLayoutParams().height = initialHeight2 - (int)(initialHeight2 * interpolatedTime);
                    view2.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        Animation animation3 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view3.setVisibility(View.GONE);
                } else {
                    view3.getLayoutParams().height = initialHeight3 - (int)(initialHeight3 * interpolatedTime);
                    view3.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        Animation animation4 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view4.setVisibility(View.GONE);
                } else {
                    view4.getLayoutParams().height = initialHeight4 - (int)(initialHeight4 * interpolatedTime);
                    view4.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        Animation animation5 = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view5.setVisibility(View.GONE);
                } else {
                    view5.getLayoutParams().height = initialHeight5 - (int)(initialHeight5 * interpolatedTime);
                    view5.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation1.setDuration(COLLEXAP_DELAY);

        set.addAnimation(otherAnim);
        set.addAnimation(animation1);
        set.addAnimation(animation2);
        set.addAnimation(animation3);
        set.addAnimation(animation4);
        set.addAnimation(animation5);

        set.start();
    }
}
