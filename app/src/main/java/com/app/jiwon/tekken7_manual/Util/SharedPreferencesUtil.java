package com.app.jiwon.tekken7_manual.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    public static boolean getBooleanPreferences(String name, String key, boolean second, Context context) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        return pref.getBoolean(key, second);
    }

    public static int getIntPreferences(String name, String key, int second, Context context) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        return pref.getInt(key, second);
    }

    public static void saveBooleanPreferences(String name, String key, boolean value, Context context) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void saveIntPreferences(String name, String key, int value, Context context) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void removePreferences(String name, String key, Context context) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removeAllPreferences(String name, Context context) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }
}
