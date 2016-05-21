/*
 * Autor: Daniel Somarribas Quir�s
 * b57072
 * 2016
 */
package Modelo.Archivo;

import Modelo.Objetos.Matricula;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author sqdan
 */
public class ARCH_Matriculas {
    ObjectInputStream archivoEntrada;
    ObjectOutputStream archivoSalida;
    
    public ARCH_Matriculas()
    {
        if(cargarArchivo())
        {
            System.out.println("El archivo 'Matriculas' se ha cargado de forma exitosa.");
        }
        else
        {
            System.out.println("Error al cargar el archivo 'Matriculas'.");
            crearArchivo();
        }
    }
    
    public boolean cargarArchivo()
    {
        boolean existe = false;
        
        try
        {
            archivoEntrada = new ObjectInputStream(new FileInputStream("Archivos/Matriculas.dat"));
            existe = true;
            System.out.println("El archivo 'Matriculas' se ha cargado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el archivo 'Matriculas':\n"+e);
        }
        
        return existe;
    }
    
    public void crearArchivo()
    {
        try
        {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("Archivos/Matriculas.dat"));
            System.out.println("El archivo 'Matriculas' se ha creado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al crear el archivo 'Matriculas':\n"+e);
        }
    }
    
    public void addObject(ArrayList<Matricula> matriculas)
    {
        try
        {
            archivoSalida.writeObject(matriculas);
            System.out.println("Se escribió en forma correcta la información en el archivo");
        }
        catch(Exception e)
        {
            System.out.println("Error al escribir la información en el archivo:\n"+e);
        }
    }
    
    public ArrayList<Matricula> getArray()
    {
        ArrayList <Matricula> array = new ArrayList<>();
        
        try
        {
            while(true)
            {
                array = ((ArrayList<Matricula>)archivoEntrada.readObject());
            }
        }
        catch(Exception e)
        {
            System.out.println("Error al devolver el archivo 'Matriculas':\n"+e);
        }
        
        return array;        
    }
}
