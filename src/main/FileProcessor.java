package main;

import main.entities.Persona;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileProcessor {
    protected String fileName;

    public FileProcessor(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Persona> processFile() throws FileNotFoundException {
        Scanner reader = openFile();
        ArrayList<Persona> result = readLines(reader);
        reader.close();
        return result;
    }

    protected Scanner openFile() throws FileNotFoundException {
        return new Scanner(new File(fileName));
    }

    protected abstract ArrayList<Persona> readLines(Scanner reader);
}