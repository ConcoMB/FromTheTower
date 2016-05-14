package com.concolabs.fromthetower.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import com.concolabs.fromthetower.R;

import java.io.InputStream;

public class GifView extends View {
    public Movie mMovie;
    public long mMovieStart;
    private int mGifId;

    public GifView(Context context) {
        super(context);
        initializeView();
    }

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
        initializeView();
    }

    public GifView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(context, attrs);
        initializeView();
    }

    private void initializeView() {
        if (mGifId == 0) return;
        InputStream is = getContext().getResources().openRawResource(mGifId);
        mMovie = Movie.decodeStream(is);
    }

    private void parseAttributes(Context ctx, AttributeSet attrs) {
        TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.GifView);
        if (typedArray == null) return;
        String gifName = typedArray.getString(R.styleable.GifView_src);
        if (gifName == null) return;
        String[] split = gifName.split("/");
        gifName = split[split.length - 1].split("\\.")[0];
        mGifId = ctx.getResources().getIdentifier(gifName, "drawable", ctx.getPackageName());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        if (mMovie != null) {
            int relTime = (int) ((now - mMovieStart) % mMovie.duration());
            mMovie.setTime(relTime);
            mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height());
            this.invalidate();
        }
    }

    public void setGifResource(int resId) {
        this.mGifId = resId;
        initializeView();
    }

    public int getGifResource() {
        return this.mGifId;
    }
}
