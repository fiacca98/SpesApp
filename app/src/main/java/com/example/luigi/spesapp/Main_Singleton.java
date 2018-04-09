package com.example.luigi.spesapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luigi on 09/04/2018.
 */

public class Main_Singleton {

    private static Main_Singleton ourInstance = new Main_Singleton();

    private List<Lista> liste = new ArrayList<Lista>();

    public static synchronized Main_Singleton getInstance() {
        return ourInstance;
    }

    public List<Lista> getListe(){
        return this.liste;
    }

    public void addLista(Lista lista){
        this.liste.add(lista);
    }

    public void removeLocation(Lista lista){
        this.liste.remove(liste);
    }
    public void addArticolo(int position, Articolo articolo) {
        this.liste.get(position).addArticolo(articolo);
    }
}
