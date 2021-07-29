package main;

import main.entities.Persona;

import java.io.*;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {

        Persona unaPersona = new Persona("79990141", 1, "Mario", "Aguero"
                  ,"Obando", LocalDate.parse("1990-11-11"));
        Persona otraPersona = new Persona("79990141", 1, "Maria", "Fitz"
                ,"Wanda", LocalDate.parse("1991-11-11"));

        try {
            FileOutputStream f = new FileOutputStream(new File("/home/william/IdeaProjects/Simulation2/src/main/datos/misObjetos.dat"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(unaPersona);
            o.writeObject(otraPersona);

            o.close();
            f.close();

            FileInputStream fi = new FileInputStream(new File("/home/william/IdeaProjects/Simulation2/src/main/datos/misObjetos.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            Persona pr1 = (Persona) oi.readObject();
            Persona pr2 = (Persona) oi.readObject();

            System.out.println(pr1.toString());
            System.out.println(pr2.toString());

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
