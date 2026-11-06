package com.hugofont.AD.tema01.parsers.json;

import java.util.Arrays;
import java.util.Comparator;

public class NotasAlumno {
    //a) Alumno y la asignatura con nota más alta
    public void mostrarNotaMasAltaPorAlumno(Alumno[] alumnos) {
        System.out.println("\nEjercicio 6 a) Alumno y la asignatura con la nota más alta");
        if (alumnos == null) return;

        for (Alumno alumno : alumnos) {
            Nota[] notas = alumno.getNotas();
            if (notas != null && notas.length > 0) {
                Nota notaMax = notas[0];
                for (int i = 1; i < notas.length; i++) {
                    if (notas[i].getNota() > notaMax.getNota()) {
                        notaMax = notas[i];
                    }
                }
                System.out.println("Alumno: " + alumno.getNombre() +
                        " | Asignatura: " + notaMax.getAsignatura() +
                        " | Nota: " + notaMax.getNota());
            } else {
                System.out.println("Alumno: " + alumno.getNombre() + " no tiene notas registradas.");
            }
        }
    }

    // --- b) Alumno con la nota media más alta ---
    public void mostrarAlumnoMediaMasAlta(Alumno[] alumnos) {
        System.out.println("\nEjercicio 6 b) Alumno con la nota media más alta");
        if (alumnos == null || alumnos.length == 0) return;

        Alumno mejorAlumno = null;
        double mejorMedia = -1.0;

        for (Alumno alumno : alumnos) {
            Nota[] notas = alumno.getNotas();
            if (notas != null && notas.length > 0) {
                double suma = 0;
                for (int i = 0; i < notas.length; i++) {
                    suma += notas[i].getNota();
                }
                double media = suma / notas.length;

                if (media > mejorMedia) {
                    mejorMedia = media;
                    mejorAlumno = alumno;
                }
            }
        }

        if (mejorAlumno != null) {
            System.out.printf("El alumno con la nota media más alta es: %s (Media: %.2f)%n",
                    mejorAlumno.getNombre(), mejorMedia);
        } else {
            System.out.println("No se han podido calcular medias.");
        }
    }
}
