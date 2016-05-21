package Modelo.Metodos;

import Controlador.CNTRL_Estudiantes;
import Modelo.Archivo.ARCH_Estudiantes;
import Modelo.Objetos.Estudiante;
import Modelo.XML.XML_Estudiantes;
import java.util.ArrayList;

/**
 * Métodos del Frame de Estudiantes
 * @author Daniel Somarribas - b57072
 */
public class MTDS_Estudiantes 
{
    //Define el ArrayList de Estudiantes
    ArrayList <Estudiante> array;
    CNTRL_Estudiantes controlador;
    ARCH_Estudiantes archivos;
    String tipo;
    XML_Estudiantes xml;

    //Método Constructor: Crea el ArrayList de Estudiantes
    public MTDS_Estudiantes(CNTRL_Estudiantes controlador, ARCH_Estudiantes archivos)
    {
        array=new ArrayList <Estudiante>();
        this.controlador = controlador;
        this.archivos = archivos;
    }
    
    //Devuelve el ArrayList de Estudiantes
    public ArrayList <Estudiante> getArray()
    {
        return array;
    }
    
    public void copiarArray()
    {
        if(tipo.equals("Texto"))
        {
            if(archivos.cargarArchivo())
            {
                this.array = archivos.getArray();
                System.out.println("Archivo Usuarios copiado al array de Estudiantes (En Mantenimiento Estduiantes)");
            }
            else
            {
                System.out.println("Error al cargar el archivo 'Estudiantes' (Desde Métodos).");
            }
        }
    }
     
    public void escribirArrayArchivo()
    {
        if(tipo.equals("Texto"))
        {
            archivos.crearArchivo();
            archivos.addObject(array);
        }
        System.out.println("Se ha enviado la información del array al archivo.");
    }
    
    //Añade un nuevo Estudiante al ArrayList
    public boolean agregar(String[] info)
    {
        boolean agregado = false;
        
        if(tipo.equals("XML"))
        {
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
            Estudiante temporal=new Estudiante(info);
            
            try
            {
                array.add(temporal);
                
                escribirArrayArchivo();
                
                copiarArray();
                
                agregado = true;
            }
            catch(Exception e)
            {
                agregado = false;
            }
        }
        
        return agregado;
    }
    
    //Usa un Ciclo For para repasar el ArrayList de Estudiantes en busca de una Cédula
    //Devuelve un objeto Estudiante
    public String[] buscar(String iD)
    {
        System.out.println("Buscando ID: "+iD);

        String[] info = new String[2];
        
        System.out.println("String[] Creado.");
        
        if(tipo.equals("XML"))
        {
            System.out.println("Buscar en XML");

            info = xml.getInfo(iD);
        }
        
        if(tipo.equals("Texto"))
        {
            System.out.println("Tamaño del Array?: "+array.size());
            for(int i = 0; i<array.size(); i++)
            {
                System.out.println("Comparando con ID: "+array.get(i).getID());
                if(array.get(i).getID().equals(iD))
                {
                    info[0] = array.get(i).getNombre();
                    info[1] = array.get(i).getLugar();
                }
            }
        }
        
        return info;
    }
    
    public boolean modificar(String iD, String nombre, String lugar)
     {
         boolean modificado = false;

         if(tipo.equals("XML"))
         {
             String[] info = new String[3];
             
             info[0] = iD;
             info[1] = nombre;
             info[2] = lugar;
             
             try
             {
                 xml.modificar(info);
                 
                 modificado = true;
             }
             catch(Exception e)
             {
                 modificado = false;
             }
         }
         
         if(tipo.equals("Texto"))
         {
             int indice = 0;
             
             for(int contador = 0; contador<array.size();contador++)
             {
                 if(array.get(contador).getID().equals(iD))
                 {
                     indice = contador;
                     contador = array.size();
                 }
             }
             
             try
             {
                 array.get(indice).setID(iD);
                 array.get(indice).setNombre(nombre);
                 array.get(indice).setLugar(lugar);
                 
                 escribirArrayArchivo();
                 
                 copiarArray();
                 
                 modificado = true;
             }
             catch(Exception e)
             {
                 modificado = false;
             }
         }
         
         return modificado;
     }
    
    public boolean eliminar(String iD)
     {
         boolean eliminado = false;
         
         if(tipo.equals("XML"))
         {
             try
             {
                 xml.eliminar(iD);
                 
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
                 if(array.get(contador).getID().equals(iD))
                 {
                     indice = contador;
                     contador = array.size();
                 }
             }
             
             try
             {
                 array.remove(indice);
                 
                 escribirArrayArchivo();
                 
                 copiarArray();
                 
                 eliminado = true;
             }
             catch(Exception e)
             {
                 eliminado = false;
             }
         }
         
         return eliminado;
     }
    
    //Devuelve un String con todos los estudiantes registrados usando un Ciclo For
    public String[][] getTodos()
    {
        String[][] lista = null;
        
        if(tipo.equals("XML"))
        {
            lista = xml.getTodo();
        }
        
        if(tipo.equals("Texto"))
        {
            int elementos = array.size();
            
            lista = new String[elementos][3];
            
            for(int i=0; i < array.size(); i++)
            {
                if(array.get(i).getID() != null && !"".equals(array.get(i).getID()))
                {
                    System.out.println("Estudiante - "+i+"\n"
                            +"ID: "+lista[i][0]
                            +"Nombre: "+lista[i][1]
                            +"Lugar: "+lista[i][2]);
                    lista[i] = array.get(i).getInfo();
                }
            }
        }
        
        return lista;
    }
    
    public int getTamano()
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
        System.out.println("Tipo en Métodos Estudiantes?: "+tipo);
        if(tipo.equals("XML"))
        {
            xml = new XML_Estudiantes();
        }
        if(tipo.equals("Texto"))
        {
            copiarArray();
        }
    }
}
