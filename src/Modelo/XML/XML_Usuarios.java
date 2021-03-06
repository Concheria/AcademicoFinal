/*
 * Tarea 02
 * Sistema Acad�mico con conexi�n a Base de Datos
 * 
 * Usuario: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Modelo.XML;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author DanielSQ
 */
public class XML_Usuarios {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document document;
    ArrayList titulos;
    ArrayList valores;
    Element raiz,principal;
    Source source;
    Result result;
    Result console;
    Transformer transformer;
    String nombreArchivo;
    
    public XML_Usuarios()
    { 
        nombreArchivo="Usuarios";
        
        if(cargarXML())
        {
            System.out.println("XML cargado correctamente");
        }
        else
        {
            crearArchivo( nombreArchivo);
            crearXML();
        }
    
        titulos = new ArrayList();
        valores = new ArrayList();
    }
    
    public void crearXML() //Método nuevo en pruebas
    {
        factory = DocumentBuilderFactory.newInstance();
        try 
        {
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "xml", null);
            document.setXmlVersion("1.0");
            source = new DOMSource(document);
            result = new StreamResult(new java.io.File("XML/"+nombreArchivo+".xml"));
 
            console = new StreamResult(System.out);
 
            transformer = TransformerFactory.newInstance().newTransformer();
 
            transformer.transform(source, result);
            transformer.transform(source, console);
 
            System.err.println("Archivo XML creado correctamente");
        } 
        catch (Exception e) 
        {
            System.err.println("Error al crear el archivo XML: " + e);
        }
    }
    
    public boolean cargarXML() //Método nuevo en pruebas
    {
        boolean cargo=false;
        try 
        {
        
            File fXmlFile = new File("XML/"+nombreArchivo+".xml");
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(fXmlFile);
            document.getDocumentElement().normalize();
            cargo=true;
            
            NodeList nList = document.getElementsByTagName("Usuario");
            Node nNode = nList.item(0);
            raiz = (Element) nNode;
                
            System.out.println("XML Cargado");
        } 
        catch (Exception e) 
        {
            System.out.println("Error al cargar el archivo XML:\n"+e);
        }
        return cargo;
    }
    
    public void agregar(String arregloInformacion[])//Método nuevo en pruebas
    {
        try{
            
            raiz = document.createElement("Usuario");
            principal = document.createElement("Usuario");
            document.getDocumentElement().appendChild(raiz);
            
            Element valor1 = document.createElement("User");
            Text text = document.createTextNode(arregloInformacion[0]);
            Element valor2 = document.createElement("Nombre");
            Text text2 = document.createTextNode(arregloInformacion[1]);
            Element valor3 = document.createElement("Pass");
            Text text3 = document.createTextNode(arregloInformacion[2]);
            
            raiz.appendChild(valor1);
            valor1.appendChild(text);
            raiz.appendChild(valor2);
            valor2.appendChild(text2);
            raiz.appendChild(valor3);
            valor3.appendChild(text3);
            
            source = new DOMSource(document);
            result = new StreamResult(new java.io.File("XML/"+nombreArchivo+".xml"));
            console = new StreamResult(System.out);
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);
            
            }
        catch (Exception e) 
        {
            System.err.println("Error al guardar: " + e);
        }
    }
    
    public void crearArchivo(String nombreArchivo) 
    {
        try{
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, nombreArchivo, null);
            document.setXmlVersion("1.0");
            raiz = document.getDocumentElement();
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("XML/"+nombreArchivo+".xml")); 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            System.out.println("Archivo XML creado con el nombre: "+nombreArchivo);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XML_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (TransformerException ex) {
            Logger.getLogger(XML_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public String[] getInfo(String user)
    {
        String[] arregloInformacion=new String[2];
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Usuario");
        Node tag=null,datoContenido=null;
        
        boolean itemEncontrado=false,tituloUser=false;
        int contador=0;
        int nombre = -1;
        int password = -1;
        
        for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++)
        {
            Node item = listaDeItems.item(contadorItems);
            NodeList datosItem = item.getChildNodes();
            for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++)
            {
                tag = datosItem.item(contadorTags);
                datoContenido = tag.getFirstChild();
                
                if(tag.getNodeName().equals("User") && datoContenido.getNodeValue().equals(""+user) )
                {
                    itemEncontrado=true;
                    nombre = contadorTags + 1;
                    password = contadorTags + 2;
                }
                if(contadorTags==nombre && tag.getNodeName().equals("Nombre"))
                {
                    arregloInformacion[contador]=datoContenido.getNodeValue();
                    contador++;
                }
                if(contadorTags==password && tag.getNodeName().equals("Pass"))
                {
                    arregloInformacion[contador]=datoContenido.getNodeValue();
                    contadorTags = datosItem.getLength();
                    contadorItems = listaDeItems.getLength();
                    contador++;
                }
            }
            
        }
        return arregloInformacion;
    }
    
    public String getPass(String user)
    {
        System.out.println("Consultando Pass");
      
        String pass = null;
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Usuario");
        Node tag=null,datoContenido=null;
        
        boolean itemEncontrado=false,tituloUser=false;
        int numero=-1;
        
        System.out.println("Cantidad de Items: "+listaDeItems.getLength());
        
        for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++)
        {
            System.out.println("Consultando Item: "+contadorItems);
            Node item = listaDeItems.item(contadorItems);
            NodeList datosItem = item.getChildNodes();
            for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++)
            {
                tag = datosItem.item(contadorTags);
                datoContenido = tag.getFirstChild();
                
                //System.out.println("Consultando Tag: "+tag.getNodeName());
                //System.out.println("Consultando Elemento del tag: "+datoContenido.getNodeValue());
                
                if(tag.getNodeName().equals("User") && datoContenido.getNodeValue().equals(""+user) )
                {
                    System.out.println("Consultando Usuario: "+user);
                    //itemEncontrado=true;
                    numero=contadorTags+2;
                }
                if(contadorTags==numero && tag.getNodeName().equals("Pass"))
                {
                    pass=datoContenido.getNodeValue();
                    System.out.println("* password: "+pass);
                    itemEncontrado = true;
                    //contador++;
                }
            }
            
        }
        return pass;
    }
    
    public boolean verificarElementos()
    {
        boolean existe = false;
        
        System.out.println("Consultar XML");
        
         Element raiz = document.getDocumentElement();
         NodeList listaDeItems = raiz.getElementsByTagName("Usuario");
         Node tag=null,datoContenido=null;

         boolean itemEncontrado=false,tituloUser=false;
         int contador=-1;
       
         System.out.println("Cantidad de Items: "+listaDeItems.getLength());
         
         
         for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++) 
         {   
             System.out.println("Analizando Item: "+contadorItems);
             Node item = listaDeItems.item(contadorItems);
             NodeList datosItem = item.getChildNodes();
             for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++) 
             {           
                 tag = datosItem.item(contadorTags); 
                 datoContenido = tag.getFirstChild();
                 
                 itemEncontrado = true;
                     
                 if(!datoContenido.getNodeValue().equals(""))
                 {
                     existe = true;
                 }
             }
         }
         System.out.println("Item Encontrado: "+itemEncontrado);
        
        return existe;
    }
    
    public String[][] getTodo()
    { 
        System.out.println("Consultar XML");
        
         Element raiz = document.getDocumentElement();
         NodeList listaDeItems = raiz.getElementsByTagName("Usuario");
         Node tag=null,datoContenido=null;

         boolean itemEncontrado=false,tituloUser=false;
         int contador=-1;

         String[][] arregloTodo = new String[listaDeItems.getLength()][3];
       
         System.out.println("Cantidad de Items: "+listaDeItems.getLength());
         
         
         for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++) 
         {   
             System.out.println("Analizando Item: "+contadorItems);
             Node item = listaDeItems.item(contadorItems);
             NodeList datosItem = item.getChildNodes();
             for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++) 
             {           
                 tag = datosItem.item(contadorTags); 
                 datoContenido = tag.getFirstChild();
                 
                 itemEncontrado = true;
                     
                 if(!datoContenido.getNodeValue().equals(""))
                 {
                     if(contador < 2)
                     {
                         arregloTodo[contadorItems][contadorTags] = datoContenido.getNodeValue();
                     }
                 }
             }
         }
         System.out.println("Item Encontrado: "+itemEncontrado);
         return arregloTodo;
    }
    
    public void modificar(String informacion[])
    { 
         Element raiz = document.getDocumentElement();
         NodeList listaDeItems = raiz.getElementsByTagName("Usuario");
         Node tag=null,datoContenido=null;
         String arregloInformacion[]=new String[2];
         boolean itemEncontrado=false,tituloUser=false;
         int contador=0;
         try
         {
            for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++) 
            {   
                Node item = listaDeItems.item(contadorItems);
                NodeList datosItem = item.getChildNodes();
                for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++) 
                {   
                    tag = datosItem.item(contadorTags); 
                    datoContenido = tag.getFirstChild();
                    if(tag.getNodeName().equals("User") && datoContenido.getNodeValue().equals(""+informacion[0]) )
                    {   
                       itemEncontrado=true;     
                    }
                    if(itemEncontrado && contador<2)
                    {
                        datoContenido.setNodeValue(informacion[contador]);                    
                        contador++;
                    }
                }
            }
           source = new DOMSource(document);
           result = new StreamResult(new java.io.File("XML/"+nombreArchivo+".xml"));
           console = new StreamResult(System.out);
           transformer = TransformerFactory.newInstance().newTransformer();
           transformer.transform(source, result);
           transformer.transform(source, console);
        }
        catch (Exception e) 
        {
            System.err.println("Error al modificar: " + e);
        }
    }
    
    public void eliminar(String user)
    { 
        Element raiz = document.getDocumentElement();
         NodeList listaDeItems = raiz.getElementsByTagName("Curso");
         Node tag=null,datoContenido=null;
         boolean itemEncontrado=false,tituloCodigo=false;
         try
         {
             for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++)
             {
                 Node item = listaDeItems.item(contadorItems);
                 NodeList datosItem = item.getChildNodes();
                 for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++)
                 {
                     tag = datosItem.item(contadorTags);
                     datoContenido = tag.getFirstChild();
                    if(tag.getNodeName().equals("User") && datoContenido.getNodeValue().equals(""+user) )
                    {
                       itemEncontrado=true;
                       raiz.removeChild(item);
                       source = new DOMSource(document);
                       result = new StreamResult(new java.io.File("XML/"+nombreArchivo+".xml"));
                       console = new StreamResult(System.out);
                       transformer = TransformerFactory.newInstance().newTransformer();
                       transformer.transform(source, result);
                       transformer.transform(source, console);
                    } 
                }
            }
         }
        catch (Exception e) 
        {
            System.err.println("Error al eliminar: " + e);
        }
    }
}
