package com.example.luigi.spesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luigi.spesapp.R;
import com.example.luigi.spesapp.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luigi on 09/04/2018.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    List<Lista> liste = new ArrayList<Lista>();

    public MyRecyclerAdapter(Context context) {
        Main_Singleton.getInstance().addLista(this.mockListe("spesa venerdi"));
        Main_Singleton.getInstance().addLista(this.mockListe("spesa pasquetta"));
        Main_Singleton.getInstance().addLista(this.mockListe("spesa sabato"));
        this.liste = Main_Singleton.getInstance().getListe();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView infoText;
        Context context;

        public MyViewHolder(View view){
            super(view);
            infoText = (TextView) view.findViewById(R.id.info_text);
            context = view.getContext();
        }
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch(MainActivity.mCurrentLayoutManagerType){

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
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {
        holder.infoText.setText(liste.get(position).getNome());
    }

    public Lista mockListe(String nome){
        Lista lista = new Lista(1,nome,1);
        return lista;
    }
}
