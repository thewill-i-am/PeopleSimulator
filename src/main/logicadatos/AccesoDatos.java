package main.logicadatos;

import main.entities.Persona;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AccesoDatos extends FileProcessor {
    private HashMap<Integer, ArrayList<Integer>> _hashMapTableEdad = new HashMap();
    private HashMap<Integer, ArrayList<Integer>> _hashMapTableEstatura = new HashMap();
    private HashMap<Integer, ArrayList<Integer>> _hashMapTablePeso = new HashMap();
    private HashMap<String, ArrayList<Integer>>  _hashMapTablePais = new HashMap();

    public AccesoDatos(String fileName) {
        super(fileName);
    }

    public AccesoDatos() {

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
                getPersonDataFromStringInHashTable(persona, i);
                result.add(currentPersona);
            } else {
                skip = false;
            }
        }
        return result;
    }

    public void guardarDatos(ArrayList<Persona> personas) throws IOException {
        FileOutputStream f = new FileOutputStream(new File("/home/william/IdeaProjects/Simulation2/src/main/datos/finalOutput.dat"));
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
        FileInputStream fi = new FileInputStream(new File("/home/william/IdeaProjects/Simulation2/src/main/datos/finalOutput.dat"));
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

    private Persona getPersonDataFromStringInHashTable(String currentData, int i) {
        String[] data = currentData.split(",");

        if (!_hashMapTableEdad.containsKey(Integer.parseInt(data[1]))){
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(i);
            _hashMapTableEdad.put(Integer.parseInt(data[1]), arrayList);
        }else{
            _hashMapTableEdad.get(Integer.parseInt(data[1])).add(i);
        }

        if (!_hashMapTableEstatura.containsKey(Integer.parseInt(data[3]))){
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(i);
            _hashMapTableEstatura.put(Integer.parseInt(data[3]), arrayList);
        }else{
            _hashMapTableEstatura.get(Integer.parseInt(data[3])).add(i);
        }

        if (!_hashMapTablePeso.containsKey(Integer.parseInt(data[4]))){
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(i);
            _hashMapTablePeso.put(Integer.parseInt(data[4]), arrayList);
        }else{
            _hashMapTablePeso.get(Integer.parseInt(data[4])).add(i);
        }

        if (!_hashMapTablePais.containsKey(data[5])){
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(i);
            _hashMapTablePais.put(data[5], arrayList);
        }else{
            _hashMapTablePais.get(data[5]).add(i);
        }

        return new Persona();
    }

}