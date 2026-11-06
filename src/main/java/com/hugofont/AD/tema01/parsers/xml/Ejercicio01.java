package com.hugofont.AD.tema01.parsers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

public class Ejercicio01 {

    private Ejercicio01() { }

    public static Empleado[] parsearEmpleados(String rutaFichero) throws IOException,
            ParserConfigurationException, SAXException {
        /* Inicializamos a null el array de países */
        Empleado[] empleados = null;
        try (FileInputStream fis = new FileInputStream(rutaFichero)) {
            /* Obtenemos el DocumentBuilderFactory necesario para parsear mediante DOM */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            /* Obtenemos el DocumentBuilder necesario para parsear mediante DOM */
            DocumentBuilder builder = factory.newDocumentBuilder();
            /* Obtenemos el Document parseando mediante DOM */
            Document dom = builder.parse(fis);
            /* Eliminamos nodos de texto redundantes (como saltos de línea o espacios) */
            dom.getDocumentElement().normalize();
            /* Obtenemos la lista de nodos con el tag "empleado" */
            NodeList items = dom.getElementsByTagName("empleado");
            /* Array de empleados con tamaño igual al número de nodos de tipo empleado */
            empleados = new Empleado[items.getLength()];
            for (int i = 0; i < items.getLength(); i++) {
                Element empleadoEtiqueta = (Element) items.item(i);
                String id = empleadoEtiqueta.getAttribute("id");
                String nombre = empleadoEtiqueta.getElementsByTagName("nombre").item(0).getTextContent();
                String departamento = empleadoEtiqueta.getElementsByTagName("departamento").item(0).getTextContent();
                Element salarioElemento = (Element) empleadoEtiqueta.getElementsByTagName("salario").item(0);
                String moneda = salarioElemento.getAttribute("moneda");
                double salario = Double.parseDouble(salarioElemento.getTextContent());
                String fechaAlta = empleadoEtiqueta.getElementsByTagName("fechaAlta").item(0).getTextContent();
                LocalDate fecha = LocalDate.parse(fechaAlta);
                empleados[i] = new Empleado(id, nombre, departamento, moneda, salario, fecha);
            }
        }
        return empleados;
    }
}