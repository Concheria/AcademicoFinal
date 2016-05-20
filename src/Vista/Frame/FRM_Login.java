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
import Modelo.Database.ConexionBD;

/**
 *
 * @author DanielSQ
 */
public class FRM_Login extends javax.swing.JFrame {
    ConexionBD baseDatos;
    CNTRL_MenuPrincipal controlador;
    
    /**
     * Creates new form FRM_Login
     * @param baseDatos
     * @param controlador
     */
    public FRM_Login(ConexionBD baseDatos, CNTRL_MenuPrincipal controlador) {
        this.baseDatos = baseDatos;
        this.controlador = controlador;
        initComponents();
        addController(controlador);
    }
    
    /**
     * Añade el controlador al botón de Ingresar
     * @param controlador
     */
    public void addController(CNTRL_MenuPrincipal controlador)
    {
        jbtn_Ingresar.addActionListener(controlador);
    }
    
    /**
     * Devuelve el usuario escrito en el frame
     * @return
     */
    public String getUser()
    {
        return jtf_User.getText();
    }
    
    /**
     * Devuelve la contraseña escrita en el frame
     * @return
     */
    public String getPass()
    {
        return jpf_Pass.getText();
    }
    
    /**
     * Devuelve toda la información escrita en el frame
     * @return
     */
    public String[] getInfo()
    {
        String[] info = new String[2];
        
        info[0] = jtf_User.getText();
        info[1] = jpf_Pass.getText();
        
        return info;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_User = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpf_Pass = new javax.swing.JPasswordField();
        jtf_User = new javax.swing.JTextField();
        jbtn_Ingresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jl_User.setText("<html><b>Usuario</b></html>");

        jLabel2.setText("<html><b>Contraseña</b></html>");

        jpf_Pass.setToolTipText("Contraseña");
        jpf_Pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpf_PassActionPerformed(evt);
            }
        });
        jpf_Pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpf_PassKeyPressed(evt);
            }
        });

        jtf_User.setToolTipText("Usuario");

        jbtn_Ingresar.setText("Ingresar");
        jbtn_Ingresar.setToolTipText("Ingresar al Sistema");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtf_User, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(jpf_Pass)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jbtn_Ingresar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpf_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbtn_Ingresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpf_PassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpf_PassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpf_PassActionPerformed

    private void jpf_PassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpf_PassKeyPressed
        if(evt.getKeyCode() == 10)
        {
            controlador.ingresar();
        }
    }//GEN-LAST:event_jpf_PassKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtn_Ingresar;
    private javax.swing.JLabel jl_User;
    private javax.swing.JPasswordField jpf_Pass;
    private javax.swing.JTextField jtf_User;
    // End of variables declaration//GEN-END:variables
}