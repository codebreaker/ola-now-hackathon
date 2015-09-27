package com.scriptedpapers.olanow.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mahes on 23/6/15.
 */
public class PrefManager {

    public static final String ANIMATION_PREFERENCES = "ola.preference" ;

    private SharedPreferences sharedPreferences = null;

    public void init(Context ctxt){
        sharedPreferences = ctxt.getSharedPreferences(ANIMATION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setAnimValue(Context ctxt, String key, int value) {
        sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(ctxt);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(key, value);
        e.apply();
    }

    public int getAnimValue(Context ctxt, String key) {
        sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(ctxt);
        return sharedPreferences.getInt(key, 0);
    }
}
