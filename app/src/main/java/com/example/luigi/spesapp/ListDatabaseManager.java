package com.example.luigi.spesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by luigi on 11/04/2018.
 */

public class ListDatabaseManager {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Context context;

    public static final String DATABASE_TABLE = "lists";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_USER = "id_user";

    public ListDatabaseManager(Context context) {
        this.context = context;
    }

    public ListDatabaseManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private ContentValues createContentValues(String name, int id_user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_ID_USER, id_user);
        return contentValues;
    }

    public long createList(String name, int id_user) {
        ContentValues initialValues = createContentValues(name, id_user);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public boolean updateList(Lista lista) {
        ContentValues updateValues = createContentValues(lista.getNome(),lista.getId_utente());
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + lista.getId_lista(), null) > 0;
    }

    public Cursor fetchAllLists() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    public Cursor readList(int id) {
        String[] columns = new String[]{"*"};
        return database.query(DATABASE_TABLE, columns, "id = '"+id+"'", null, null, null, null);
    }

    public Cursor getListsByUser(int id_user){
        String[] columns = new String[] {"*"};
        return database.query(DATABASE_TABLE, columns, KEY_ID_USER+" = "+id_user, null, null, null, null);
    }
}
