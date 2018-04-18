package com.example.luigi.spesapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corsista on 16/04/2018.
 */

public class AdapterActivity extends ArrayAdapter<Articolo> {
    private final Context context;
    private List<Articolo> itemList= new ArrayList<Articolo>();

    //costruttore
    public AdapterActivity(Context context, List <Articolo> itemList) {

        super(context, R.layout.item_detail_layout, itemList);
        this.itemList = itemList;
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewOptimize(position, convertView, parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getViewOptimize(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_detail_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.nameHolder = (TextView) convertView.findViewById(R.id.foodName);
            viewHolder.imageHolder = (ImageView) convertView.findViewById(R.id.food);
            viewHolder.valueHolder = (TextView) convertView.findViewById(R.id.foodValue);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String quantity=itemList.get(position).getQuantita();
        viewHolder.nameHolder.setText(itemList.get(position).getNome());
        viewHolder.valueHolder.setText(String.valueOf(quantity));

       return convertView;
    }


    public class ViewHolder {
        private TextView nameHolder;
        private TextView valueHolder;
        private ImageView imageHolder;
    }


    public void setValues() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    public void updateItem(Context context) {


        ItemDatabaseManager itemDatabaseManager = new ItemDatabaseManager(context);
        itemDatabaseManager.open();
        Cursor cursor = itemDatabaseManager.getItemsByList(1);
        cursor.moveToFirst();
        int index = cursor.getCount();
        if (index > 0) {
            int i = 0;
             this.itemList.clear();
            do {
                Articolo item = new Articolo(cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_NAME)),
                        cursor.getInt(cursor.getColumnIndex(ItemDatabaseManager.KEY_ID)),
                        cursor.getInt(cursor.getColumnIndex(itemDatabaseManager.KEY_ID_LIST)),
                        cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_VALUE)));
                this.itemList.add(item);
                i++;
                cursor.moveToNext();
            } while (i < index);
        }

    }

}
