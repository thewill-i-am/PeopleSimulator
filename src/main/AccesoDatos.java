package main;


import main.entities.Persona;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class FileProcessor {
    protected String fileName;

    public FileProcessor(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String> processFile() throws FileNotFoundException {
        Scanner reader = openFile();
        ArrayList<String> result = readLines(reader);
        reader.close();
        return result;
    }

    protected Scanner openFile() throws FileNotFoundException {
        return new Scanner(new File(fileName));
    }

    protected abstract List readLines(Scanner reader);
}

public class AccesoDatos extends FileProcessor {

    public AccesoDatos(String fileName) {
        super(fileName);
    }

    @Override
    protected ArrayList<String> readLines(Scanner reader) {
        ArrayList<String> result = new ArrayList<String>();
        boolean skip = true;
        int i = 0;
        while (reader.hasNextLine()) {
            i++;
            String producto = reader.nextLine();
            if (!skip) {
                Producto currentProducto = getProductDataFromString(producto, i);
                Nodo nuevoNodo = new Nodo(currentProducto);
                result.agregar(nuevoNodo);
            } else {
                skip = false;
            }
        }
        return result;
    }

    private Persona getProductDataFromString(String currentData, int index) {
        Persona newPersona = new Persona();
        String[] data = currentData.split(",");
        newPersona.setNombre(data[0]);

        newProducto.setIndex(index);
        newProducto.setId(data[0]);
        newProducto.setNombre(data[1]);
        newProducto.setCategoria(data[2]);
        return newProducto;
    }
}