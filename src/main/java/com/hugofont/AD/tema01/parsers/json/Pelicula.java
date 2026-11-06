package com.hugofont.AD.tema01.parsers.json;

import java.util.Arrays;

public class Pelicula {
    private final String titulo;
    private final double imdb;
    private final double rt;
    private final String[] generos;

    public Pelicula(String titulo, double imdb, double rt, String[] generos) {
        this.titulo = titulo;
        this.imdb = imdb;
        this.rt = rt;
        this.generos = generos;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getImdb() {
        return imdb;
    }

    public double getRt() {
        return rt;
    }

    public String[] getGeneros() {
        return generos;
    }

    // Método para calcular la puntuación media:
    // Normaliza RT (escala 1-100 -> 1-10) y hace la media con IMDb
    public double getPuntuacionMedia() {
        double rtNormalizada = rt / 10.0;
        return (imdb + rtNormalizada) / 2.0;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", imdb=" + imdb +
                ", rt=" + rt +
                ", media=" + getPuntuacionMedia() +
                ", generos=" + Arrays.toString(generos) +
                '}';
    }
}