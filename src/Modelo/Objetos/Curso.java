package Modelo.Objetos;

import java.io.Serializable;

/**
 * Objeto Curso
 * @author daniel
 */
public class Curso implements Serializable
{
    //Define las Variables:
    private String nombre;
    private String codigo;
    private int creditos;

    //Método Constructor: Set a todas las Variables
    public Curso(String codigo, String nombre, int creditos)
    {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the creditos
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @param creditos the creditos to set
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    //Devuelve un String con toda la información
    public String[] getInfo()
    {
        String[] info = new String[3];
        
        info[0] = getCodigo();
        info[1] = getNombre();
        info[2] = ""+getCreditos();
        
        return info;
    }
    
}
