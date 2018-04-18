package com.example.luigi.spesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriele on 17/04/2018.
 */

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {
    UserDatabaseManager userDatabaseManager;
    ListDatabaseManager listDatabaseManager;
    ItemDatabaseManager itemDatabaseManager;
    int listID, userId;
    String name;
    List<Articolo> liste = new ArrayList<>();

    public ItemRecyclerAdapter(Context context) {
        this.updateList(context);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.foodName.setText(liste.get(position).getNome());
        holder.foodValue.setText(String.valueOf(liste.get(position).getQuantita()));
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodValue;
        View myView;
        Context context;

        public ItemViewHolder(View view) {
            super(view);
            myView = view;
            foodName = (TextView) view.findViewById(R.id.foodName);
            foodValue = (TextView) view.findViewById(R.id.foodValue);
            context = view.getContext();
        }
    }

    public void updateList(Context context) {
        ItemDatabaseManager itemDatabaseManager = new ItemDatabaseManager(context);
        String username = SharedPreferenceUtility.readUserFromSharedPreferences(context);
        userDatabaseManager = new UserDatabaseManager(context);
        userDatabaseManager.open();
        Cursor userCursor = userDatabaseManager.getUserId(username);
        userCursor.moveToFirst();
        this.userId = userCursor.getInt(userCursor.getColumnIndex(userDatabaseManager.KEY_ID));

        listDatabaseManager = new ListDatabaseManager(context);
        listDatabaseManager.open();
        Cursor listCursor = listDatabaseManager.getListsByUser(1);
        listCursor.moveToFirst();
        this.listID=listCursor.getInt(listCursor.getColumnIndex(listDatabaseManager.KEY_ID));


        itemDatabaseManager = new ItemDatabaseManager(context);
        itemDatabaseManager.open();
        Cursor cursor = itemDatabaseManager.getItemsByList(listID);
        cursor.moveToFirst();
        int index = cursor.getCount();
        if (index > 0) {
            int i = 0;
            this.liste.clear();
            do {
                Articolo item = new Articolo(cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_NAME)),
                        cursor.getInt(cursor.getColumnIndex(ItemDatabaseManager.KEY_ID)),
                        cursor.getInt(cursor.getColumnIndex(itemDatabaseManager.KEY_ID_LIST)),
                        cursor.getInt(cursor.getColumnIndex(itemDatabaseManager.KEY_VALUE)));
                this.liste.add(item);
                i++;
                cursor.moveToNext();
            } while (i < index);
        }


        }



    public int getListID(int position){
        return liste.get(position).getId_lista();
    }

    @Override
    public ItemRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch (MainActivity.mCurrentLayoutManagerType) {

            case GRID_LAYOUT_MANAGER:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_detail_layout, parent, false);
                break;
            case LINEAR_LAYOUT_MANAGER:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_detail_layout, parent, false);
                break;

            default:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_detail_layout, parent, false);
        }


        return new ItemViewHolder(itemView);
    }

}

