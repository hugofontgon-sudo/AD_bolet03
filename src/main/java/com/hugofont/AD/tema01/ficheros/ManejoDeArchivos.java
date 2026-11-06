package com.hugofont.AD.tema01.ficheros;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class ManejoDeArchivos {

    public ManejoDeArchivos() {
        // Ejemplos ficheros de texto
        // Sin buffer
        String origen = "/home/ggascon/pruebas/hola.txt";
        String destino = "/home/ggascon/pruebas/hola2.txt";
        ejemploCopiarFicheroTextoSinBuffer(origen, destino);

        // Con buffer
        ejemploCopiarFicheroTextoConBuffer(origen, destino);

        //Ejemplos ficheros de texto y binarios
        // Sin buffer
        String respuesta = "";
        Scanner sc = new Scanner(System.in);
        origen = "/etc/passwd";
        destino =  "/home/ggascon/pruebas/usuarios";
        do {
            try {
                boolean sobreescribir = respuesta.equals("s");
                ejemploCopiarFicheroBinarioSinBuffer(origen, destino, sobreescribir);
            } catch (FileNotFoundException fnfe) {
                System.out.println("El fichero origen no existe");
            } catch (FileAlreadyExistsException faee) {
                System.out.println("El fichero destino ya existe");
                System.out.println("¿Deseas sobreescribirlo (s/n)?");
                respuesta = sc.nextLine();
            }
        } while (respuesta.equals("s"));
    }

    public boolean ejemploCopiarFicheroBinarioSinBuffer(String origen, String destino)
            throws FileNotFoundException, FileAlreadyExistsException {
        return ejemploCopiarFicheroBinarioSinBuffer(origen, destino, false);
    }

    public boolean ejemploCopiarFicheroBinarioSinBuffer(String origen, String destino, boolean sobreescribir)
            throws FileNotFoundException, FileAlreadyExistsException {
        File fileOrigen = new File(origen);
        if (!fileOrigen.exists()) {
            throw new FileNotFoundException("El fichero " + origen + " no existe");
        }
        File fileDestino = new File(destino);
        if (!sobreescribir && fileDestino.exists()) {
            throw new FileAlreadyExistsException("El destino " + destino + " ya existe");
        }
        try (
                FileInputStream fis = new FileInputStream(fileOrigen);
                FileOutputStream fos = new FileOutputStream(fileDestino);
        ) {
            int dato;
            while ((dato = fis.read()) != -1) {
                fos.write(dato);
            }
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    public boolean ejemploCopiarFicheroBinarioConBuffer(String origen, String destino, boolean sobreescribir)
            throws FileNotFoundException, FileAlreadyExistsException {
        File fileOrigen = new File(origen);
        if (!fileOrigen.exists()) {
            throw new FileNotFoundException("El fichero " + origen + " no existe");
        }
        File fileDestino = new File(destino);
        if (!sobreescribir && fileDestino.exists()) {
            throw new FileAlreadyExistsException("El destino " + destino + " ya existe");
        }
        try (
                FileInputStream fis = new FileInputStream(fileOrigen);
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileOutputStream fos = new FileOutputStream(fileDestino);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int dato;
            while ((dato = bis.read()) != -1) {
                bos.write(dato);
            }
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    public void ejemploCopiarFicheroTextoSinBuffer(String origen, String destino) {
        File ficheroOrigen = new File(origen);
        File ficheroDestino = new File(destino);
        if (ficheroOrigen.isFile()) {
            try (
                    FileReader fileReader = new FileReader(ficheroOrigen);
                    FileWriter fileWriter = new FileWriter(ficheroDestino);
            ) {
                int caracter;
                while ((caracter = fileReader.read()) != -1) {
                    fileWriter.write(caracter);
                }
                IO.println("Fin del fichero");
            } catch (IOException ioe) {
                IO.println(ioe.getMessage());
            }
        } else {
            IO.println("El fichero no existe");
        }
    }

    public void ejemploCopiarFicheroTextoConBuffer(String origen, String destino) {
        File ficheroOrigen = new File(origen);
        File ficheroDestino = new File(destino);
        if (ficheroOrigen.isFile()) {
            try (
                    FileReader fileReader = new FileReader(ficheroOrigen);
                    BufferedReader br = new BufferedReader(fileReader);
                    FileWriter fileWriter = new FileWriter(ficheroDestino);
                    BufferedWriter bw = new BufferedWriter(fileWriter);
            ) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    bw.write(linea);
                    bw.newLine();
                }
                IO.println("Fin del fichero");
            } catch (IOException ioe) {
                IO.println(ioe.getMessage());
            }
        } else {
            IO.println("El fichero no existe");
        }
    }
}