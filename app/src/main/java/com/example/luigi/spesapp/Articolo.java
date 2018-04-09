package com.example.luigi.spesapp;

/**
 * Created by luigi on 09/04/2018.
 */

public class Articolo {
    private String nome;
    private String id;
    private int id_lista;
    private double quantita;

    public Articolo(String nome, String id, int id_lista, double quantita) {
        this.nome = nome;
        this.id = id;
        this.id_lista = id_lista;
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public int getId_lista() {
        return id_lista;
    }

    public double getQuantita() {
        return quantita;
    }
}
