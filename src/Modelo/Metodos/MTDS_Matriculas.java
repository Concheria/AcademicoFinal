/*
 * Autor: Daniel Somarribas Quir�s
 * b57072
 * 2016
 */
package Modelo.Metodos;

import Controlador.CNTRL_Matriculas;
import Modelo.Archivo.ARCH_Matriculas;
import Modelo.Objetos.Matricula;
import Modelo.XML.XML_Matriculas;
import java.util.ArrayList;

/**
 *
 * @author sqdan
 */
public class MTDS_Matriculas {
    ArrayList <Matricula> array;
    CNTRL_Matriculas controlador;
    ARCH_Matriculas archivos;
    String tipo;
    XML_Matriculas xml;

    public MTDS_Matriculas(CNTRL_Matriculas controlador, ARCH_Matriculas archivos)
    {
        array=new ArrayList <Matricula>();
        this.controlador = controlador;
        this.archivos = archivos;
    }
    
    public ArrayList <Matricula> getArray()
    {
        return array;
    }
    
    public void copiarArrayTexto()
    {
        if(tipo.equals("Texto"))
        {
            if(archivos.cargarArchivo())
            {
                this.array = archivos.getArray();
                System.out.println("Archivo Usuarios copiado al array de Usuarios (En Mantenimiento Usuarios)");
            }
            else
            {
                System.out.println("Error al cargar el archivo 'Usuarios' (Desde Métodos).");
            }
        }
    }
    
    public boolean verificarElementos()
    {
        boolean hayElementos = false;
        
        if(tipo.equals("XML"))
        {
            
        }
        
        if(tipo.equals("Texto"))
        {
            if(archivos.cargarArchivo())
            {
                copiarArrayTexto();
                for(int i=0;i<array.size();i++)
                {
                    if(!array.get(0).getCodigo().equals(""))
                    {
                        hayElementos = true;
                    }
                }
            }
        }
        
        return hayElementos;
    }
    
    public void escribirArrayArchivo()
    {
        if(tipo.equals("XML"))
        {
            
        }
        
        if(tipo.equals("Texto"))
        {
            archivos.crearArchivo();
            archivos.addObject(array);
        }
        
        System.out.println("Se ha enviado la información del array al archivo.");
    }
    
    public boolean agregar(String iD, String codigo)
    {
        try
        {
            Matricula temporal=new Matricula(iD, codigo);
        
            array.add(temporal);
            
            escribirArrayArchivo();
            
            copiarArrayTexto();
            
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public boolean eliminar(String iD, String codigo)
     {
         int indice = 0;
         
         for(int contador = 0; contador<array.size();contador++)
         {
             if(array.get(contador).getID().equals(iD) && array.get(contador).getCodigo().equals(codigo))
             {
                 indice = contador;
                 contador = array.size();
             }
         }
         
         try
         {
             array.remove(indice);
                          
             escribirArrayArchivo();
            
             copiarArrayTexto();
             
             return true;
         }
         catch(Exception e)
         {
             return false;
         }
     }
    
    public boolean buscar(String iD, String codigo)
    {
        boolean existe = false;
        
        System.out.println("Buscando ID: "+iD+" - Código: "+codigo);
  
        System.out.println("Tamaño del Array?: "+array.size());
        for(int i = 0; i<array.size(); i++)
         {
             System.out.println("Comparando con ID: "+array.get(i).getCodigo()+" - Codigo: "+array.get(i).getCodigo());
             
             if(array.get(i).getID().equals(iD) && array.get(i).getCodigo().equals(codigo))
             {
                 existe = true;
             }
         }
        
        return existe;
    }
    
    public String[][] getTodos()
    {
        int tamano = getArraySize();
        
        String[][] info = new String[tamano][];
        
        for(int i=0;i<array.size();i++)
        {
            info[i] = array.get(i).getInfo();
        }
        
        return info;
    }
    
    public int getArraySize()
    {
        return array.size();
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
        System.out.println("Tipo en Métodos Matrículas?: "+tipo);
        if(tipo.equals("XML"))
        {
            xml = new XML_Matriculas();
        }
        if(tipo.equals("Texto"))
        {
            copiarArrayTexto();
        }
    }
}
