package com.soulet.simon.moodtracker2.controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soulet.simon.moodtracker2.R;
import com.soulet.simon.moodtracker2.utils.SaveMoodReceiver;

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

    private int moodWeek[] = new int[7];
    private String commentWeek[] = new String[7];

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        this.configureLayouts();
        this.configureImages();
        this.getPreferencesFromReceiver();
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

    //----------------------------------------------------------------------------------------------
    //                                     UPDATE UI
    //----------------------------------------------------------------------------------------------

    private void getPreferencesFromReceiver(){ //Get moods and comments stock in preferences
        for(int i = 0; i < 7; i++){
            moodWeek[i] = mPreferences.getInt(SaveMoodReceiver.PREF_KEY_MOOD+i,-1);
            Log.d("TAG", "mood"+ moodWeek[i]);
            commentWeek[i] = mPreferences.getString(SaveMoodReceiver.PREF_KEY_COMMENT+i, "");
            Log.d("TAG", "comment"+ commentWeek[i]);

        }

        System.out.println("Tableau HistoryActivity");
        for(int i = 0; i < 7; i++){
            System.out.println("moodWeek à l'emplacement "+i+" = "+moodWeek[i]);
            System.out.println("commentWeek à l'emplacement "+i+" = "+commentWeek[i]);
        }
    }

    private View.OnClickListener onClickLayout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
}
