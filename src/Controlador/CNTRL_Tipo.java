/*
 * Tarea 02
 * Sistema Acad�mico con conexi�n a Base de Datos
 * 
 * Estudiante: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Controlador;

import Vista.Frame.FRM_Tipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author DanielSQ
 */
public class CNTRL_Tipo implements ActionListener
{
    FRM_Tipo frame;
    String tipo = "";
    
    public CNTRL_Tipo(FRM_Tipo frame)
    {
        this.frame = frame;   
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Texto"))
        {
            tipo = "Texto";
        }
        
        if(e.getActionCommand().equals("XML"))
        {
            tipo = "XML";
        }
        
        if(e.getActionCommand().equals("Base"))
        {
            tipo = "Base";
        }
        setTipo();
    }
 
    public void setTipo()
    {
        frame.setTipo(tipo);
    }
}
