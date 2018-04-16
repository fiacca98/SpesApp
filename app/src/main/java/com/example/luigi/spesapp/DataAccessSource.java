package com.example.luigi.spesapp;

import android.content.Context;

import java.util.List;
import java.util.MissingFormatArgumentException;

/**
 * Created by corsista on 16/04/2018.
 */

public class DataAccessSource {

    public static List<Lista> getDataSourceItemList(Context context) {
        return Main_Singleton.getInstance().getListe();
    }

    public static void addItem(Lista lista, Context context){
        Main_Singleton.getInstance().getListe().add(lista);
    }
    public void removeItem(Lista città, Context context){
        Main_Singleton.getInstance().getListe().remove(città);
    }
    public static Lista getItemByPosition(Context context, int position){
        return Main_Singleton.getInstance().getListe().get(position);
    }
}
