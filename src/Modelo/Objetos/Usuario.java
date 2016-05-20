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
public class Usuario implements Serializable
{
    private String user;
    private String pass;
    private String nombre;

    /**
     *
     * @param user
     * @param pass
     */
    public Usuario(String user, String pass, String nombre) {
        this.user = user;
        this.pass = pass;
        this.nombre = nombre;
    }
    
        public Usuario(String[] infoUsuario) {
        this.user = infoUsuario[0];
        this.nombre = infoUsuario[1];
        this.pass = infoUsuario[2];
    }

    /**
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param User
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     *
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     *
     * @return
     */
    public String[] getInfo()
    {
        String[] info = new String[3];
        
        info[0] = getUser();
        info[1] = getNombre();
        info[2] = getPass();
        
        return info;
    }
}
