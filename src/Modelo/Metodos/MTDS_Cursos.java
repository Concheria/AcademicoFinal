package Modelo.Metodos;

import Controlador.CNTRL_Cursos;
import Modelo.Archivo.ARCH_Cursos;
import Modelo.Objetos.Curso;
import Modelo.XML.XML_Cursos;
import java.util.ArrayList;

/**
 * Métodos del Frame de Cursos
 * @author Daniel Somarribas - b57072
 */
public class MTDS_Cursos 
{
    //Define el ArrayList de Cursos
    ArrayList <Curso> array;
    CNTRL_Cursos controlador;
    ARCH_Cursos archivos;
    String tipo;
    XML_Cursos xml;
    
    //Método Constructor: Crea el ArrayList de Cursos
    public MTDS_Cursos(CNTRL_Cursos controlador, ARCH_Cursos archivos)
    {
        array=new ArrayList <Curso>();
        this.controlador = controlador;
        this.archivos = archivos;
    }
    
    //Devuelve el ArrayList de Cursos
    public ArrayList <Curso> getArray()
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
                if(xml.consultarTodoInformacionDelXml())
                {
                    System.out.println("Hay Elementos");
                }
                else
                {
                    System.out.println("No Hay Elementos!");
                }
            }
        }
        
        if(tipo.equals("Texto"))
        {
            if(archivos.cargarArchivo())
            {
                copiarArray();
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
        if(tipo.equals("Texto"))
        {
            archivos.crearArchivo();
            archivos.addObject(array);
        }
        
        System.out.println("Se ha enviado la información del array al archivo.");
    }
    
    //Añade un nuevo Curso al ArrayList
    public boolean agregar(String codigo, String nombre, int creditos)
    {
        boolean agregado = false;
        
        if(tipo.equals("XML"))
        {
            String creditosStr = ""+creditos;
            
            String[] info = new String[3];
            
            info[0] = codigo;
            info[1] = nombre;
            info[2] = creditosStr;
            
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
            Curso temporal=new Curso(codigo, nombre, creditos);
            
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
    
    //Usa un Ciclo For para repasar el ArrayList de Cursos en busca de una Cédula
    //Devuelve un objeto Curso
    public String[] buscar(String codigo)
    {
        System.out.println("Buscando Codigo: "+codigo);

        String[] info = new String[2];
        
        System.out.println("String[] Creado.");

        if(tipo.equals("XML"))
        {
            xml.consultarInformacionDelXml(codigo);
            info = xml.getArregloInformacion();
        }
        
        if(tipo.equals("Texto"))
        {    
            System.out.println("Tamaño del Array?: "+array.size());
            for(int i = 0; i<array.size(); i++)
            {
                System.out.println("Comparando con Codigo: "+array.get(i).getCodigo());
                if(array.get(i).getCodigo().equals(codigo))
                {
                    info[0] = array.get(i).getNombre();
                    info[1] = ""+array.get(i).getCreditos();
                }
            }
        }
        
        return info;
    }
    
    public boolean modificar(String codigo, String nombre, int creditos)
     {
         boolean modificado = false;
         
         if(tipo.equals("XML"))
         {
             String creditosStr = ""+creditos;
             String[] info = new String[3];
             
             info[0] = codigo;
             info[1] = nombre;
             info[2] = creditosStr;
             
             try
             {
                 xml.modificarInformacionDelXml(info);
                 
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
                 if(array.get(contador).getCodigo().equals(codigo))
                 {
                     indice = contador;
                     contador = array.size();
                 }
             }
             
             try
             {
                 array.get(indice).setCodigo(codigo);
                 array.get(indice).setNombre(nombre);
                 array.get(indice).setCreditos(creditos);
                 
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
    
    public boolean eliminar(String codigo)
     {
         boolean eliminado = false;
         
         if(tipo.equals("XML"))
         {
             try
             {
                 xml.eliminarInformacionDelXml(codigo);
                 
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
                 if(array.get(contador).getCodigo().equals(codigo))
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
    
    //Devuelve un String con todos los cursos registrados usando un Ciclo For
    public String[][] getTodos()
    {
        String[][] lista = null;

        if(tipo.equals("XML"))
        {
            if(xml.consultarTodoInformacionDelXml())
            {
                lista = xml.getArregloTodo();
            }
            else
            {
                System.out.println("Error al devolver todo");
            }
        }
        
        if(tipo.equals("Texto"))
        {
            int elementos = array.size();
            
            lista = new String[elementos][3];
            
            for(int i=0; i < array.size(); i++)
            {
                if(array.get(i).getCodigo() != null && !"".equals(array.get(i).getCodigo()))
                {
                    System.out.println("Curso - "+i+"\n"
                            +"Codigo: "+lista[i][0]
                            +"Nombre: "+lista[i][1]
                            +"Creditos: "+lista[i][2]);
                    lista[i] = array.get(i).getInfo();
                }
            }
        }
        
        return lista;
    }
    
    public int getTamanoArray()
    {
        return array.size();
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
        System.out.println("Tipo en Métodos Cursos?: "+tipo);
        if(tipo.equals("XML"))
        {
            xml = new XML_Cursos();
        }
        if(tipo.equals("Texto"))
        {
            copiarArray();
        }
    }
}