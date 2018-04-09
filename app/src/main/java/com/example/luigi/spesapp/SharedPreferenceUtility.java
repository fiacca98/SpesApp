package com.example.luigi.spesapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by luigi on 09/04/2018.
 */

public class SharedPreferenceUtility {

    public static void setUserOnSharedPreferences(String username, String password, Activity a) {

        SharedPreferences sharedPref = a.getSharedPreferences("User", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("Username", username);
        editor.putString("Password", password);

        DateFormat df = new SimpleDateFormat("d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        Log.d("data", date);
        editor.putString("Token",date);

        editor.commit();
        Log.d("salvato", "salvato");
    }

    public static String readUserFromSharedPreferences(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String username = sharedPref.getString("Username",null);
        String password = sharedPref.getString("Password", null);
        String token = sharedPref.getString("Token", null);

        DateFormat df = new SimpleDateFormat("d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        if(token == null)
            return null;
        else {
            if (token.equals(date))
                return username;
            else
                return null;
        }
    }

}
