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

        for(int i = 1; i < 7; i++){//Rumbling between days (preferences index i become preference index i-1)
            moodWeek[i-1] = mPreferences.getInt(PREF_KEY_MOOD+i,-1);
            commentWeek[i-1] = mPreferences.getString(PREF_KEY_COMMENT+i, "");
        }

        System.out.println("Tableau Receiver");
        for(int i = 0; i < 7; i++){
            System.out.println("moodWeek à l'emplacement "+i+" = "+moodWeek[i]);
            System.out.println("commentWeek à l'emplacement "+i+" = "+commentWeek[i]);
        }

        for(int i = 0; i < 7; i++){//Put in preferences moods and comments of the 7 last days
            mPreferences.edit().putInt(PREF_KEY_MOOD+i, moodWeek[i]).apply();
            mPreferences.edit().putString(PREF_KEY_COMMENT+i, commentWeek[i]).apply();
        }

        //Remove the mood and comment of the day
        mPreferences.edit().remove(MainActivity.PREF_KEY_MOOD).apply();
        mPreferences.edit().remove(MainActivity.PREF_KEY_COMMENT).apply();
    }
}
