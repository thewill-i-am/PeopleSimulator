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
    public static void main(String[] args) throws FileNotFoundException {
        AccesoDatos accesoDatos = new AccesoDatos("/home/william/IdeaProjects/Simulation2/src/main/datos/players_15.csv");
        ArrayList<Persona> personas =  accesoDatos.processFile();

        int opcion = scanner.nextInt();
        switch (opcion){
            case 1:
                System.out.println("Filtrar por nombre: ");
                String filtrado= scanner.next();
                Predicate<Persona> containsDigit = s -> s.getNombre().contains(filtrado) == true;
                List<Persona> personasFiltrado = personas.stream()
                        .filter(containsDigit)
                        .collect(Collectors.toList());
                personasFiltrado.forEach(x -> System.out.println(x.toString()));
                break;
            case 2:
                System.out.println("Filtrar por edad: ");
                Integer filtradoEdad= scanner.nextInt();
                Predicate<Persona> containsDigitEdad = s -> s.getEdad() == filtradoEdad == true;
                List<Persona> personasFiltradoEdad = personas.stream()
                        .filter(containsDigitEdad)
                        .collect(Collectors.toList());
                personasFiltradoEdad.forEach(x -> System.out.println(x.toString()));

                break;
            case 3:
                System.out.println("Filtrar por peso: ");
                Integer filtradoPeso= scanner.nextInt();
                Predicate<Persona> containsDigitPeso = s -> s.getPeso() == filtradoPeso == true;
                List<Persona> personasFiltradoPeso = personas.stream()
                        .filter(containsDigitPeso)
                        .collect(Collectors.toList());

                personasFiltradoPeso.forEach(x -> System.out.println(x.toString()));
                break;
            case 4:
                System.out.println("Filtrar por estatura: ");
                Integer filtradoEstatura= scanner.nextInt();
                Predicate<Persona> containsDigitEstatura = s -> s.getEstatura() == filtradoEstatura == true;
                List<Persona> personasFiltradoEstatura = personas.stream()
                        .filter(containsDigitEstatura)
                        .collect(Collectors.toList());

                personasFiltradoEstatura.forEach(x -> System.out.println(x.toString()));
                break;
            case 5:
                System.out.println("Filtrar por país: ");
                String filtradoPais= scanner.next();
                Predicate<Persona> containsDigitPais = s -> s.getNacionalidad().contains(filtradoPais)  == true;
                List<Persona> personasFiltradoPais = personas.stream()
                        .filter(containsDigitPais)
                        .collect(Collectors.toList());

                personasFiltradoPais.forEach(x -> System.out.println(x.toString()));
                break;
            case 6:
                System.out.println("Filtrar por dorsal: ");
                Integer filtradoDorsal= scanner.nextInt();
                Predicate<Persona> containsDigitDorsal = s -> s.getDorsal() == filtradoDorsal  == true;
                List<Persona> personasFiltradoDorsal = personas.stream()
                        .filter(containsDigitDorsal)
                        .collect(Collectors.toList());

                personasFiltradoDorsal.forEach(x -> System.out.println(x.toString()));
                break;
            default:
                break;

        }

    }
}
