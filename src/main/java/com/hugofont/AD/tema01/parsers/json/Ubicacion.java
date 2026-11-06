package com.hugofont.AD.tema01.parsers.json;

public class Ubicacion {
    private final int pasillo;
    private final String estante;

    public Ubicacion(int pasillo, String estante) {
        this.pasillo = pasillo;
        this.estante = estante;
    }

    public int getPasillo() { return pasillo; }
    public String getEstante() { return estante; }

    @Override
    public String toString() {
        return "Pasillo " + pasillo + ", Estante " + estante;
    }
}