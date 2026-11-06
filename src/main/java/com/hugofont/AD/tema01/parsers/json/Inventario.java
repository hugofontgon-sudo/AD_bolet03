package com.hugofont.AD.tema01.parsers.json;

import java.util.Arrays;

public class Inventario {

    private final String id;
    private final String nombre;
    private final int stock;
    private final double precio;
    private final String[] tags;
    private final Ubicacion ubicacion;

    public Inventario(String id, String nombre, int stock, double precio, String[] tags, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.tags = tags;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public String[] getTags() {
        return tags;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", tags=" + Arrays.toString(tags) +
                ", ubicacion=" + ubicacion +
                '}';
    }
}