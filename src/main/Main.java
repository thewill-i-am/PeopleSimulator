package main;

import main.entities.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AccesoDatos accesoDatos = new AccesoDatos("src/main/datos/players_15.csv");
        ArrayList<Persona> personas =  accesoDatos.processFile();
        accesoDatos.guardarDatos(personas);
        System.out.println(" ------> Datos Salvados <------- ");
        System.out.println(" ------> Ahora tienes que ejecutar el Main2 <------- ");
    }
}
