package com.hugofont.AD.tema01.parsers.json;

public class Nota {
    private final String asignatura;
    private final double nota;

    public Nota(String asignatura, double nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Nota nota1)) return false;

        return Double.compare(nota, nota1.nota) == 0 && asignatura.equals(nota1.asignatura);
    }

    @Override
    public int hashCode() {
        int result = asignatura.hashCode();
        result = 31 * result + Double.hashCode(nota);
        return result;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "asignatura='" + asignatura + '\'' +
                ", nota=" + nota +
                '}';
    }
}