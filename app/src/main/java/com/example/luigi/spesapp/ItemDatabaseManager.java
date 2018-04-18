package com.example.luigi.spesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by corsista on 11/04/2018.
 */

public class ItemDatabaseManager {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Context context;

    public static final String DATABASE_TABLE = "items";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_LIST = "id_list";
    public static final String KEY_VALUE = "value";

    public ItemDatabaseManager(Context context) {
        this.context = context;
    }

    public ItemDatabaseManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private ContentValues createContentValues(String name, int id_list, double value) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_ID_LIST, id_list);
        contentValues.put(KEY_VALUE, value);
        return contentValues;
    }

    public long createItem(String name, int id_list, int value) {
        ContentValues initialValues = createContentValues(name, id_list, value);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public boolean updateItem(Articolo articolo) {
        ContentValues updateValues = createContentValues(articolo.getNome(),articolo.getId_lista(),articolo.getQuantita());
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + articolo.getId(), null) > 0;
    }

    public Cursor fetchAllItems() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    public Cursor readItem(int id) {
        String[] columns = new String[]{"*"};
        return database.query(DATABASE_TABLE, columns, "_id = '"+id+"'", null, null, null, null);
    }

    public Cursor getItemsByList(int id_list) {
        String[] columns = new String[]{"*"};
        return database.query(DATABASE_TABLE, columns, "id_list = '"+id_list+"'", null, null, null, null);
    }
}

