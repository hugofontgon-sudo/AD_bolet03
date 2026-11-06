package com.hugofont.AD.tema01;

import com.hugofont.AD.tema01.ficheros.ManejoDeArchivos;

public class Main {
    static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Argumentos incorrectos");
            System.err.println("Uso: Main <fichero>");
            System.exit(1);
        }


        // Clases básicos sobre manejo de archivos
        new ManejoDeArchivos();


    }
}