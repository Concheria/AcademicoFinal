/*
 * Tarea 02
 * Sistema Acad�mico con conexi�n a Base de Datos
 * 
 * Matrícula: Daniel Somarribas Quir�s
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
public class XML_Matriculas {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document document;
    ArrayList titulos;
    ArrayList valores;
    Element raiz,principal;
    String arregloInformacion[];
    Source source;
    Result result;
    Result console;
    Transformer transformer;
    String nombreArchivo;
    String arregloTodo[][];
    
    public XML_Matriculas()
    { 
        nombreArchivo="Matriculas";
        
        if(cargarXML())
        {
            System.out.println("XML cargado correctamente");
        }
        else
        {
            crearXML();
            System.out.println("XML creado correctamente");
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
            
            NodeList nList = document.getElementsByTagName("Matrícula");
            Node nNode = nList.item(0);
            raiz = (Element) nNode;
                
        } 
        catch (Exception e) 
        {
            System.out.println("Error al cargar el archivo XML:\n"+e);
        }
        return cargo;
    }
    
    public void guardarEnXML(String arregloInformacion[])//Método nuevo en pruebas
    {
        try{
            
            raiz = document.createElement("Matrícula");
            principal = document.createElement("Matrícula");
            document.getDocumentElement().appendChild(raiz);
            
            Element valor1 = document.createElement("Cédula");
            Text text = document.createTextNode(arregloInformacion[0]);
            Element valor2 = document.createElement("Código");
            Text text2 = document.createTextNode(arregloInformacion[1]);
            
            raiz.appendChild(valor1);
            valor1.appendChild(text);
            raiz.appendChild(valor2);
            valor2.appendChild(text2);
            
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
            Logger.getLogger(XML_Matriculas.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (TransformerException ex) {
            Logger.getLogger(XML_Matriculas.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public boolean getExiste(String cedula, String codigo)
    {              
        System.out.println("Buscando en Matrículas");
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Matrícula");
        Node tag=null,datoContenido=null,tag2 = null, datoContenido2 = null;
        
        boolean existe=false,tituloCedula=false;
        int contador=0;
        
        System.out.println("Cantidad de Items: "+listaDeItems.getLength());
        
        for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++)
        {
            System.out.println("Analizando item: "+contadorItems);
            Node item = listaDeItems.item(contadorItems);
            NodeList datosItem = item.getChildNodes();
            for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++)
            {
                tag = datosItem.item(contadorTags);
                datoContenido = tag.getFirstChild();
                System.out.println("Cédula Enviada: "+cedula);
                System.out.println("Primer Tag: "+tag.getNodeName()+": "+datoContenido.getNodeValue());
                if((tag.getNodeName().equals("Cédula") && datoContenido.getNodeValue().equals(""+cedula)))
                {
                    tag2 = datosItem.item(contadorTags+1);
                    datoContenido2 = tag2.getFirstChild();
                    System.out.println("Código Enviado: "+codigo);
                    System.out.println("Segundo Tag: "+tag2.getNodeName()+": "+datoContenido2.getNodeValue());
                    if((tag2.getNodeName().equals("Código") && datoContenido2.getNodeValue().equals(""+codigo)))
                    {
                        existe=true;
                        contadorTags = datosItem.getLength();
                        contadorItems = listaDeItems.getLength();
                        System.out.println("Matricula encontrada en XML!");
                    }
                }
            }
            
        }
        
        System.out.println("Exist?: "+existe);
        return existe;
    }
    
    public String[][] getTodos()
    { 
         Element raiz = document.getDocumentElement();
         NodeList listaDeItems = raiz.getElementsByTagName("Matrícula");
         Node tag=null,datoContenido=null;

         arregloTodo = new String[listaDeItems.getLength()][2];
         
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
                    if(contador < 2)
                    {
                        arregloTodo[contadorItems][contadorTags] = datoContenido.getNodeValue();
                    }
                }
             }

         }
         return arregloTodo;
    }
    
    public void eliminar(String cedula, String codigo)
    {
        System.out.println("Eliminando en XMl");
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Matrícula");
        Node tag=null,datoContenido=null, tag2 = null, datoContenido2 = null;
        boolean itemEncontrado=false,tituloCedula=false;
        
        try
        {
            for(int contadorItems=0; contadorItems<listaDeItems.getLength(); contadorItems++)
            {
                System.out.println("Analizando item: "+contadorItems);
                Node item = listaDeItems.item(contadorItems);
                NodeList datosItem = item.getChildNodes();
                for(int contadorTags=0; contadorTags<datosItem.getLength(); contadorTags++)
                {          
                    tag = datosItem.item(contadorTags);
                    datoContenido = tag.getFirstChild();          
                    System.out.println("Cédula Enviada: "+cedula);
                    System.out.println("Primer Tag: "+tag.getNodeName()+": "+datoContenido.getNodeValue());
          
                    if((tag.getNodeName().equals("Cédula") && datoContenido.getNodeValue().equals(""+cedula)))
                    {                    
                        tag2 = datosItem.item(contadorTags+1);
                        datoContenido2 = tag2.getFirstChild();
                        System.out.println("Código Enviado: "+codigo);
                        System.out.println("Segundo Tag: "+tag2.getNodeName()+": "+datoContenido2.getNodeValue());
                        if((tag2.getNodeName().equals("Código") && datoContenido2.getNodeValue().equals(""+codigo)))
                        {
                            itemEncontrado=true;
                            System.out.println("Matricula encontrada - Intentando Eliminar");
                            raiz.removeChild(item);
                            source = new DOMSource(document);
                            result = new StreamResult(new java.io.File("XML/"+nombreArchivo+".xml"));
                            console = new StreamResult(System.out);
                            transformer = TransformerFactory.newInstance().newTransformer();
                            transformer.transform(source, result);
                            transformer.transform(source, console);
                            
                            contadorTags = datosItem.getLength();
                            contadorItems = listaDeItems.getLength();
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Error al eliminar: " + e);
        }
    }
    
    public int getCantidadElementos()
    {
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Matrícula");
        
        return listaDeItems.getLength();
    }
}