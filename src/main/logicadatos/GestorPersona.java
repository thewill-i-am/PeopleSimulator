package main.logicadatos;

import main.entities.Persona;
import main.logicadatos.FileProcessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GestorPersona {
    FileProcessor processor;
    ArrayList<Persona> persona = new ArrayList<Persona>();

    public GestorPersona(FileProcessor oneProcessor) {
        this.processor = oneProcessor;
    }
    public void cargarLista() throws FileNotFoundException {
        if (this.persona.isEmpty()){
            this.persona = this.processor.processFile();
            System.out.println("Lista Cargada");
        }else{
            System.out.println("Lista Llena");
        }
    }
}
