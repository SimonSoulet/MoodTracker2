package com.soulet.simon.moodtracker2.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.soulet.simon.moodtracker2.controller.MainActivity;

/**
 * Created by Simon on 06/04/2018.
 */

public class SaveMoodReceiver extends BroadcastReceiver{

    private SharedPreferences mPreferences;
    public static final String PREF_KEY_MOOD = "moodDay";
    public static final String PREF_KEY_COMMENT = "commentDay";

    @Override
    public void onReceive(Context context, Intent intent) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Log.e("TAG", "Humeur du jour sauvegardée");

        //Get mood and comment save by the user
        int mood = intent.getIntExtra(MainActivity.PREF_KEY_MOOD, -1);
        String comment = intent.getStringExtra(MainActivity.PREF_KEY_COMMENT);

        //Create 2 tables (mood and comment)
        int moodWeek[] = new int[7];
        String commentWeek[] = new String[7];

        //Put the mood and comment of the day on the last index of tables
        moodWeek[6] = mood;
        commentWeek[6] = comment;

    }
}
