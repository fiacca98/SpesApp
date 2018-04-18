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
    public static final String KEY_ID = "ID";
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
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private ContentValues createContentValues(String username, String name, String email, String password, int tutorial) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_MAIL, email);
        contentValues.put(KEY_PASSWORD, password);
        contentValues.put(KEY_TUTORIAL, tutorial);
        return contentValues;
    }

    public long createUser(String username, String name, String email, String password, int tutorial) {
        ContentValues initialValues = createContentValues(username, name, email, password, tutorial);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public boolean updateUser(int id, User user) {
        ContentValues updateValues = createContentValues(user.getUsername(), user.getName(), user.getEmail(), user.getPassword(), user.getTutorial());
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + " = " + id, null) > 0;
    }

    public Cursor fetchAllUsers() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }

    public Cursor readUser(String username) {
        String[] columns = new String[]{"*"};
        return database.query(DATABASE_TABLE, columns, "username = '" + username + "'", null, null, null, null);
    }

    public Cursor readUserById(int id) {
        String[] columns = new String[]{"*"};
        return database.query(DATABASE_TABLE, columns, "ID = "+id, null, null, null, null);
    }

    public Cursor getUserId(String username) {
        String[] columns = new String[]{KEY_ID};
        return database.query(DATABASE_TABLE, columns, "username = '" + username + "'", null, null, null, null);
    }

    public boolean setTutorial(int id){
        Cursor cursor = this.readUserById(id);
        cursor.moveToFirst(); //String username, String name, String email, String password, int tutorial
        User user = new User(cursor.getString(cursor.getColumnIndex(this.KEY_USERNAME)),
                cursor.getString(cursor.getColumnIndex(this.KEY_NAME)),
                cursor.getString(cursor.getColumnIndex(this.KEY_MAIL)),
                cursor.getString(cursor.getColumnIndex(this.KEY_PASSWORD)),
                cursor.getInt(cursor.getColumnIndex(this.KEY_TUTORIAL)));
        return this.updateUser(id, user);
    }
}

