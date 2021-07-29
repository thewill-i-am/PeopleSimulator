package main;

import main.entities.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main3 {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        AccesoDatos accesoDatos = new AccesoDatos("/home/william/IdeaProjects/Simulation2/src/main/datos/players_15.csv");
        accesoDatos.processFile();
    }
}
