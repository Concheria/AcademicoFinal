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
import Vista.Frame.Administrar.FRM_Cursos;
import Vista.Frame.Administrar.FRM_Estudiantes;
import Vista.Frame.Ver.FRM_VerMatriculas;
import Vista.Frame.Administrar.FRM_Matriculas;
import Vista.Frame.Administrar.FRM_Usuarios;
import Vista.Frame.Ver.FRM_VerCursos;
import Vista.Frame.Ver.FRM_VerEstudiantes;
import Vista.Frame.FRM_Login;
import Vista.Frame.FRM_MenuPrincipal;
import Vista.Frame.FRM_Tipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author DanielSQ
 */
public class CNTRL_MenuPrincipal implements ActionListener
{
    int tipoArchivo;
    String tipo;
    
    FRM_MenuPrincipal frame;
    FRM_Cursos cursos;
    FRM_Estudiantes estudiantes;
    FRM_VerEstudiantes verEstudiantes;
    FRM_VerCursos verCursos;
    FRM_Matriculas matriculas;
    FRM_VerMatriculas verMatriculas;
    FRM_Usuarios usuarios;
    FRM_Login login;
    FRM_Tipo tipoFrame;
    ARCH_Usuarios archivoUsuarios;
    MTDS_Usuarios metodosUsuarios;
    
    ConexionBD baseDatos;
    
    /**
     * Crea el controlador del Menú Principal
     * @param frame
     */
    public CNTRL_MenuPrincipal(FRM_MenuPrincipal frame)
    {
        baseDatos = new ConexionBD();
        this.frame = frame;
        inicializarFrames(baseDatos);
    }
    
    /**
     * Inicializa todos los Frames del programa y los centra
     * @param baseDatos
     */
    public void inicializarFrames(ConexionBD baseDatos)
    {
        this.baseDatos = baseDatos;
        archivoUsuarios = new ARCH_Usuarios();
        estudiantes = new FRM_Estudiantes(baseDatos);
        estudiantes.setLocationRelativeTo(null);
        verEstudiantes = new FRM_VerEstudiantes(baseDatos);
        verEstudiantes.setLocationRelativeTo(null);
        cursos = new FRM_Cursos(baseDatos);
        cursos.setLocationRelativeTo(null);
        verCursos = new FRM_VerCursos(baseDatos);
        verCursos.setLocationRelativeTo(null);
        matriculas = new FRM_Matriculas(baseDatos, this);
        matriculas.setLocationRelativeTo(null);
        verMatriculas = new FRM_VerMatriculas(baseDatos, this);
        verMatriculas.setLocationRelativeTo(null);
        usuarios = new FRM_Usuarios(baseDatos, archivoUsuarios);
        usuarios.setLocationRelativeTo(null);
        login = new FRM_Login(baseDatos, this);
        login.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Estudiantes"))
        {
            System.out.println("Administrar Estudiantes");
            estudiantes.setVisible(true);
        }
                
        if(e.getActionCommand().equals("Cursos"))
        {
            System.out.println("Administrar Cursos");
            cursos.setVisible(true);
        }
        
        if(e.getActionCommand().equals("Matriculas"))
        {
            matriculas.setVisible(true);
            System.out.println("Administrar Matrículas");            
        }
        
        
        if(e.getActionCommand().equals("VerEstudiantes"))
        {
            prepararVer();
            System.out.println("Ver Estudiantes");
            verEstudiantes.resetTabla();
            verEstudiantes.updateTabla();
            verEstudiantes.setVisible(true);
        }
        
        if(e.getActionCommand().equals("VerCursos"))
        {
            prepararVer();
            System.out.println("Ver Cursos");
            verCursos.resetTabla();
            verCursos.updateTabla();
            verCursos.setVisible(true);
        }
        
        if(e.getActionCommand().equals("VerMatriculas"))
        {
            prepararVer();
            verMatriculas.resetTabla();
            verMatriculas.updateTabla();
            verMatriculas.setVisible(true);
            System.out.println("Ver Matrículas");            
        }
        
        if(e.getActionCommand().equals("AdministrarUsuarios"))
        {
            usuarios.setVisible(true);
        }
        
        if(e.getActionCommand().equals("Ingresar"))
        {
            System.out.println("Intentando Ingresar");
            ingresar();
        }
        
        if(e.getActionCommand().equals("Salir"))
        {
            System.exit(0);
        }
    }
    
    public void openTipo()
    {
        tipoFrame = new FRM_Tipo(this);
        if(baseDatos.conectarBD() == false)
        {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la Base de Datos.\nPor favor intente más tarde.");
            tipoFrame.apagarBotonSQL();
        }
        tipoFrame.setVisible(true);
    }
    
    public void continuar()
    {
        metodosUsuarios = usuarios.getMetodos();
        frame.setTipo(tipo);
        tipoFrame.setVisible(false);
        System.out.println("Tipo en Operaciones: "+tipo);
        distribuirTipo();
        frame.inicioDeSistema();
    }
    
    public void distribuirTipo()
    {
        usuarios.setTipo(tipo);
        matriculas.setTipo(tipo);
        estudiantes.setTipo(tipo);
        cursos.setTipo(tipo);
    }
    
    public int verificarUsersBase()
    {
        return baseDatos.verificarUsers();
    }
    
    public boolean verificarUsuarios()
    {
        boolean existe = false;
        
        if(metodosUsuarios.verificarElementos())
        {
            existe = true;
        }
        
        return existe;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public void ingresar()
    {
        String userEscrito = login.getUser();
        String passEscrito = login.getPass();
                
        if((userEscrito.equals("")) || (passEscrito.equals("")))
        {
            JOptionPane.showMessageDialog(null, "Debe llenar ambos campos");
        }
        else
        {
            if(frame.getTipo().equals("Base"))
            {
                String pass = baseDatos.getPass(userEscrito);
                
                if(passEscrito.equals(pass))
                {
                    login.setVisible(false);
                    frame.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Revise su nombre de usuario o contraseña");
                }
            }
            
            if(frame.getTipo().equals("Texto") || frame.getTipo().equals("XML"))
            {
                metodosUsuarios = usuarios.getMetodos();
                
                String pass = metodosUsuarios.getPass(userEscrito);
                
                System.out.println("Pass desde Métodos: "+pass);
                
                if(passEscrito.equals(pass))
                {
                    login.setVisible(false);
                    frame.setVisible(true);
                    usuarios.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Revise su nombre de usuario o contraseña");
                }
            }
        }
    }
    
    public String[] getInfoEstudiante(String iD)
    {
        return estudiantes.getInfoBuscar(iD);
    }
    
    public String getNombreEstudiante(String iD)
    {
        String nombre = "";
        
        String[] info = getInfoEstudiante(iD);
        
        nombre = info[0];
        
        System.out.println("Nombre encontrado: "+nombre);
        
        return nombre;
    }
    
    public String[] getInfoCurso(String codigo)
    {
        return cursos.getInfoBuscar(codigo);
    }
    
    public void usuariosConfiguracionInicial()
    {
        frame.setVisible(false);
        usuarios.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        usuarios.configuracionInicial(this);
        usuarios.setVisible(true);
    }    
        
    public void prepararVer()
    {
        verEstudiantes.setTipo(tipo);
        verEstudiantes.setMetodos(estudiantes.getMetodos());
        
        verCursos.setTipo(tipo);
        verCursos.setMetodos(cursos.getMetodos());
        
        verMatriculas.setTipo(tipo);
        verMatriculas.setMetodos(matriculas.getMetodos());
    }
    
    public void iniciarLogin()
    {
        frame.setVisible(false);
        usuarios.setVisible(false);
        login.setVisible(true);
    }
}
