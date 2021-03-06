/*
 * Tarea 02
 * Sistema Acad�mico con conexi�n a Base de Datos
 * 
 * Estudiante: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Vista.Frame;

import Controlador.CNTRL_MenuPrincipal;
import Controlador.CNTRL_Tipo;

/**
 *
 * @author DanielSQ
 */
public class FRM_Tipo extends javax.swing.JFrame {
    CNTRL_Tipo controlador;
    CNTRL_MenuPrincipal controladorMenu;
    String tipo;
    
    /**
     * Creates new form FRM_Tipo
     * @param controladorMenu
     */
    public FRM_Tipo(CNTRL_MenuPrincipal controladorMenu) 
    {
        this.controladorMenu = controladorMenu;
        initComponents();
        controlador = new CNTRL_Tipo(this);
        addControlador(controlador);
        setLocationRelativeTo(null);
    }
    
    public void addControlador(CNTRL_Tipo controlador)
    {
        jbtn_Texto.addActionListener(controlador);
        jbtn_XML.addActionListener(controlador);
        jbtn_BaseDatos.addActionListener(controlador);
    }
    
    public void sendTipo()
    {        
        controladorMenu.setTipo(tipo);
        controladorMenu.continuar();
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
        sendTipo();
    }
    
    public void apagarBotonSQL()
    {
        jbtn_BaseDatos.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_Text = new javax.swing.JLabel();
        jbtn_Texto = new javax.swing.JButton();
        jbtn_XML = new javax.swing.JButton();
        jbtn_BaseDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_Text.setText("<html><b><font size = 4>Seleccione el Tipo de Base de Datos</font></html>");

        jbtn_Texto.setText("Texto Plano");
        jbtn_Texto.setActionCommand("Texto");

        jbtn_XML.setText("XML");

        jbtn_BaseDatos.setText("mySQL");
        jbtn_BaseDatos.setActionCommand("Base");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbtn_Texto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtn_XML, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtn_BaseDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtn_Texto)
                .addGap(18, 18, 18)
                .addComponent(jbtn_XML)
                .addGap(18, 18, 18)
                .addComponent(jbtn_BaseDatos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtn_BaseDatos;
    private javax.swing.JButton jbtn_Texto;
    private javax.swing.JButton jbtn_XML;
    private javax.swing.JLabel jl_Text;
    // End of variables declaration//GEN-END:variables
}
