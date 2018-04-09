package com.example.luigi.spesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import static com.example.luigi.spesapp.DatabaseManager.KEY_TUTORIAL;

/**
 * Created by Giulia on 09/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myshoppingdb.db";
    private static final int DATABASE_VERSION = 1;


    public static final String DATABASE_CREATE =
            "CREATE TABLE users(" +
                    DatabaseManager.KEY_USERNAME + " TEXT PRIMARY KEY, " +
                    DatabaseManager.KEY_NAME + " TEXT, " +
                    DatabaseManager.KEY_MAIL + " TEXT, " +
                    DatabaseManager.KEY_PASSWORD + " TEXT, " +
                    DatabaseManager.KEY_TUTORIAL + " BOOLEAN" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager.DATABASE_TABLE);
        onCreate(db);
    }
}
