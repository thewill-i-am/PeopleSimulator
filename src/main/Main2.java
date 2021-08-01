package main;

import main.entities.Persona;
import main.logicadatos.AccesoDatos;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main2 {

    private static Scanner scanner = new Scanner(System.in);

    public static void hacerBusqueda(ArrayList<Persona> personas, Predicate<Persona> predicate) {
        long start = System.currentTimeMillis();

        List<Persona> personasFiltradoDorsal = personas.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        personasFiltradoDorsal.forEach(x -> System.out.println(x.toString()));

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("TECNICA SIN INDICES: Tiempo de ejecucion: " + timeElapsed + " mili segundos");
    }


    public static void hacerBusquedaPorIndex(HashMap hashMap, Object term, ArrayList<Persona> personas) {
        long start = System.currentTimeMillis();

        ArrayList<Integer> result = (ArrayList) hashMap.get(term);

        for (Integer consecutivo: result) {
            Persona persona = personas.get(consecutivo-2);
            System.out.println(persona);
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("TECNICA CON INDICES : Tiempo de ejecucion: " + timeElapsed + " mili segundos");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        AccesoDatos accesoDatos = new AccesoDatos();
        ArrayList<Persona> personas = accesoDatos.leerDatosDat();

        HashMap edad = accesoDatos.leerDatosInx("edad");
        HashMap pais = accesoDatos.leerDatosInx("pais");
        HashMap peso = accesoDatos.leerDatosInx("peso");
        HashMap estatura = accesoDatos.leerDatosInx("estatura");

        System.out.println("Por favor elija opcion para realizar el filtrado: \n" +
                "1 -> Nombre \n" +
                "2 -> Edad \n" +
                "3 -> Peso \n" +
                "4 -> Estatura \n" +
                "5 -> Pais \n" +
                "6 -> Dorsal");

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
                hacerBusquedaPorIndex(edad, filtradoEdad, personas);
                break;
            case 3:
                System.out.println("Filtrar por peso: ");
                Integer filtradoPeso= scanner.nextInt();
                hacerBusqueda(personas,  s -> s.getPeso() == filtradoPeso);
                hacerBusquedaPorIndex(peso, filtradoPeso, personas);
                break;
            case 4:
                System.out.println("Filtrar por estatura: ");
                Integer filtradoEstatura= scanner.nextInt();
                hacerBusqueda(personas,  s -> s.getEstatura() == filtradoEstatura);
                hacerBusquedaPorIndex(estatura, filtradoEstatura, personas);
                break;
            case 5:
                System.out.println("Filtrar por país: ");
                String filtradoPais= scanner.next();
                hacerBusqueda(personas,  s -> s.getNacionalidad().contains(filtradoPais));
                hacerBusquedaPorIndex(pais, filtradoPais, personas);
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
