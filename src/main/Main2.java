package main;

import main.entities.Persona;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Main2 {


    public static void main(String[] args) {

        HashMap data = new HashMap();
        ArrayList<String> info = new ArrayList<>();
        info.add("1");
        info.add("2");
        info.add("3");
        info.add("4");
        data.put(1, info);
        ArrayList<String> info2 = new ArrayList<>();
        info2.add("1");
        info2.add("2");
        info2.add("3");
        data.put(2, info2);
        try {
            FileOutputStream f = new FileOutputStream(new File("c:\\dev\\java\\misObjetos.dat"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(data);

            o.close();
            f.close();

            FileInputStream fi = new FileInputStream(new File("c:\\dev\\java\\misObjetos.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            HashMap pr1 = (HashMap) oi.readObject();

            System.out.println(((ArrayList)pr1.get(1)).size());
            System.out.println(((ArrayList)pr1.get(1)).get(2));


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
