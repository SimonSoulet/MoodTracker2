package com.soulet.simon.moodtracker2.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soulet.simon.moodtracker2.R;

public class MainActivity extends AppCompatActivity {

    private ImageView mSmiley;
    private Button mComment;
    private Button mHistory;
    private RelativeLayout mLayout;
    private int mCurrentMood; // to stock the mood of the user on scroll

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
    }

    //----------------------------------------------------------------------------------------------
    //                                      INITIALIZATION
    //----------------------------------------------------------------------------------------------

    private void initResources(){
        mLayout.setBackgroundResource(color[mCurrentMood]);
        mSmiley.setImageResource(smiley[mCurrentMood]);
    }
}
