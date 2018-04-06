package com.soulet.simon.moodtracker2.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soulet.simon.moodtracker2.R;

public class HistoryActivity extends AppCompatActivity {

    private RelativeLayout mRelativeLayout1WA;
    private RelativeLayout mRelativeLayout6DA;
    private RelativeLayout mRelativeLayout5DA;
    private RelativeLayout mRelativeLayout4DA;
    private RelativeLayout mRelativeLayout3DA;
    private RelativeLayout mRelativeLayout2DA;
    private RelativeLayout mRelativeLayoutY;

    private ImageView mImageView1WA;
    private ImageView mImageView6DA;
    private ImageView mImageView5DA;
    private ImageView mImageView4DA;
    private ImageView mImageView3DA;
    private ImageView mImageView2DA;
    private ImageView mImageViewY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        this.configureLayouts();
        this.configureImages();
    }

    //----------------------------------------------------------------------------------------------
    //                                      CONFIGURATIONS
    //----------------------------------------------------------------------------------------------

    private void configureLayouts(){
        mRelativeLayout1WA = (RelativeLayout) findViewById(R.id.activity_history_layout_one_week_ago);
        mRelativeLayout6DA = (RelativeLayout) findViewById(R.id.activity_history_layout_six_days_ago);
        mRelativeLayout5DA = (RelativeLayout) findViewById(R.id.activity_history_layout_five_days_ago);
        mRelativeLayout4DA = (RelativeLayout) findViewById(R.id.activity_history_layout_four_days_ago);
        mRelativeLayout3DA = (RelativeLayout) findViewById(R.id.activity_history_layout_three_days_ago);
        mRelativeLayout2DA = (RelativeLayout) findViewById(R.id.activity_history_layout_two_days_ago);
        mRelativeLayoutY = (RelativeLayout) findViewById(R.id.activity_history_layout_yesterday);

        mRelativeLayout1WA.setOnClickListener(onClickLayout);
        mRelativeLayout6DA.setOnClickListener(onClickLayout);
        mRelativeLayout5DA.setOnClickListener(onClickLayout);
        mRelativeLayout4DA.setOnClickListener(onClickLayout);
        mRelativeLayout3DA.setOnClickListener(onClickLayout);
        mRelativeLayout2DA.setOnClickListener(onClickLayout);
        mRelativeLayoutY.setOnClickListener(onClickLayout);
    }

    private void configureImages(){
        mImageView1WA = (ImageView) findViewById(R.id.activity_history_img_OWA);
        mImageView6DA = (ImageView) findViewById(R.id.activity_history_img_SDA);
        mImageView5DA = (ImageView) findViewById(R.id.activity_history_img_FiveDA);
        mImageView4DA = (ImageView) findViewById(R.id.activity_history_img_FourDA);
        mImageView3DA = (ImageView) findViewById(R.id.activity_history_img_ThreeDA);
        mImageView2DA = (ImageView) findViewById(R.id.activity_history_img_TwoDA);
        mImageViewY = (ImageView) findViewById(R.id.activity_history_img_Y);
    }

    private View.OnClickListener onClickLayout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
}
