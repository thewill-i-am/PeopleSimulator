package main;

import main.entities.Persona;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccesoDatos extends FileProcessor {

    public AccesoDatos(String fileName) {
        super(fileName);
    }

    @Override
    protected ArrayList<Persona> readLines(Scanner reader) {
        ArrayList<Persona> result = new ArrayList<Persona>();
        boolean skip = true;
        int i = 0;
        while (reader.hasNextLine()) {
            i++;
            String persona = reader.nextLine();
            if (!skip) {
                Persona currentPersona = getPersonDataFromString(persona, i);
                result.add(currentPersona);
            } else {
                skip = false;
            }
        }
        return result;
    }

    public void guardarDatos(ArrayList<Persona> personas) throws IOException {
        FileOutputStream f = new FileOutputStream(new File("src/main/datos/finalOutput.dat"));
        ObjectOutputStream o = new ObjectOutputStream(f);

        personas.forEach(x -> {
            try {
                o.writeObject(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        o.close();
        f.close();
    }

    public ArrayList<Persona> leerDatosDat() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("src/main/datos/finalOutput.dat"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        ArrayList<Persona> personas = new ArrayList<Persona>();
        try {
            Persona obj = null;
            while ((obj = (Persona) oi.readObject()) != null) {
                personas.add(obj);
            }
        }catch (Exception e){
            oi.close();
            fi.close();
        }
        oi.close();
        fi.close();
        return personas;
    }


    private Persona limpiarData(String[] datosNombre, Persona newPersona){
        switch (datosNombre.length){
            case 1:
                newPersona.setNombre(datosNombre[0]);
                newPersona.setApellido1("No tiene registrado");
                newPersona.setApellido2("No tiene registrado");
                break;
            case 2:
                newPersona.setNombre(datosNombre[0]);
                newPersona.setApellido1(datosNombre[1]);
                newPersona.setApellido2("No tiene registrado");
                break;
            case 4:
                newPersona.setNombre(datosNombre[0]);
                newPersona.setApellido1(datosNombre[2]);
                newPersona.setApellido2(datosNombre[3]);
                break;
            default:
                newPersona.setNombre(datosNombre[0]);
                newPersona.setApellido1(datosNombre[1]);
                newPersona.setApellido2(datosNombre[2]);
                break;
        }
        return newPersona;
    }

    private Persona getPersonDataFromString(String currentData, int i) {
        Persona newPersona = new Persona();
        String[] data = currentData.split(",");
        String fullName = data[0];
        String[] datosNombre = fullName.split(" ");

        newPersona = limpiarData(datosNombre, newPersona);

        newPersona.setEdad(Integer.parseInt(data[1]));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate local = LocalDate.parse(data[2], df);
        newPersona.setFechaNacimiento(local);

        newPersona.setEstatura(Integer.parseInt(data[3]));
        newPersona.setPeso(Integer.parseInt(data[4]));
        newPersona.setNacionalidad(data[5]);
        newPersona.setClub(data[6]);
        newPersona.setConsecutivo(i);
        newPersona.setCedula(Integer.toString(i));

        if (data.length > 7){
            newPersona.setDorsal(Integer.parseInt(data[7]));
        }else{
            newPersona.setDorsal(00);
        }

        return newPersona;
    }
}