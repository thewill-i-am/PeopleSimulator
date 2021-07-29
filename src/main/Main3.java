package main;

import main.entities.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main3 {

    private static Scanner scanner = new Scanner(System.in);

        public static void hacerBusqueda(ArrayList<Persona> personas, Predicate<Persona> predicate) {
            long start = System.nanoTime();

            List<Persona> personasFiltradoDorsal = personas.stream()
                    .filter(predicate)
                    .collect(Collectors.toList());

            personasFiltradoDorsal.forEach(x -> System.out.println(x.toString()));

            long finish = System.nanoTime();
            long timeElapsed = finish - start;
            System.out.println("Tiempo de ejecucion: " + timeElapsed);
        }

        public static void main(String[] args) throws FileNotFoundException {

        AccesoDatos accesoDatos = new AccesoDatos("/home/william/IdeaProjects/Simulation2/src/main/datos/players_15.csv");
        ArrayList<Persona> personas =  accesoDatos.processFile();

        System.out.println("Por favor elija opcion: ");
        int opcion = scanner.nextInt();
        switch (opcion){
            case 1:
                System.out.println("Filtrar por nombre: ");
                String filtrado = scanner.next();
                hacerBusqueda(personas, s -> s.getNombre().contains(filtrado));
                break;
            case 2:
                System.out.println("Filtrar por edad: ");
                Integer filtradoEdad= scanner.nextInt();
                hacerBusqueda(personas,  s -> s.getEdad() == filtradoEdad);
                break;
            case 3:
                System.out.println("Filtrar por peso: ");
                Integer filtradoPeso= scanner.nextInt();
                hacerBusqueda(personas,  s -> s.getPeso() == filtradoPeso);
                break;
            case 4:
                System.out.println("Filtrar por estatura: ");
                Integer filtradoEstatura= scanner.nextInt();
                hacerBusqueda(personas,  s -> s.getEstatura() == filtradoEstatura);
                break;
            case 5:
                System.out.println("Filtrar por país: ");
                String filtradoPais= scanner.next();
                hacerBusqueda(personas,  s -> s.getNacionalidad().contains(filtradoPais));
                break;
            case 6:
                System.out.println("Filtrar por dorsal: ");
                Integer filtradoDorsal= scanner.nextInt();
                hacerBusqueda(personas, s -> s.getDorsal() == filtradoDorsal);
                break;
            default:
                break;

        }
    }

}
