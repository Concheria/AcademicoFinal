/*
 * Tarea 02
 * Sistema Academico con conexion a Base de Datos
 * 
 * Estudiante: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Modelo.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DanielSQ
 */
public class ConexionBD 
{
    Connection con = null;
    
    /**
     * Constructor para el conector a la Base de Datos
     */
    public ConexionBD()
    {
    }
    
    /**
     * Conecta a la Base de Datos
     * Nota: Para usar este programa se requiere una base de datos en mysql llamada académico con las tablas requeridas
     */
    public boolean conectarBD()
    {
        try
        {
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/academico";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado a la Base de Datos");       
            return true;
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e)
        {
            System.out.println("Conexión a la Base de Datos Fallida:\n"+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /*
    * Operaciones para Estudiante
    */

    /**
     * Agrega el Estudiante a la tabla
     * @param estudianteID
     * @param nombre
     * @param direccion
     * @return
     */
    public boolean agregarEstudiante(String estudianteID, String nombre, String direccion)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO estudiantes(estudianteID, nombre, direccion) VALUES ('"+estudianteID+"','"+nombre+"','"+direccion+"')");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Agregar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Busca el Estudiante en la tabla
     * @param estudianteID
     * @return
     */
    public String[] buscarEstudiante(String estudianteID)
    {
        System.out.println("ID Enviado: "+estudianteID);
        
        String[] info = new String[2];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM estudiantes where estudianteID = '"+estudianteID+"'");
            
            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                
                System.out.println("Estudiante:\n"+
                        "Código: "+estudianteID+
                        "Nombre: "+nombre+
                        "Dirección: "+direccion);
                
                info[0] = nombre;
                info[1] = direccion;
            }
            rs.close();
            
            return info;
        }
        catch(Exception e)
        {
            System.out.println("Buscar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /**
     * Envía los datos de un estudiante para modificarlo
     * @param estudianteID
     * @param nombre
     * @param direccion
     * @return
     */
    public boolean modificarEstudiante(String estudianteID, String nombre, String direccion)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE estudiantes SET estudianteID='"+estudianteID+"',nombre='"+nombre+"',direccion='"+direccion+"' WHERE estudianteID='"+estudianteID+"'");
            
            System.out.println("Estudiante Modificado");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Modificar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Envía el ID de un estudiante para Eliminarlo
     * @param estudianteID
     * @return
     */
    public boolean eliminarEstudiante(String estudianteID)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM estudiantes WHERE estudianteID='"+estudianteID+"'");
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Eliminar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Devuelve el número de filas en la tabla estudiantes
     * @return
     */
    public int numeroFilasEstudiantes()
    {
        int filas = 0;
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = cmd.executeQuery("SELECT * FROM estudiantes WHERE 1");
            
            if(rs.last())
            {
                filas = rs.getRow();
                rs.beforeFirst();
            }
            
            rs.close();
            
            System.out.println("Filas: "+filas);
            
            return filas;
        }
        catch(Exception e)
        {
            System.out.println("Consultar Filas Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return filas;
        }
    }
    
    /**
     * Devuelve la información de todos los estudiantes
     * @param numeroFilas
     * @return
     */
    public String[][] InfoTotalEstudiantes(int numeroFilas)
    {
        String[][] info = new String[numeroFilas][3];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM estudiantes where 1");
            
            for(int i=0;i<numeroFilas;i++)
            {
                rs.next();
                info[i][0] = rs.getString("estudianteID");
                info[i][1] = rs.getString("nombre");
                info[i][2] = rs.getString("direccion");
            }
            
            return info;
        }
        catch (Exception e)
        {            
            System.out.println("Consultar Estudiantes Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /*
    * Operaciones para Curso
    */
    
    /**
     * Agrega el Curso a la tabla
     * @param cursoID
     * @param nombre
     * @param creditos
     * @return
     */
    public boolean agregarCurso(String cursoID, String nombre, String creditos)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO cursos(cursoID, nombre, creditos) VALUES ('"+cursoID+"','"+nombre+"','"+creditos+"')");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Agregar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Busca el Curso en la tabla
     * @param cursoID
     * @return
     */
    public String[] buscarCurso(String cursoID)
    {
        System.out.println("ID Enviado: "+cursoID);
        
        String[] info = new String[2];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM cursos where cursoID = '"+cursoID+"'");
            
            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String creditos = rs.getString("creditos");
                
                System.out.println("Curso:\n"+
                        "Código: "+cursoID+
                        "Nombre: "+nombre+
                        "Dirección: "+creditos);
                
                info[0] = nombre;
                info[1] = creditos;
            }
            rs.close();
            
            return info;
        }
        catch(Exception e)
        {
            System.out.println("Buscar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /**
     * Envía los datos del curso a la tabla para Modificarlo
     * @param cursoID
     * @param nombre
     * @param creditos
     * @return
     */
    public boolean modificarCurso(String cursoID, String nombre, String creditos)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE cursos SET cursoID='"+cursoID+"',nombre='"+nombre+"',creditos='"+creditos+"' WHERE cursoID='"+cursoID+"'");
            
            System.out.println("Curso Modificado");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Modificar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Envía el ID del Curso y lo elimina
     * @param cursoID
     * @return
     */
    public boolean eliminarCurso(String cursoID)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM cursos WHERE cursoID='"+cursoID+"'");
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Eliminar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Devuelve el número de filas de los Cursos
     * @return número de filas
     */
    public int numeroFilasCursos()
    {
        int filas = 0;
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = cmd.executeQuery("SELECT * FROM cursos WHERE 1");
            
            if(rs.last())
            {
                filas = rs.getRow();
                rs.beforeFirst();
            }
            
            rs.close();
            
            System.out.println("Filas: "+filas);
            
            return filas;
        }
        catch(Exception e)
        {
            System.out.println("Consultar Filas Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return filas;
        }
    }
    
    /**
     * Devuelve la información de todos los cursos
     * @param numeroFilas
     * @return info[][]
     */
    public String[][] InfoTotalCursos(int numeroFilas)
    {
        String[][] info = new String[numeroFilas][3];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM cursos where 1");
            
            for(int i=0;i<numeroFilas;i++)
            {
                rs.next();
                info[i][0] = rs.getString("cursoID");
                info[i][1] = rs.getString("nombre");
                info[i][2] = rs.getString("creditos");
            }
            
            return info;
        }
        catch (Exception e)
        {            
            System.out.println("Consultar Cursos Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /*
    * Métodos de Matrícula:
    * 
    * Estos métodos se relacionan con la matrícula.
    */

    /**
     * Realiza la matrícula y la añade a la Base de Datos
     * @param estudianteID
     * @param cursoID
     * @return ejecuto
     */
    public boolean agregarMatricula(String estudianteID, String cursoID)
    {
        Statement cmd = null;
        boolean ejecuto;
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO matricula(estudianteID, cursoID) VALUEs ('"+estudianteID+"','"+cursoID+"')");
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Realizar Matrícula Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Elimina la matrícula con el código de estudiante y de curso
     * @param estudianteID
     * @param cursoID
     * @return
     */
    public boolean eliminarMatricula(String estudianteID, String cursoID)
    {
        Statement cmd = null;
        boolean ejecuto;
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM matricula where estudianteID = '"+estudianteID+"' AND cursoID = '"+cursoID+"'");
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Eliminar Matrícula Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Devuelve si existe una matrícula con ese estudiante y ese código. 
     * Si no se encuentra, permite agregar.
     * si se encuentra, entonces solo permite eliminar.
     * @param estudianteID
     * @param cursoID
     * @return si existe matrícula
     */
    public boolean buscarMatricula(String estudianteID, String cursoID)
    {
        Statement cmd = null;
        ResultSet rs = null;
        boolean encontrado = false;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM matricula where estudianteID = '"+estudianteID+"' AND cursoID = '"+cursoID+"'");
            
            while(rs.next())
            {
                encontrado = true;
            }
            
            return encontrado;
        }
        catch(Exception e)
        {
            System.out.println("Buscar Matrícula Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Devuelve el número de filas en la Tabla Matrícula
     * @return
     */
    public int numeroFilasMatricula()
    {
        int filas = 0;
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = cmd.executeQuery("SELECT * FROM matricula WHERE 1");
            
            if(rs.last())
            {
                filas = rs.getRow();
                rs.beforeFirst();
            }
            
            rs.close();
            
            System.out.println("Filas: "+filas);
            
            return filas;
        }
        catch(Exception e)
        {
            System.out.println("Consultar Filas Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return filas;
        }
    }
    
    /**
     * Devuelve la información de las matrículas
     * @param numeroFilas
     * @return
     */
    public String[][] InfoTotalMatriculas(int numeroFilas)
    {
        String[][] info = new String[numeroFilas][2];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM matricula where 1");
            
            for(int i=0;i<numeroFilas;i++)
            {
                rs.next();
                info[i][0] = rs.getString("estudianteID");
                info[i][1] = rs.getString("cursoID");
            }
            
            return info;
        }
        catch (Exception e)
        {            
            System.out.println("Consultar Matrículas Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /**
     * Devuelve la información del estudiante relevante
     * @param numeroFilas
     * @param estudianteIDs
     * @return
     */
    public String[] InfoEstudiantesMatriculas(int numeroFilas, String[][] estudianteIDs)
    {
        String[] info = new String[numeroFilas];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            for(int i=0;i<numeroFilas;i++)
            {
                cmd = con.createStatement();
                rs = cmd.executeQuery("SELECT * FROM estudiantes where estudianteID='"+estudianteIDs[i][0]+"'");
                
                while(rs.next())
                {
                    info[i] = rs.getString("nombre");
                }
            }
            
            return info;
        }
        catch (Exception e)
        {            
            System.out.println("Consultar Estudiantes para Matrículas Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /**
     * Devuelve la información relevante del curso
     * @param numeroFilas
     * @param cursoIDs
     * @return
     */
    public String[][] InfoCursosMatriculas(int numeroFilas, String[][] cursoIDs)
    {
        String[][] info = new String[numeroFilas][2];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            for(int i=0;i<numeroFilas;i++)
            {
                cmd = con.createStatement();
                rs = cmd.executeQuery("SELECT * FROM cursos where cursoID='"+cursoIDs[i][1]+"'");
                
                while(rs.next())
                {
                    info[i][0] = rs.getString("nombre");
                    info[i][1] = rs.getString("creditos");
                }
            }
            
            return info;
        }
        catch (Exception e)
        {            
            System.out.println("Consultar Cursos para Matrículas Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /*
    * Métodos de Usuario
    */
    
    /**
     * Agrega el Usuario a la tabla
     * @param user
     * @param nombre
     * @param pass
     * @return
     */
    public boolean agregarUser(String user, String nombre, String pass)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO usuarios(usuario, nombre, contraseña) VALUES ('"+user+"','"+nombre+"','"+pass+"')");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Agregar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Busca el Usuario en la tabla
     * @param user
     * @return
     */
    public String[] buscarUser(String user)
    {
        System.out.println("ID Enviado: "+user);
        
        String[] info = new String[2];
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuarios where usuario = '"+user+"'");
            
            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String pass = rs.getString("contraseña");
                
                System.out.println("Usuario:\n"+
                        "Usuario: "+user+
                        "Nombre: "+nombre+
                        "Contraseña: "+pass);
                
                info[0] = nombre;
                info[1] = pass;
            }
            rs.close();
            
            return info;
        }
        catch(Exception e)
        {
            System.out.println("Buscar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return info;
        }
    }
    
    /**
     * Envía los datos de un usuario para modificarlo
     * @param user
     * @param nombre
     * @param pass
     * @return si se modificó
     */
    public boolean modificarUser(String user, String nombre, String pass)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE usuarios SET usuario='"+user+"',nombre='"+nombre+"',contraseña='"+pass+"' WHERE usuario='"+user+"'");
            
            System.out.println("Usuario Modificado:\n"+
                    "Usuario: "+user+"\n"+
                    "Nombre: "+nombre+"\n"+
                    "Contraseña: "+pass+"\n");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Modificar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /**
     * Elimina un usuario de la tabla
     * @param user
     * @return si se eliminó
     */
    public boolean eliminarUser(String user)
    {
        Statement cmd = null;
        boolean ejecuto;
        
        try
        {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM usuarios WHERE usuario='"+user+"'");
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Eliminar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return false;
        }
    }
    
    /*
    * Métodos de Login
    */
    
    public int verificarUsers()
    {
        System.out.println("Verificando Usuarios");
        
        int usuarios = 0;
        ResultSet rs = null;
        Statement cmd = null;
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuarios");
            
            while(rs.next())
            {
                usuarios++;
            }
            rs.close();
            
            return usuarios;
        }
        catch(Exception e)
        {
            System.out.println("Buscar Fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return -1;
        }
    }
    
    public String getPass(String user)
    {
        ResultSet rs = null;
        Statement cmd = null;
        String pass = "";
        
        try
        {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuarios where usuario='"+user+"'");
            
            while(rs.next())
            {
                pass = rs.getString("contraseña");
            }
            rs.close();
            
            return pass;
        }
        catch(Exception e)
        {
            System.out.println("Encontrar contraseña fallido:\n"+e.getMessage());
            e.printStackTrace();
            
            return pass;
        }
    }
}
