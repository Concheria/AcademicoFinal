/*
 * Autor: Daniel Somarribas Quir�s
 * b57072
 * 2016
 */
package Modelo.Archivo;

import Modelo.Objetos.Curso;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author sqdan
 */
public class ARCH_Cursos 
{
    ObjectInputStream archivoEntrada;
    ObjectOutputStream archivoSalida;
    
    public ARCH_Cursos()
    {
        if(cargarArchivo())
        {
            System.out.println("El archivo 'Cursos' se ha cargado de forma exitosa.");
        }
        else
        {
            System.out.println("Error al cargar el archivo 'Cursos'.");
        }
    }
    
    public boolean cargarArchivo()
    {
        boolean existe = false;
        
        try
        {
            archivoEntrada = new ObjectInputStream(new FileInputStream("Archivos/Cursos.dat"));
            existe = true;
            System.out.println("El archivo 'Cursos' se ha cargado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el archivo 'Cursos':\n"+e);
        }
        
        return existe;
    }
    
    public void crearArchivo()
    {
        try
        {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("Archivos/Cursos.dat"));
            System.out.println("El archivo 'Cursos' se ha creado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al crear el archivo 'Cursos':\n"+e);
        }
    }
    
    public void addObject(ArrayList<Curso> cursos)
    {
        try
        {
            archivoSalida.writeObject(cursos);
            System.out.println("Se escribió en forma correcta la información en el archivo");
        }
        catch(Exception e)
        {
            System.out.println("Error al escribir la información en el archivo:\n"+e);
        }
    }
    
    public ArrayList<Curso> getArray()
    {
        ArrayList <Curso> array = new ArrayList<>();
        
        try
        {
            while(true)
            {
                array = ((ArrayList<Curso>)archivoEntrada.readObject());
            }
        }
        catch(Exception e)
        {
            System.out.println("Error al devolver el archivo 'Cursos':\n"+e);
        }
        
        return array;        
    }
}
