package com.soulet.simon.moodtracker2.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.soulet.simon.moodtracker2.R;
import com.soulet.simon.moodtracker2.utils.SaveMoodReceiver;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private ImageView mSmiley;
    private Button mComment;
    private Button mHistory;
    private RelativeLayout mLayout;
    private int mCurrentMood; // to stock the mood of the user on scroll
    private GestureDetectorCompat mGesture; // to configure the scroll

    private SharedPreferences mPreferences; //to stock the mood and comment of the day
    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";
    public static final String PREF_KEY_MOOD = "PREF_KEY_MOOD";

    private PendingIntent mPendingIntent; // to execute broadcast

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

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        this.getPreviousMoodPreferences();
        this.initResources();
        this.configureGestureDetectorAndLayout();
        this.configureCommentBtn();
        this.configureHistoryBtn();
        this.configureAlarmManager();
        this.saveMoodOfTheDay();
    }

    @Override
    protected void onStop() { // stock the select mood when the activity stop
        super.onStop();
        mPreferences.edit().putInt(PREF_KEY_MOOD, mCurrentMood).apply();
    }

    //----------------------------------------------------------------------------------------------
    //                                      INITIALIZATION
    //----------------------------------------------------------------------------------------------

    private void getPreviousMoodPreferences(){ // get the last mood that the user save when we start the activity
        int mood = mPreferences.getInt(PREF_KEY_MOOD, -1);
        if(mood != -1){
            mCurrentMood = mood;
        }else{
            mCurrentMood = 3;
        }
    }

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

    private void configureCommentBtn(){
        mComment = (Button) findViewById(R.id.activity_main_comment_btn);
        mComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Commentaire");
                final EditText comment = new EditText(MainActivity.this);
                builder.setView(comment);
                builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPreferences.edit().putString(PREF_KEY_COMMENT, comment.getText().toString()).apply();
                        mPreferences.edit().putInt(PREF_KEY_MOOD, mCurrentMood).apply();
                        String userComment = mPreferences.getString(PREF_KEY_COMMENT, "");
                        if(userComment.equals("")){
                            Toast.makeText(MainActivity.this, "Commentaire enregistré !", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Nouveau commentaire enregistré !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    private void configureHistoryBtn(){
        mHistory = (Button) findViewById(R.id.activity_main_history_btn);
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyactivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyactivity);
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

    //----------------------------------------------------------------------------------------------
    //                                   SAVE MOOD AND COMMENT
    //----------------------------------------------------------------------------------------------

    private void configureAlarmManager(){
        Intent alarmIntent = new Intent(MainActivity.this, SaveMoodReceiver.class);
        alarmIntent.putExtra(PREF_KEY_MOOD, mCurrentMood);
        String userComment = mPreferences.getString(PREF_KEY_COMMENT, "");
        alarmIntent.putExtra(PREF_KEY_COMMENT, userComment);
        mPendingIntent = PendingIntent.getBroadcast(MainActivity.this,0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void saveMoodOfTheDay(){ //Save the current mood at 0:00 am
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+1);
        manager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 60*1000, mPendingIntent);
    }
}
