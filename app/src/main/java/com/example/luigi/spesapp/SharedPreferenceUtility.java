package com.example.luigi.spesapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

import static java.lang.String.valueOf;

/**
 * Created by luigi on 09/04/2018.
 */

public class SharedPreferenceUtility {

    public static void setUserOnSharedPreferences(String username, Activity a) {

        SharedPreferences sharedPref = a.getSharedPreferences("User", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("Username", username);


        editor.putLong("Token",System.currentTimeMillis());

        editor.commit();
        Log.d("salvato", "salvato");
    }

    public static String readUserFromSharedPreferences(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String username = sharedPref.getString("Username",null);
        long token = sharedPref.getLong("Token", 0);

        Calendar calendar = Calendar.getInstance();

        Calendar currCalendar = Calendar.getInstance();

        calendar.setTimeInMillis(token);
        currCalendar.setTimeInMillis(System.currentTimeMillis());
        if(calendar.get(Calendar.DAY_OF_MONTH) == currCalendar.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.MONTH) == currCalendar.get(Calendar.MONTH)
                && calendar.get(Calendar.YEAR) == currCalendar.get(Calendar.YEAR))

            return username;
        else
            return null;

    }

}
