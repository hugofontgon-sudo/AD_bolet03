package com.hugofont.AD.tema01.parsers.xml;

import java.time.LocalDate;

public class Empleado {
    private final String id;
    private final String nombre;
    private final String departamento;
    private final String moneda;
    private final double salario;
    private final LocalDate fechaAlta;

    public Empleado(String id, String nombre, String departamento, String moneda, double salario, LocalDate fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.moneda = moneda;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getMoneda() {
        return moneda;
    }

    public double getSalario() {
        return salario;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Empleado empleado)) return false;

        return id.equals(empleado.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", departamento='" + departamento + '\'' +
                ", moneda='" + moneda + '\'' +
                ", salario=" + salario +
                ", fechaAlta=" + fechaAlta +
                '}';
    }
}