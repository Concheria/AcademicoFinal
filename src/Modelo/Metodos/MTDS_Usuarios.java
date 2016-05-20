/*
 * Tarea 02
 * Sistema Acad�mico con conexi�n a Base de Datos
 * 
 * Estudiante: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Modelo.Metodos;

import Controlador.CNTRL_Usuarios;
import Modelo.Archivo.ARCH_Usuarios;
import Modelo.Objetos.Usuario;
import Modelo.XML.XML_Usuarios;
import java.util.ArrayList;

/**
 *
 * @author DanielSQ
 */
public class MTDS_Usuarios {
    ArrayList <Usuario> array;
    CNTRL_Usuarios controlador;
    ARCH_Usuarios archivos;
    String tipo;
    XML_Usuarios xml;
    
    public MTDS_Usuarios(CNTRL_Usuarios controlador,ARCH_Usuarios archivos)
    {
        array = new ArrayList<Usuario>();
        this.archivos = archivos;
        this.controlador = controlador;
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
            if(xml.cargarXML())
            {
                if(xml.verificarElementos())
                {
                    System.out.println("Hay Elementos");
                    hayElementos = true;
                }
                else
                {
                    System.out.println("No Hay Elementos!");
                    hayElementos = false;
                }
            }
        }
        
        if(tipo.equals("Texto"))
        {
            if(archivos.cargarArchivo())
            {
                copiarArrayTexto();
                for(int i=0;i<array.size();i++)
                {
                    if(!array.get(0).getUser().equals(""))
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
        if(tipo.equals("Texto"))
        {
            archivos.crearArchivo();
            archivos.addObject(array);
        }
        
        System.out.println("Se ha enviado la información del array al archivo.");
    }
     
     public boolean agregar(String[] infoUsuario)
    {
        boolean agregado = false;
        
        if(tipo.equals("XML"))
        {
            System.out.println("Agregando en XML");
            try
            {
                xml.agregar(infoUsuario);
                
                agregado = true;
            }
            catch(Exception e)
            {
                agregado = false;
            }
        }
        
        if(tipo.equals("Texto"))
        {
            Usuario temporal = new Usuario(infoUsuario);
            
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
     
     public String[] buscar(String usuario)
     {         
         String[] info = new String[2];
         
         if(tipo.equals("XML"))
        {
            info = xml.getInfo(usuario);
            
            System.out.println("Regresado:\nNombre: "+info[0]+"\nPass: "+info[1]);
        }
         
         if(tipo.equals("Texto"))
         {
             for(int i = 0; i<array.size(); i++)
             {
                 if(array.get(i).getUser().equals(usuario))
                 {
                     info[0] = array.get(i).getNombre();
                     info[1] = array.get(i).getPass();
                 }
             }
         }
         
         return info;
     }
     
     public boolean modificar(String usuario, String nombre, String pass)
     {
         boolean modificado = false;
         
         if(tipo.equals("XML"))
         {
             String[] info = new String[3];
             
             info[0] = usuario;
             info[1] = nombre;
             info[2] = pass;
             
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
                 if(array.get(contador).getUser().equals(usuario))
                 {
                     indice = contador;
                     contador = array.size();
                 }
             }
             
             try
             {
                 array.get(indice).setUser(usuario);
                 array.get(indice).setNombre(nombre);
                 array.get(indice).setPass(pass);
                 
                 escribirArrayArchivo();
                 
                 copiarArrayTexto();
                 
                 modificado = true;
             }
             catch(Exception e)
             {
                 modificado = false;
             }
         }
         
         return modificado;
     }
     
     public boolean eliminar(String usuario)
     {
         boolean eliminado = false;
         
         if(tipo.equals("XML"))
         {
             try
             {
                 xml.eliminar(usuario);
                 
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
                 if(array.get(contador).getUser().equals(usuario))
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
     
     public String getPass(String usuario)
     {
         String pass = "";
         
         if(tipo.equals("XML"))
         {
             pass = xml.getPass(usuario);
             System.out.println("Pass recibido de XML: "+pass);
         }
         
         if(tipo.equals("Texto"))
         {
             for(int i = 0; i<array.size(); i++)
             {
                 if(array.get(i).getUser().equals(usuario))
                 {
                     pass = array.get(i).getPass();
                 }
             }
         }
         
         return pass;
     }
     
     public void setTipo(String tipo)
    {
        this.tipo = tipo;
        System.out.println("Tipo en Métodos Usuarios?: "+tipo);
        if(tipo.equals("XML"))
        {
            xml = new XML_Usuarios();
        }
        if(tipo.equals("Texto"))
        {
            copiarArrayTexto();
        }
    }
}
