package com.soulet.simon.moodtracker2.controller;

import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soulet.simon.moodtracker2.R;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private ImageView mSmiley;
    private Button mComment;
    private Button mHistory;
    private RelativeLayout mLayout;
    private int mCurrentMood; // to stock the mood of the user on scroll
    private GestureDetectorCompat mGesture; // to configure the scroll

    int smiley[] = {R.drawable.smiley_sad, R.drawable.smiley_disappointed, R.drawable.smiley_normal,
            R.drawable.smiley_happy, R.drawable.smiley_super_happy};

    int color[] = {R.color.faded_red, R.color.warm_grey, R.color.cornflower_blue_65,
            R.color.light_sage, R.color.banana_yellow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (RelativeLayout) findViewById(R.id.activity_main_layout);
        mSmiley = (ImageView) findViewById(R.id.activity_main_smiley_img);

        mCurrentMood = 3;

        this.initResources();
        this.configureGestureDetectorAndLayout();
    }

    //----------------------------------------------------------------------------------------------
    //                                      INITIALIZATION
    //----------------------------------------------------------------------------------------------

    private void initResources(){
        mLayout.setBackgroundResource(color[mCurrentMood]);
        mSmiley.setImageResource(smiley[mCurrentMood]);
    }

    //----------------------------------------------------------------------------------------------
    //                                     CONFIGURATIONS
    //----------------------------------------------------------------------------------------------
    private void configureGestureDetectorAndLayout(){
        mGesture = new GestureDetectorCompat(this, this);
        mGesture.setOnDoubleTapListener(this);
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGesture.onTouchEvent(event);
                return true;
            }
        });
    }

    //----------------------------------------------------------------------------------------------
    //                                       UPDATE UI (SCROLL)
    //----------------------------------------------------------------------------------------------

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (velocityY > 0){
            this.nextMood();
        }
        if (velocityY < 0){
            this.previousMood();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void nextMood(){ // When the user scroll up to down
        if(mCurrentMood < 4) {
            mCurrentMood++;
            mLayout.setBackgroundResource(color[mCurrentMood]);
            mSmiley.setImageResource(smiley[mCurrentMood]);
        }
    }

    public void previousMood(){ // When the user scroll down to up
        if (mCurrentMood > 0){
            mCurrentMood--;
            mSmiley.setImageResource(smiley[mCurrentMood]);
            mLayout.setBackgroundResource(color[mCurrentMood]);
        }
    }
}
