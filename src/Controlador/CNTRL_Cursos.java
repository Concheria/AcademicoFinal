/*
 * Tarea 02
 * Sistema Academico con conexion a Base de Datos
 * Nota: Para usar esta aplicación se requiere una base de datos en mysql de nombre "academico"
 * 
 * Curso: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Controlador;

import Modelo.Archivo.ARCH_Cursos;
import Modelo.Database.ConexionBD;
import Modelo.Metodos.MTDS_Cursos;
import Vista.Frame.Administrar.FRM_Cursos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Clase para el controlador del administrador de estudiantes
 * @author DanielSQ
 */
public class CNTRL_Cursos implements ActionListener
{
    ConexionBD baseDatos;
    FRM_Cursos frame;
    String tipo = "";
    MTDS_Cursos metodos;
    ARCH_Cursos archivo;
    
    /**
     * Crea el controlador del Administrador de Cursos
     * @param frame
     * @param baseDatos
     */
    public CNTRL_Cursos(FRM_Cursos frame, ConexionBD baseDatos)
    {
        archivo = new ARCH_Cursos();
        this.frame = frame;
        this.baseDatos = baseDatos;
        metodos = new MTDS_Cursos(this, archivo);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Agregar"))
        {
            agregar();
            System.out.println("Agregar");    
        }
        
        if(e.getActionCommand().equals("Modificar"))
        {
            modificar();
            System.out.println("Modificar");
        }
        
        if(e.getActionCommand().equals("Eliminar"))
        {
            eliminar();
            System.out.println("Eliminar");
        }
    }
    
    /**
     * Agrega el estudiante a la base de datos.
     * 
     * Obtiene el código, el nombre y la dirección del estudiante del frame y lo añade a la base.
     * La base determina si el código ya está usado (es llave primaria)
     */
    public void agregar()
    {
        String[] info = frame.getInfo();
        
        String codigo = info[0];
        String nombre = info[1];
        String creditosStr = info[2];
        
        System.out.println("Curso:\n"
                +"ID: "+codigo+"\n"
                +"Nombre: "+nombre+"\n"
                +"Dirección: "+creditosStr+"");
        
        if(nombre.equals("") || creditosStr.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
        }
        else
        {
            if(tipo.equals("Base"))
            {
                if(baseDatos.agregarCurso(codigo, nombre, creditosStr))
                {
                    System.out.println("Curso Agregado Correctamente");
                    frameReset();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"No se ha podido Agregar el Curso.\n"
                            +"Asegúrese de que el código de curso no esté repetido y que los créditos sean un número.");
                }
            }
            if(tipo.equals("Texto") || tipo.equals("XML"))
            {
                int creditos = 0;
                
                try
                {
                    creditos = Integer.parseInt(creditosStr);
                    
                    if(metodos.agregar(codigo, nombre, creditos))
                    {
                        System.out.println("Estudiante Agregado Correctamente");
                        frameReset();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No se ha podido Agregar el Estudiante.\n"
                                +"¿Quizás usa un código de estudiante repetido?");
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Aségurese de que los créditos sean un número entero");    
                }
            }
        }
    }
    
    /**
     * Envía el código del curso para buscar en la base de datos.
     * Lo realiza cada vez que se escribe, por lo cual no requiere el botón buscar.
 
 Si el código se encuentra, llena los campos de texto y bloquea el botón de agregar.
 Si no se encuentra, limpia los campos de nombre y dirección, y bloquea los botones de modificar y eliminar.
     */
    public void buscar()
    {
        System.out.println("Buscar");
        
        String codigo = frame.getID();
        
        String[] info = null;
        
        if(tipo.equals("Base"))
        {
            info = baseDatos.buscarCurso(codigo);
        }
        
        if(tipo.equals("Texto") || tipo.equals("XML"))
        {
            System.out.println("En Texto");
            info = metodos.buscar(codigo);
        }
        
        if(info[0] != null)
        {
            frame.fillFields(info);
            frame.siEntidadConf();
        }
        else
        {
            frame.clearPartialFields();
            frame.noEntidadConf();
        }
    }
    
    /**
     * Envía la nueva información a la conexión de la base, y le pide cambiarla.
     */
    public void modificar()
    {
        String[] info = frame.getInfo();
        
        String codigo = info[0];
        String nombre = info[1];
        String creditosStr = info[2];
        
        if(tipo.equals("Base"))
        {
            if(baseDatos.modificarCurso(codigo, nombre, creditosStr))
            {
                JOptionPane.showMessageDialog(null, "Curso Modificado Correctamente");
                frameReset();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se ha podido Modificar el Curso.");
            }
        }
        
        if(tipo.equals("Texto") || tipo.equals("XML"))
        {
            int creditos = 0;
            
            try
            {
                creditos = Integer.parseInt(creditosStr);
                
                if(metodos.modificar(codigo, nombre, creditos))
                {
                    JOptionPane.showMessageDialog(null, "Estudiante Modificado Correctamente");
                    frameReset();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No se ha podido Modificar el Estudiante.");
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Aségurese de que los créditos sean un número entero");    
            }
        }
    }
    
    /**
     * Envía el código del Curso a la base de datos para Eliminar
     */
    public void eliminar()
    {
        String codigo = frame.getID();
        
        if(tipo.equals("Base"))
        {
            if(baseDatos.eliminarCurso(codigo))
            {
                JOptionPane.showMessageDialog(null, "Curso Eliminado Correctamente");
                frameReset();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se ha podido Eliminar el Curso");
            }
        }
        
        if(tipo.equals("Texto") || tipo.equals("XML"))
        {
            if(metodos.eliminar(codigo))
            {
                JOptionPane.showMessageDialog(null, "Curso Eliminado Correctamente");
                frameReset();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se ha podido Eliminar el Curso");
            }
        }
    }
    
    public String[] getInfoBuscar(String codigo)
    {
        return metodos.buscar(codigo);
    }
    
    /**
     * Reinicia el Frame
     */
    public void frameReset()
    {
        frame.clearFields();
        frame.nadaConf();
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
        System.out.println("Tipo en Cursos: "+tipo);
        metodos.setTipo(tipo);
    }
    
    public String getTipo()
    {
        return tipo;
    }
    
    public MTDS_Cursos getMetodos()
    {
        return metodos;
    }
}
