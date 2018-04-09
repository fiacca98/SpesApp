package com.example.luigi.spesapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luigi on 09/04/2018.
 */

public class Lista {

    private int id_lista;
    private String nome;
    private int id_utente;
    private List<Articolo> articoli = new ArrayList<Articolo>();

    public Lista(int id_lista, String nome, int id_utente) {
        this.id_lista = id_lista;
        this.nome = nome;
        this.id_utente = id_utente;
    }

    public int getId_lista() {
        return id_lista;
    }

    public String getNome() {
        return nome;
    }

    public int getId_utente() {
        return id_utente;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void addArticolo(Articolo articolo) {
        this.articoli.add(articolo);
    }
}
