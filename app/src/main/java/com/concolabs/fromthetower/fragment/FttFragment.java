package com.concolabs.fromthetower.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.concolabs.fromthetower.R;
import com.concolabs.fromthetower.view.GifView;

public class FttFragment extends Fragment {

    private ImageView mFttPressMe;
    private ImageView mFttButton;
    private ImageView mFttButtonPressed;
    private ImageView mFttEntrance;
    private GifView mFttDance;
    private GifView mFttLights;
    private GifView mFttMirrorBall;

    private MediaPlayer mIntroMP;
    private MediaPlayer mMusicMP;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ftt, container, false);
        bindViews(v);
        init();
        return v;
    }

    private void bindViews(View v) {
        mFttPressMe = (ImageView) v.findViewById(R.id.fragment_ftt_press_me);
        mFttButton = (ImageView) v.findViewById(R.id.fragment_ftt_button);
        mFttButtonPressed = (ImageView) v.findViewById(R.id.fragment_ftt_button_pressed);
        mFttEntrance = (ImageView) v.findViewById(R.id.fragment_ftt_entrance);
        mFttDance = (GifView) v.findViewById(R.id.fragment_ftt_dance);
        mFttLights = (GifView) v.findViewById(R.id.fragment_ftt_lights);
        mFttMirrorBall = (GifView) v.findViewById(R.id.fragment_ftt_mirror_ball);
    }

    private void init() {
        mFttButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        mFttButtonPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    private void play() {
        mFttButton.setVisibility(View.GONE);
        mFttButtonPressed.setVisibility(View.VISIBLE);
        mFttEntrance.setVisibility(View.VISIBLE);
        mFttPressMe.setVisibility(View.GONE);
        mIntroMP = MediaPlayer.create(getActivity(), R.raw.luki);
        mIntroMP.start();
        mIntroMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mFttEntrance.setVisibility(View.GONE);
                mFttDance.setVisibility(View.VISIBLE);
                mFttMirrorBall.setVisibility(View.VISIBLE);
                mFttLights.setVisibility(View.VISIBLE);
                mMusicMP = MediaPlayer.create(getActivity(), R.raw.ftt);
                mMusicMP.start();
                mMusicMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        restore();
                    }
                });
            }
        });
    }

    private void stop() {
        if (mMusicMP != null && mMusicMP.isPlaying()) mMusicMP.stop();
        if (mIntroMP != null && mIntroMP.isPlaying()) mIntroMP.stop();
        restore();
    }

    private void restore() {
        mFttButton.setVisibility(View.VISIBLE);
        mFttButtonPressed.setVisibility(View.GONE);
        mFttEntrance.setVisibility(View.GONE);
        mFttDance.setVisibility(View.GONE);
        mFttPressMe.setVisibility(View.VISIBLE);
        mFttMirrorBall.setVisibility(View.GONE);
        mFttLights.setVisibility(View.GONE);
    }
}
