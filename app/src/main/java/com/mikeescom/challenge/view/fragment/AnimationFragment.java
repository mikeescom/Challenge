package com.mikeescom.challenge.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mikeescom.challenge.R;
import com.mikeescom.challenge.view.utils.AnimationUtils;

public class AnimationFragment extends Fragment {
    private static final String TAG = AnimationFragment.class.getSimpleName();

    private Context context;
    int position;
    private ScrollView scrollView;
    private View flightTerminal;
    private View otherInfo;
    private View layout;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        AnimationFragment animationFragment = new AnimationFragment();
        animationFragment.setArguments(bundle);
        return animationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollView = view.findViewById(R.id.scrollview);
        flightTerminal = view.findViewById(R.id.flight_terminal);
        otherInfo = view.findViewById(R.id.other_info);
        layout = view.findViewById(R.id.layout);
        scrollView.setOnTouchListener((view1, motionEvent) -> {
            animate();
            return false;
        });
    }

    private void animate() {
        if (flightTerminal.getVisibility() == View.VISIBLE) {
            AnimationUtils.fadeIn(context, otherInfo, false);
            AnimationUtils.fadeOut(context, flightTerminal, true);
            AnimationUtils.translateUp(layout, 425);
            //AnimationUtils.collapseView(context, otherInfo, separator1, separator2, separator3, separator4, separator5);
        } else if (flightTerminal.getVisibility() == View.GONE) {
            AnimationUtils.fadeIn(context, flightTerminal, true);
            AnimationUtils.fadeOut(context, otherInfo, false);
            AnimationUtils.translateDown(layout, 205);
            //AnimationUtils.expandView(context, otherInfo, separator1, separator2, separator3, separator4, separator5);
        }
    }
}
