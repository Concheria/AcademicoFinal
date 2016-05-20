/*
 * Tarea 02
 * Sistema Academico con conexion a Base de Datos
 * 
 * Estudiante: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Controlador;

import Modelo.Archivo.ARCH_Usuarios;
import Modelo.Database.ConexionBD;
import Modelo.Metodos.MTDS_Usuarios;
import Vista.Frame.Administrar.FRM_Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author DanielSQ
 */
public class CNTRL_Usuarios implements ActionListener{
    FRM_Usuarios frame;
    ConexionBD baseDatos;
    CNTRL_MenuPrincipal controladorMenuPrincipal = null;
    String tipo = "";
    MTDS_Usuarios metodos;
    ARCH_Usuarios archivo;
    
    public CNTRL_Usuarios(FRM_Usuarios frame, ConexionBD baseDatos, ARCH_Usuarios archivo)
    {
        this.archivo = archivo;
        this.frame = frame;
        this.baseDatos = baseDatos;
        metodos = new MTDS_Usuarios(this, archivo);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Agregar"))
        {
            System.out.println("Agregar");  
            if(tipo.equals("Base"))
            {
                agregarBase();
            }
            if(tipo.equals("Texto") || tipo.equals("XML"))
            {
                agregarTexto();
            }  
        }
        
        if(e.getActionCommand().equals("Modificar"))
        {
            if(tipo.equals("Base"))
            {
                modificarBase();
            }
            if(tipo.equals("Texto") || tipo.equals("XML"))
            {
                modificarTexto();
            }
            System.out.println("Modificar");
        }
        
        if(e.getActionCommand().equals("Eliminar"))
        {
            if(tipo.equals("Base"))
            {
                eliminarBase();
            }
            if(tipo.equals("Texto") || tipo.equals("XML"))
            {
                eliminarTexto();
            }
                        JOptionPane.showMessageDialog(null, "Usuario Eliminado Correctamente");
            System.out.println("Eliminar");
        }
    }
    
    /**
     * Agrega el usuario a la base de datos.
     * 
     * Obtiene el código, el nombre y la contraseña del usuario del frame y lo añade a la base.
     * La base determina si el usuario ya está usado (es llave primaria)
     */
    public void agregarBase()
    {
        String[] info = frame.getInfo();
        
        String user = info[0];
        String nombre = info[1];
        String pass = info[2];
        
        System.out.println("Usuario:\n"
                +"ID: "+user+"\n"
                +"Nombre: "+nombre+"\n"
                +"Pass: "+pass+"");
        
        if(nombre.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
        }
        else
        {
            if(baseDatos.agregarUser(user, nombre, pass))
            {
                System.out.println("Usuario Agregado Correctamente");
                if(controladorMenuPrincipal != null)
                {
                    JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
                    frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
                    controladorMenuPrincipal.iniciarLogin();
                }
                else
                {
                    frameReset();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se ha podido Agregar el Usuario.\n");
            }
        }
    }
    
    /**
     * Envía el nombre de usuario para buscarBase en la base de datos.
     * Lo realiza cada vez que se escribe, por lo cual no requiere el botón buscarBase.
 
 * Si el nombre se encuentra, llena los campos de texto y bloquea el botón de agregar.
 * Si no se encuentra, limpia los campos de nombre y contraseña, y bloquea los botones de modificarBase y eliminarBase.
     */
    public void buscar()
    {
        
        String[] info = null;
        
        String user = frame.getUser();
        
        System.out.println("Buscar: "+user);
        
        if(tipo.equals("Base"))
        {
            System.out.println("En base de Datos");
            info = baseDatos.buscarUser(user);
        }
        
        if(tipo.equals("Texto") || tipo.equals("XML"))
        {
            System.out.println("En Texto");
            info = metodos.buscar(user);
        }
        
        if(info[0] != null )
        {
            System.out.println("Llenando Espacios");
            frame.fillField(info[0]);
            frame.siEntidadConf();
        }
        else
        {
            System.out.println("Limpiando espacios");
            frame.clearPartialField();
            frame.noEntidadConf();
        }
    }
    
    /**
     * Envía la nueva información a la conexión de la base, y le pide cambiarla.
     */
    public void modificarBase()
    {
        String[] info = frame.getInfo();
        
        String user = info[0];
        String nombre = info[1];
        String pass = info[2];
        
        if(pass.equals(""))
        {
            pass = baseDatos.getPass(user);
        }
        
        if(baseDatos.modificarUser(user, nombre, pass))
        {
            JOptionPane.showMessageDialog(null, "Usuario Modificado Correctamente");
            frameReset();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha podido Modificar el Usuario.");
        }
    }
    
    /**
     * Envía el código del Estudiante a la base de datos para Eliminar
     */
    public void eliminarBase()
    {
        String user = frame.getUser();
                
        if(baseDatos.eliminarUser(user))
        {
            frameReset();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha podido Eliminar el Usuario");
        }
    }
    
    public void agregarTexto()
    {        
        String[] info = frame.getInfo();
        
        String user = info[0];
        String nombre = info[1];
        String pass = info[2];
        
        System.out.println("Usuario:\n"
                +"ID: "+user+"\n"
                +"Nombre: "+nombre+"\n"
                +"Pass: "+pass+"");
        
        if(nombre.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
        }
        else
        {
            if(metodos.agregar(info))
            {
                System.out.println("Usuario Agregado Correctamente");
                if(controladorMenuPrincipal != null)
                {
                    JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
                    frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
                    controladorMenuPrincipal.iniciarLogin();
                    controladorMenuPrincipal = null;
                    frameReset();
                }
                else
                {
                    frameReset();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al agregar el usuario");
            }
        }
    }
    
    public void modificarTexto()
    {
        String[] info = frame.getInfo();
        
        String user = info[0];
        String nombre = info[1];
        String pass = info[2];
        
        if(pass.equals(""))
        {
            pass = metodos.getPass(user);
        }
        
        if(metodos.modificar(user, nombre, pass))
        {
            JOptionPane.showMessageDialog(null, "Usuario Modificado Correctamente");
            frameReset();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha podido Modificar el Usuario.");
        }
    }
    
    public void eliminarTexto()
    {
        String usuario = frame.getUser();
        metodos.eliminar(usuario);
        frameReset();
    }
    
    public void configuracionInicial(CNTRL_MenuPrincipal controladorMenuPrincipal)
    {
        this.controladorMenuPrincipal = controladorMenuPrincipal;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
        System.out.println("Tipo en Usuarios: "+tipo);
        metodos.setTipo(tipo);
    }
    
    public String getTipo()
    {
        return tipo;
    }
    
    /**
     * Reinicia el Frame
     */
    public void frameReset()
    {
        frame.clearFields();
        frame.nadaConf();
    }
    
    public MTDS_Usuarios getMetodos()
    {
        return metodos;
    }
}
