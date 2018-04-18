package com.example.luigi.spesapp;

/**
 * Created by luigi on 09/04/2018.
 */

public class Articolo {
    private String nome;
    private final int id;
    private int id_lista;
    private String quantita;
    private int buyed;

    public Articolo(String nome, int id, int id_lista, String quantita, int buyed) {
        this.nome = nome;
        this.id = id;
        this.id_lista = id_lista;
        this.quantita = quantita;
        this.buyed = buyed;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public int getId_lista() {
        return id_lista;
    }

    public String getQuantita() {
        return quantita;
    }

    public int getBuyed() {
        return buyed;
    }

    public void setBuyed(int buyed) {
        this.buyed = buyed;
    }
}
