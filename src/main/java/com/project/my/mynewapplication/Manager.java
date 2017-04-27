package com.project.my.mynewapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;



public class Manager {


    private static int theme_id = R.style.AppTheme;
    private static SharedPreferences.Editor preferencesEditor = null;


    static void createContext(Context context) {


        String PREF_NAME = "Preferences";
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        preferencesEditor = preferences.edit();
        preferencesEditor.apply();
        theme_id = preferences.getInt("theme_id", R.style.AppTheme);
    }


    static int getTheme() {
        return theme_id;
    }

    static void setTheme(int theme_id) {
        Manager.theme_id = theme_id;
        preferencesEditor.putInt("theme_id", theme_id);
        preferencesEditor.commit();
    }
}