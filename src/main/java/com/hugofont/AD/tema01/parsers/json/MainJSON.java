package com.hugofont.AD.tema01.parsers.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.xpath.XPathResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

public class MainJSON {

    static void main() {
        MainJSON mainJSON = new MainJSON();
        final String ARCHIVO = "alumnos.json";

        try {
            Alumno[] alumos = mainJSON.parsearAlumnos(ARCHIVO);
            System.out.println("5. LISTADOD DE ALUMNOS");
            for (Alumno alumo : alumos) {
                System.out.println(alumo);
            }

            //Ejercicio 6
            mainJSON.mostrarNotaMasAltaPorAlumno(alumos);
            mainJSON.mostrarAlumnoMediaMasAlta(alumos);

        } catch (IOException e) {
            System.err.println("No se ha podido leer el archivo " + ARCHIVO);
        }


        //Ejercicio 7
        final String ARCHIVO_INVENTARIO = "inventario.json";
        mainJSON.consultarUbicacionProducto(ARCHIVO_INVENTARIO);


        //Ejercicio 8
        final String ARCHIVO_PELICULAS = "peliculas.json";

        try {
            Pelicula[] peliculas = mainJSON.parsearPeliculas(ARCHIVO_PELICULAS);

            mainJSON.mostrarPeliculasPorMedia(peliculas);
            mainJSON.mostrarGenerosDistintos(peliculas);

        } catch (IOException e) {
            System.err.println("No se ha podido leer el archivo " + ARCHIVO_PELICULAS);
        }

    }


    //Ejercicio 5
    public Alumno[] parsearAlumnos(String fichero) throws IOException {
        Alumno[] alumnos = null;
        StringBuilder sb = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream("/" + fichero);
             BufferedReader br = new BufferedReader(new InputStreamReader(is,
                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONArray jsonArray = new JSONArray(tokener);
            alumnos = new Alumno[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectAlumno = jsonArray.getJSONObject(i);
                String id = jsonObjectAlumno.getString("id");
                String nombre = jsonObjectAlumno.getString("nombre");
                boolean matriculado = jsonObjectAlumno.getBoolean("matriculado");
                String fechaNacimientoStr = jsonObjectAlumno.getString("fechaNacimiento");
                LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                JSONArray jsonArrayNotas = jsonObjectAlumno.getJSONArray("notas");
                Nota[] notas = new Nota[jsonArrayNotas.length()];
                for (int j = 0; j < jsonArrayNotas.length(); j++) {
                    JSONObject jsonObjectNota = jsonArrayNotas.getJSONObject(j);
                    String asignatura = jsonObjectNota.getString("asignatura");
                    double nota = jsonObjectNota.getDouble("nota");
                    notas[j] = new Nota(asignatura, nota);
                }
                alumnos[i] = new Alumno(id, nombre, matriculado, fechaNacimiento, notas);

            }

        }
        return alumnos;
    }


    //Ejercicio 6
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

    //b) Alumno con la nota media más alta
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


    //Ejercicio 7
    public Inventario[] parsearInventario(String fichero) throws IOException {
        Inventario[] productos = null;
        StringBuilder sb = new StringBuilder();

        try (InputStream is = getClass().getResourceAsStream("/" + fichero);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONObject jsonObjectRaiz = new JSONObject(tokener);

            //Obtenemos el array "productos"
            JSONArray jsonArrayProductos = jsonObjectRaiz.getJSONArray("productos");
            productos = new Inventario[jsonArrayProductos.length()];

            for (int i = 0; i < jsonArrayProductos.length(); i++) {
                JSONObject jsonObjectProducto = jsonArrayProductos.getJSONObject(i);

                String id = jsonObjectProducto.getString("id");
                String nombre = jsonObjectProducto.getString("nombre");
                int stock = jsonObjectProducto.getInt("stock");
                double precio = jsonObjectProducto.getDouble("precio");

                //Parsear el array de tags
                JSONArray jsonArrayTags = jsonObjectProducto.getJSONArray("tags");
                String[] tags = new String[jsonArrayTags.length()];
                for (int j = 0; j < jsonArrayTags.length(); j++) {
                    tags[j] = jsonArrayTags.getString(j);
                }

                //Parsear el objeto "ubicacion"
                JSONObject jsonObjectUbicacion = jsonObjectProducto.getJSONObject("ubicacion");
                int pasillo = jsonObjectUbicacion.getInt("pasillo");
                String estante = jsonObjectUbicacion.getString("estante");
                Ubicacion ubicacion = new Ubicacion(pasillo, estante);

                // Crear el objeto Producto
                productos[i] = new Inventario(id, nombre, stock, precio, tags, ubicacion);
            }
        }
        return productos;
    }

    //Método para buscar y mostrar la ubicación del producto indicado por el usuario ---
    public void consultarUbicacionProducto(String fichero) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Llamamos al método para obtener el array
            Inventario[] productos = parsearInventario(fichero);

            System.out.print("\nIntroduce el nombre del producto que deseas buscar: ");
            String nombreBuscado = scanner.nextLine().trim();

            boolean encontrado = false;
            for (Inventario p : productos) {
                // Comparamos el nombre ignorando mayúsculas y minúsculas
                if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                    System.out.printf("El producto '%s' está en la ubicación -> %s%n",
                            p.getNombre(), p.getUbicacion());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se ha encontrado ningún producto con ese nombre en el inventario.");
            }

        } catch (IOException e) {
            System.err.println("No se ha podido leer el archivo " + fichero);
        }
    }


    //Ejercicio 8
    // Método para parsear el JSON de películas
    public Pelicula[] parsearPeliculas(String fichero) throws IOException {
        Pelicula[] peliculas = null;
        StringBuilder sb = new StringBuilder();

        try (InputStream is = getClass().getResourceAsStream("/" + fichero);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONArray jsonArray = new JSONArray(tokener);
            peliculas = new Pelicula[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectPelicula = jsonArray.getJSONObject(i);

                String titulo = jsonObjectPelicula.getString("titulo");
                double imdb = jsonObjectPelicula.getDouble("imdb");
                double rt = jsonObjectPelicula.getDouble("rt");

                JSONArray jsonArrayGeneros = jsonObjectPelicula.getJSONArray("generos");
                String[] generos = new String[jsonArrayGeneros.length()];
                for (int j = 0; j < jsonArrayGeneros.length(); j++) {
                    generos[j] = jsonArrayGeneros.getString(j);
                }

                peliculas[i] = new Pelicula(titulo, imdb, rt, generos);
            }
        }
        return peliculas;
    }

    //8 a) Películas ordenadas por puntuación media (Descendente)
    public void mostrarPeliculasPorMedia(Pelicula[] peliculas) {
        System.out.println("\n8 a) Películas ordenadas por puntuación media (Descendente)");
        if (peliculas == null || peliculas.length == 0) return;

        // Clonamos el array para no alterar el orden original
        Pelicula[] copia = peliculas.clone();

        // Ordenamos en orden descendiente según la puntuación media
        Arrays.sort(copia, (p1, p2) -> Double.compare(p2.getPuntuacionMedia(), p1.getPuntuacionMedia()));

        for (Pelicula p : copia) {
            System.out.printf("Película: %-25s | Media: %.2f (IMDb: %.1f, RT norm.: %.1f)%n",
                    p.getTitulo(), p.getPuntuacionMedia(), p.getImdb(), p.getRt() / 10.0);
        }
    }

    //8 b) Géneros distintos ordenados alfabéticamente
    public void mostrarGenerosDistintos(Pelicula[] peliculas) {
        System.out.println("\n8 b) Géneros distintos ordenados alfabéticamente");
        if (peliculas == null || peliculas.length == 0) return;

        // TreeSet almacena elementos únicos y los ordena automáticamente alfabéticamente
        Set<String> generosUnicos = new TreeSet<>();

        for (Pelicula p : peliculas) {
            String[] generos = p.getGeneros();
            if (generos != null) {
                for (String g : generos) {
                    generosUnicos.add(g);
                }
            }
        }
    }
}