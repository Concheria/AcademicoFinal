/*
 * Autor: Daniel Somarribas Quir�s
 * b57072
 * 2016
 */
package Modelo.Archivo;

import Modelo.Objetos.Usuario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author sqdan
 */
public class ARCH_Usuarios 
{
    ObjectInputStream archivoEntrada;    
    ObjectOutputStream archivoSalida;
    
    public ARCH_Usuarios()
    {
        if(cargarArchivo())
        {
            System.out.println("El archivo 'Usuarios' se ha cargado de forma exitosa.");
        }
        else
        {
            System.out.println("Error al cargar el archivo 'Usuarios'.");
        }
    }
    
    public boolean cargarArchivo()
    {
        boolean existe = false;
        
        try
        {
            archivoEntrada = new ObjectInputStream(new FileInputStream("Archivos/Usuarios.dat"));
            existe = true;
            System.out.println("El archivo 'Usuarios' se ha cargado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el archivo 'Usuarios':\n"+e);
        }
        
        return existe;
    }
    
    public void crearArchivo()
    {
        try
        {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("Archivos/Usuarios.dat"));
            System.out.println("El archivo 'Usuarios' se ha creado de forma exitosa.");
        }
        catch(Exception e)
        {
            System.out.println("Error al crear el archivo 'Usuarios':\n"+e);
        }
    }
    
    public void addObject(ArrayList<Usuario> usuarios)
    {
        try
        {
            archivoSalida.writeObject(usuarios);
            System.out.println("Se escribió en forma correcta la información en el archivo");
        }
        catch(Exception e)
        {
            System.out.println("Error al escribir la información en el archivo:\n"+e);
        }
    }
        
    public ArrayList<Usuario> getArray()
    {
        ArrayList <Usuario> array = new ArrayList<>();
        
        try
        {
            while(true)
            {
                array = ((ArrayList<Usuario>)archivoEntrada.readObject());
            }
        }
        catch(Exception e)
        {
            System.out.println("Error al devolver el archivo 'Usuarios':\n"+e);
        }
        
        return array;        
    }
}
