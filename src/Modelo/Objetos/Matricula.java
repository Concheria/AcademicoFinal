package Modelo.Objetos;

import java.io.Serializable;

/**
 * Objeto Matrícula
 * @author daniel
 */
public class Matricula implements Serializable
{
    //Define las variables
    private String iD;
    private String codigo;

    //Método Constructor: Set a todas las Variables
    public Matricula(String estudiante, String curso)
    {
        this.iD = estudiante;
        this.codigo = curso;
    }
    
    /**
     * @return the iD
     */
    public String getID() {
        return iD;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param estudiante the iD to set
     */
    public void setID(String estudiante) {
        this.iD = estudiante;
    }

    /**
     * @param curso the codigo to set
     */
    public void setCodigo(String curso) {
        this.codigo = curso;
    }
    
    //Devuelve un String con toda la información
    public String[] getInfo()
    {        
        String [] info = new String[2];
        
        info[0] = getID();
        info[1] = getCodigo();
        
        return info;
    }
}