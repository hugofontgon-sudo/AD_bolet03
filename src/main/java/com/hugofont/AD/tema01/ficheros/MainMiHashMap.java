package com.hugofont.AD.tema01.ficheros;

public class MainMiHashMap {
    static void main() {
        MiHashMap map = new MiHashMap(256);
        map.put("Hello", "Hola");
        map.put("Table", "Mesa");

        System.out.println(map.get("Hello"));
        System.out.println(map.get("Table"));

    }
}