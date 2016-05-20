/*
 * Autor: Daniel Somarribas Quir�s
 * b57072
 * 2016
 */
package Modelo.Archivo;

import Modelo.Objetos.Estudiante;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author sqdan
 */
public class ARCH_Estudiantes 
{
    ObjectInputStream archivoEntrada;
    ObjectOutputStream archivoSalida;
    
    public ARCH_Estudiantes()
    {
        if(cargarArchivo())
        {
            System.out.println("El archivo 'Estudiantes' se ha cargado de forma exitosa.");
        }
        else
        {
            System.out.println("Error al cargar el archivo 'Estudiantes'.");
        }
    }
    
    public boolean cargarArchivo()
    {
        boolean existe = false;
        
        try
        {
            archivoEntrada = new ObjectInputStream(new FileInputStream("Archivos/Estudiantes.dat"));
            existe = true;
            System.out.println("El archivo 'Estudiantes' se ha cargado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el archivo 'Estudiantes':\n"+e);
        }
        
        return existe;
    }
    
    public void crearArchivo()
    {
        try
        {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("Archivos/Estudiantes.dat"));
            System.out.println("El archivo 'Estudiantes' se ha creado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al crear el archivo 'Estudiantes':\n"+e);
        }
    }
    
    public void addObject(ArrayList<Estudiante> estudiantes)
    {
        try
        {
            archivoSalida.writeObject(estudiantes);
            System.out.println("Se escribió en forma correcta la información en el archivo");
        }
        catch(Exception e)
        {
            System.out.println("Error al escribir la información en el archivo:\n"+e);
        }
    }
    
    public ArrayList<Estudiante> getArray()
    {
        ArrayList <Estudiante> array = new ArrayList<>();
        
        try
        {
            while(true)
            {
                array = ((ArrayList<Estudiante>)archivoEntrada.readObject());
            }
        }
        catch(Exception e)
        {
            System.out.println("Error al devolver el archivo 'Estudiantes':\n"+e);
        }
        
        return array;        
    }
}
