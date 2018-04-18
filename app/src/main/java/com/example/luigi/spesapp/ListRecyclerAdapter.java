package com.example.luigi.spesapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luigi on 09/04/2018.
 */

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.MyViewHolder> {

    int listID;

    List<Lista> liste = new ArrayList<Lista>();
    ListDatabaseManager listDatabaseManager;
    UserDatabaseManager userDatabaseManager;
    int userId;

    public ListRecyclerAdapter(Context context) {
        this.updateList(context);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView infoText;
        Context context;
        View myView;
        int position;
        int id;
        ListDatabaseManager listDatabaseManager;
        public MyViewHolder(View view) {
            super(view);
            myView = view;
            infoText = (TextView) view.findViewById(R.id.info_text);
            context = view.getContext();
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context,ListDetailActivity.class).putExtra("id",id).putExtra("nome",infoText.getText()));

        }
    }

    public void updateList(Context context) {
        String username = SharedPreferenceUtility.readUserFromSharedPreferences(context);
        userDatabaseManager = new UserDatabaseManager(context);
        userDatabaseManager.open();
        Cursor userCursor = userDatabaseManager.getUserId(username);
        userCursor.moveToFirst();
        this.userId = userCursor.getInt(userCursor.getColumnIndex(userDatabaseManager.KEY_ID));

        listDatabaseManager = new ListDatabaseManager(context);
        listDatabaseManager.open();
        Cursor cursor = listDatabaseManager.getListsByUser(this.userId);
        cursor.moveToFirst();
        int index = cursor.getCount();
        if (index > 0) {
            int i = 0;
            this.liste.clear();
            do {
                Lista lista = new Lista(cursor.getInt(cursor.getColumnIndex(listDatabaseManager.KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(listDatabaseManager.KEY_NAME)),
                        cursor.getInt(cursor.getColumnIndex(listDatabaseManager.KEY_ID_USER)));
                this.liste.add(lista);
                i++;
                cursor.moveToNext();
            } while (i < index);
        }
        else{
            this.liste.clear();
        }
    }

    public int getListID(int position){
        return liste.get(position).getId_lista();
    }

    @Override
    public ListRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch (MainActivity.mCurrentLayoutManagerType) {

            case GRID_LAYOUT_MANAGER:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_grid_layout, parent, false);
                break;
            case LINEAR_LAYOUT_MANAGER:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_layout, parent, false);
                break;

            default:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_layout, parent, false);
        }


        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    @Override
    public void onBindViewHolder(ListRecyclerAdapter.MyViewHolder holder, int position) {
        holder.position = position;
        holder.id = liste.get(position).getId_lista();
        holder.infoText.setText(liste.get(position).getNome());

        int id = liste.get(position).getId_lista();
        holder.myView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.context);
                final TextView input = new TextView(holder.context);
                input.setText("Sei sicuro di voler eliminare questa lista?");
                builder.setTitle("ELIMINA LISTA");
                builder.setView(input);
                builder.setPositiveButton("CANCELLA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListDatabaseManager listDatabaseManager = new ListDatabaseManager(holder.context);
                        listDatabaseManager.open();
                        int cursor = listDatabaseManager.deleteList(id);
                        listDatabaseManager.close();
                        Log.d("delete", "ho cancellato " + cursor + " linee");
                        updateList(holder.context);
                        notifyItemRemoved(position);
                        dialog.cancel();

                    }
                });
                builder.setNegativeButton("ANNULLA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;

            }
        });

    }

}
