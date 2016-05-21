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
        boolean agregado = false;
        
        if(tipo.equals("XML"))
        {
            String[] info = new String[2];
            
            info[0] = iD;
            info[1] = codigo;
            
            try
            {                
                xml.guardarEnXML(info);
                
                agregado = true;
            }
            catch(Exception e)
            {
                agregado = false;
            }
        }
        
        if(tipo.equals("Texto"))
        {
            Matricula temporal=new Matricula(iD, codigo);
                
            try
            {               
                array.add(temporal);
                
                escribirArrayArchivo();
                
                copiarArrayTexto();
                
                agregado = true;
            }
            
            catch(Exception e)
            {
                agregado = false;
            }
        }
        
        return agregado;
    }
    
    public boolean eliminar(String iD, String codigo)
     {
         boolean eliminado = false;
         
         if(tipo.equals("XML"))
         {
             try
             {
                 xml.eliminar(iD, codigo);
                 
                 eliminado = true;
             }
             catch(Exception e)
             {
                 eliminado = false;
             }
         }
         
         if(tipo.equals("Texto"))
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
                 
                 eliminado = true;
             }
             catch(Exception e)
             {
                 eliminado = false;
             }
         }
         
         return eliminado;
     }
    
    public boolean buscar(String iD, String codigo)
    {
        boolean existe = false;
        
        System.out.println("Buscando ID: "+iD+" - Código: "+codigo);
        
        if(tipo.equals("XML"))
        {
            System.out.println("Buscar en XML");

            existe = xml.getExiste(iD, codigo);
            
            System.out.println("Existe (Desde métodos)?: "+existe);
        }
        
        if(tipo.equals("Texto"))
        {
            System.out.println("Tamaño del Array?: "+array.size());
            for(int i = 0; i<array.size(); i++)
            {
                System.out.println("Comparando con ID: "+array.get(i).getCodigo()+" - Codigo: "+array.get(i).getCodigo());
                
                if(array.get(i).getID().equals(iD) && array.get(i).getCodigo().equals(codigo))
                {
                    existe = true;
                }
            }
        }
        
        if(existe)
        {
            System.out.println("Matrícula encontrada en Métodos!");
        }
        
        return existe;
    }
    
    public String[][] getTodos()
    {
        String[][] info = null;
        
        if(tipo.equals("XML"))
        {
            info = xml.getTodos();
        }
        
        if(tipo.equals("Texto"))
        {
            int tamano = getArraySize();
            
            info = new String[tamano][];
            
            for(int i=0;i<array.size();i++)
            {
                info[i] = array.get(i).getInfo();
            }
        }
        
        return info;
    }
    
    public int getArraySize()
    {
        int cantidad = 0;
        
        if(tipo.equals("XML"))
        {
            System.out.println("Devolviendo desde XML");
            cantidad = xml.getCantidadElementos();
        }
        
        if(tipo.equals("Texto"))
        {
            cantidad = array.size();
        }
        
        return cantidad;
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
