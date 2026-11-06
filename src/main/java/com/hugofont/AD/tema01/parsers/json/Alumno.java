package com.hugofont.AD.tema01.parsers.json;

import java.time.LocalDate;
import java.util.Arrays;

public class Alumno {
    private final String id;
    private final String nombre;
    private final boolean matriculado;
    private final LocalDate fechaNacimiento;
    private final Nota[] notas;

    public Alumno(String id, String nombre, boolean matriculado, LocalDate fechaNacimiento, Nota[] notas) {
        this.id = id;
        this.nombre = nombre;
        this.matriculado = matriculado;
        this.fechaNacimiento = fechaNacimiento;
        this.notas = notas;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isMatriculado() {
        return matriculado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Nota[] getNotas() {
        return notas;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Alumno alumno)) return false;

        return id.equals(alumno.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", matriculado=" + matriculado +
                ", fechaNacimiento=" + fechaNacimiento +
                ", notas=" + Arrays.toString(notas) +
                '}';
    }
}