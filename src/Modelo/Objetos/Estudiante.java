/*
 * Autor: Daniel Somarribas Quir�s
 * b57072
 * 2016
 */
package Modelo.Objetos;

import java.io.Serializable;

/**
 *
 * @author sqdan
 */
public class Estudiante implements Serializable
{
    private String ID;
    private String nombre;
    private String lugar;

    /**
     *
     * @param cedula
     * @param nombre
     * @param lugar
     */
    public Estudiante(String cedula, String nombre, String lugar) {
        this.ID = cedula;
        this.nombre = nombre;
        this.lugar = lugar;
    }
    
    public Estudiante(String[] info)
    {
        ID = info[0];
        nombre = info[1];
        lugar = info[2];
    }

    /**
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getLugar() {
        return lugar;
    }

    /**
     *
     * @param lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    /**
     *
     * @return
     */
    public String[] getInfo()
    {
        String[] info = new String[3];
        
        info[0] = getID();
        info[1] = getNombre();
        info[2] = getLugar();
        
        return info;
    }
}
