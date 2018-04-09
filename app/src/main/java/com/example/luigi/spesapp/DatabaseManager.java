package com.example.luigi.spesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ConcurrentModificationException;

/**
 * Created by Giulia on 09/04/2018.
 */

public class DatabaseManager {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Context context;

    public static final String DATABASE_TABLE = "user";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_MAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TUTORIAL = "tutorial";

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public DatabaseManager open() throws Exception {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private ContentValues createContentValues(String username, String email, String password, Boolean tutorial) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_MAIL, email);
        contentValues.put(KEY_PASSWORD, password);
        contentValues.put(KEY_TUTORIAL, tutorial);
        return contentValues;
    }

    public long createUser(User user) {
        ContentValues initialValues = createContentValues(user.getUsername(), user.getEmail(), user.getPassword(), user.getTutorial());
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public boolean updateUser(String username, User user) {
        ContentValues updateValues = createContentValues(user.getUsername(), user.getEmail(), user.getPassword(), user.getTutorial());
        return database.update(DATABASE_TABLE, updateValues, KEY_USERNAME + "=" + username, null) > 0;
    }

    public Cursor fetchAllUsers() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
}

