package main;

import main.entities.Persona;

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
                Persona currentPersona = getPersonDataFromString(persona);
                result.add(currentPersona);
            } else {
                skip = false;
            }
        }
        return result;
    }

    private Persona getPersonDataFromString(String currentData) {
        Persona newPersona = new Persona();
        String[] data = currentData.split(",");

        newPersona.setNombre(data[0]);
        newPersona.setEdad(Integer.parseInt(data[1]));

        DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate local = LocalDate.parse(data[2], df);
        newPersona.setFechaNacimiento(local);

        newPersona.setEstatura(Integer.parseInt(data[3]));
        newPersona.setPeso(Integer.parseInt(data[4]));
        newPersona.setNacionalidad(data[5]);
        newPersona.setClub(data[6]);

        if (data.length > 7){
            newPersona.setDorsal(Integer.parseInt(data[7]));
        }else{
            newPersona.setDorsal(0);
        }

        return newPersona;
    }
}