package com.example.luigi.spesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriele on 17/04/2018.
 */

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    DetailInterface mCallBack;
    UserDatabaseManager userDatabaseManager;
    ListDatabaseManager listDatabaseManager;
    ItemDatabaseManager itemDatabaseManager;
    String name;
    List<Articolo> articoli = new ArrayList<>();
    boolean nascondi = false;


    public ItemRecyclerAdapter(Context context) {
        try {
            this.mCallBack = (DetailInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DetailInterface");
        }

        this.updateList(context);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.foodName.setText(articoli.get(position).getNome());
        holder.foodValue.setText(String.valueOf(articoli.get(position).getQuantita()));

        if (articoli.get(position).getBuyed() == 1) {
            holder.check.setVisibility(View.VISIBLE);
        }
        else{
            holder.check.setVisibility(View.INVISIBLE);
        }

        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(articoli.get(position).getBuyed() == 0) {
                    articoli.get(position).setBuyed(1);
                }
                else {
                    articoli.get(position).setBuyed(0);
                }

                Log.d("articolo", articoli.get(position).getNome() );
                ItemDatabaseManager itemDatabaseManager = new ItemDatabaseManager(holder.context);
                itemDatabaseManager.open();
                itemDatabaseManager.updateItem(articoli.get(position));
                updateList(holder.context);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return articoli.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodValue;
        View myView;
        ImageView check;
        View filtro;
        Context context;
        Articolo articolo;

        public ItemViewHolder(View view) {
            super(view);
            myView = view;
            foodName = (TextView) view.findViewById(R.id.foodName);
            foodValue = (TextView) view.findViewById(R.id.foodValue);
            check = (ImageView) view.findViewById(R.id.check);
            context = view.getContext();
        }
    }

    public void updateList(Context context) {

        int listId = this.mCallBack.getListId();

        ItemDatabaseManager itemDatabaseManager = new ItemDatabaseManager(context);
        itemDatabaseManager.open();
        Cursor cursor = itemDatabaseManager.getItemsByList(listId);
        cursor.moveToFirst();
        int index = cursor.getCount();
        if (index > 0) {
            int i = 0;
            this.articoli.clear();
            do {
                Articolo item = new Articolo(cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_NAME)),
                        cursor.getInt(cursor.getColumnIndex(ItemDatabaseManager.KEY_ID)),
                        cursor.getInt(cursor.getColumnIndex(itemDatabaseManager.KEY_ID_LIST)),
                        cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_VALUE)),
                        cursor.getInt(cursor.getColumnIndex(itemDatabaseManager.KEY_BUYED)));
                if(nascondi == true && item.getBuyed() == 1){
                    //articolo comprato
                }
                else {
                    this.articoli.add(item);
                }
                i++;
                cursor.moveToNext();
            } while (i < index);
        }
        else {
            this.articoli.clear();
        }


        }



    public int getListID(int position){
        return articoli.get(position).getId_lista();
    }

    @Override
    public ItemRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

}

