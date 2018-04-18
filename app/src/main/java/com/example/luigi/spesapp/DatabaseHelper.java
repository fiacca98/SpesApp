package com.example.luigi.spesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giulia on 09/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myshoppingdb.db";
    private static final int DATABASE_VERSION = 1;


    public static final String DATABASE_CREATE_USER =
            "CREATE TABLE users(" +
                    UserDatabaseManager.KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    UserDatabaseManager.KEY_USERNAME + " TEXT, " +
                    UserDatabaseManager.KEY_NAME + " TEXT, " +
                    UserDatabaseManager.KEY_MAIL + " TEXT, " +
                    UserDatabaseManager.KEY_PASSWORD + " TEXT, " +
                    UserDatabaseManager.KEY_TUTORIAL + " BOOLEAN" +
                    ");";

    public static final String DATABASE_CREATE_LIST =
            "CREATE TABLE lists(" +
                    ListDatabaseManager.KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                    ListDatabaseManager.KEY_NAME + " TEXT, " +
                    ListDatabaseManager.KEY_ID_USER + " INTEGER" +
                    ");";

    public static final String DATABASE_CREATE_ITEM =
            "CREATE TABLE items(" +
                    ItemDatabaseManager.KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                    ItemDatabaseManager.KEY_NAME + " TEXT, " +
                    ItemDatabaseManager.KEY_ID_LIST + " INTEGER, " +
                    ItemDatabaseManager.KEY_VALUE + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_USER);
        db.execSQL(DATABASE_CREATE_LIST);
        db.execSQL(DATABASE_CREATE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserDatabaseManager.DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ItemDatabaseManager.DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ListDatabaseManager.DATABASE_TABLE);
        onCreate(db);
    }
}
