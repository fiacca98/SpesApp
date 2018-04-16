package com.example.luigi.spesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Giulia on 09/04/2018.
 */

public class UserDatabaseManager {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Context context;

    public static final String DATABASE_TABLE = "users";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TUTORIAL = "tutorial";

    public UserDatabaseManager(Context context) {
        this.context = context;
    }

    public UserDatabaseManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        //database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private ContentValues createContentValues(String username, String name, String email, String password, Boolean tutorial) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_MAIL, email);
        contentValues.put(KEY_PASSWORD, password);
        contentValues.put(KEY_TUTORIAL, tutorial);
        return contentValues;
    }

    public long createUser(String username, String name, String email, String password, Boolean tutorial) {
        ContentValues initialValues = createContentValues(username, name, email, password, tutorial);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public boolean updateUser(String username, User user) {
        ContentValues updateValues = createContentValues(user.getUsername(), user.getName(), user.getEmail(), user.getPassword(), user.getTutorial());
        return database.update(DATABASE_TABLE, updateValues, KEY_USERNAME + "=" + username, null) > 0;
    }

    public Cursor fetchAllUsers() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    public Cursor readUser(String username) {
        String[] columns = new String[]{KEY_USERNAME};
        return database.query(DATABASE_TABLE, columns, "username = '"+username+"'", null, null, null, null);
    }
}

