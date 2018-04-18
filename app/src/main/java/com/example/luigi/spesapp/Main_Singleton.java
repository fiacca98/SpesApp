package com.example.luigi.spesapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luigi on 09/04/2018.
 */

public class Main_Singleton {

    private static Main_Singleton ourInstance = new Main_Singleton();
    private static User user;

    private List<Lista> liste = new ArrayList<Lista>();

    public static synchronized Main_Singleton getInstance() {
        return ourInstance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public List<Lista> getListe() {
        return this.liste;
    }

    public void addLista(Lista lista) {
        this.liste.add(lista);
    }

    public void resetListe() {
        this.liste.clear();
    }

    public void setLista() {

    }

    public void removeLocation(Lista lista) {
        this.liste.remove(liste);
    }

    public void addArticolo(int position, Articolo articolo) {
        this.liste.get(position).addArticolo(articolo);
    }
}
