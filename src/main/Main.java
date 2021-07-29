package main;

import main.entities.Persona;
import main.logicadatos.AccesoDatos;

import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AccesoDatos accesoDatos = new AccesoDatos("/home/william/IdeaProjects/Simulation2/src/main/datos/players_15.csv");
        ArrayList<Persona> personas =  accesoDatos.processFile();
        accesoDatos.guardarDatos(personas);
        System.out.println(" ------> Datos Salvados <------- ");
        System.out.println(" ------> Ahora tienes que ejecutar el Main2 <------- ");
    }
}
