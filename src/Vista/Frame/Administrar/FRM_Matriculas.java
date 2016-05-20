/*
 * Tarea 02
 * Sistema Acad�mico con conexi�n a Base de Datos
 * 
 * Estudiante: Daniel Somarribas Quir�s
 * Carnet: b57072
 * Mayo, 2016
 */
package Vista.Frame.Administrar;

import Controlador.CNTRL_Matriculas;
import Controlador.CNTRL_MenuPrincipal;
import Modelo.Database.ConexionBD;
import Modelo.Metodos.MTDS_Matriculas;

/**
 *
 * @author Daniel
 */
public class FRM_Matriculas extends javax.swing.JFrame {
    ConexionBD baseDatos;
    CNTRL_Matriculas controlador;
    CNTRL_MenuPrincipal controladorMenu;
    String tipo = "";
    
    boolean estudianteEncontrado = false;
    boolean cursoEncontrado = false;
    boolean ambosEncontrados = false;
    
    /**
     * Creates new form FRM_VerMatriculas
     * @param baseDatos
     */
    public FRM_Matriculas(ConexionBD baseDatos, CNTRL_MenuPrincipal controladorMenu) 
    {
        this.controladorMenu = controladorMenu;
        controlador = new CNTRL_Matriculas(baseDatos, this, controladorMenu);
        this.baseDatos = baseDatos;
        initComponents();
        addController();
        disableBotones();
        fieldsNoEditables();
    }
    
    public void addController()
    {
        jp_InfoEstudiante.addController(controlador, this);
        jp_InfoCurso.addController(controlador, this);
        jp_Botones.addController(controlador);
        jbtn_Reiniciar.addActionListener(controlador);
    }

    /**
     * Devuelve el Código de Estudiante escrito en el frame
     * @return estudianteID
     */
    public String getEstudianteID()
    {        
        return jp_InfoEstudiante.getEstudianteID();
    }
    
    /**
     * Devuelve el Código de Curso escrito en el frame
     * @return cursoID
     */
    public String getCursoID()
    {        
        return jp_InfoCurso.getCursoID();
    }
    
    /**
     * Devuelve ambos códigos
     * @return ambos IDs
     */
    public String[] getIDs()
    {
        String[] ID = new String[2];
        
        ID[0] = getEstudianteID();
        ID[1] = getCursoID();
        
        return ID;
    }
    
    
    /**
     * Devuelve la información completa de Estudiante escrita en el frame
     * @return
     */
    public String[] getEstudianteInfo()
    {
        return jp_InfoEstudiante.getInfo();
    }
    
    /**
     * Devuelve la información completa de Curso escrita en el frame
     * @return
     */
    public String[] getCursoInfo()
    {
        return jp_InfoCurso.getInfo();
    }
    
    /**
     * Devuelvo ambas informaciones
     * @return infos
     */
    public String[][] getInfos()
    {
        String[][] info = new String[2][3];
        
        info[0] = getEstudianteInfo();
        info[1] = getCursoInfo();
        
        return info;
    }
    
    /**
     * Llena los campos de Estudiante con la información obtenida en la base de datos
     * @param info
     */
    public void fillFieldsEstudiante(String[] info)
    {
        jp_InfoEstudiante.fillFields(info);
    }
    
    /**
     * Llena los campos de Curso con la información obtenida en la base de datos
     * @param info
     */
    public void fillFieldsCurso(String[] info)
    {
        jp_InfoCurso.fillFields(info);
    }
    
    /**
     * Limpia todos los campos de Estudiante
     */
    public void clearFieldsEstudiante()
    {
        jp_InfoEstudiante.clearFields();
    }
    
    /**
     * Limpia todos los campos de Curso
     */
    public void clearFieldsCurso()
    {
        jp_InfoCurso.clearFields();
    }
    
    /**
     * Limpia todos los campos
     */
    public void clearFields()
    {
        jp_InfoEstudiante.clearFields();
        jp_InfoCurso.clearFields();
    }
    
    /**
     * Limpia los campos parcialmente de Estudiantes
     */
    public void clearPartialFieldsEstudiante()
    {
        jp_InfoEstudiante.clearPartialFields();
    }
    
    /**
     * Limpia los campos parcial de Cursos
     */
    public void clearPartialFieldsCurso()
    {
        jp_InfoCurso.clearPartialFields();
    }
    
    /**
     * Limpia los campos parcialmente de ambos frames
     */
    public void clearPartialFields()
    {
        jp_InfoEstudiante.clearPartialFields();
        jp_InfoCurso.clearPartialFields();
    }
    
    /**
     * Pone un booleano como positivo si encuentra el estudiante
     * @param encontrado
     */
    public void estudianteEncontrado(boolean encontrado)
    {
        estudianteEncontrado = encontrado;
        System.out.println("Estudiante Encontrado: "+estudianteEncontrado);
        checkAmbosEncontrados();
    }
    
    /**
     * Pone un booleano como positivo si encuentra el curso
     * @param encontrado
     */
    public void cursoEncontrado(boolean encontrado)
    {
        cursoEncontrado = encontrado;
        System.out.println("Curso Encontrado: "+cursoEncontrado);      
        checkAmbosEncontrados();
    }
    
    /**
     * Busca que se encuentren ambas entidades
     */
    public void checkAmbosEncontrados()
    {
        if((estudianteEncontrado == true) && (cursoEncontrado == true))
        {
            ambosEncontrados = true;
            if(controlador.buscarMatricula() == false)
            {
                if((!"".equals(jp_InfoCurso.getCursoID())) && (!"".equals(jp_InfoEstudiante.getEstudianteID())))
                {
                    agregarConf();
                }
            }
            else
            {
                eliminarConf();
            }
        }
        else
        {
            ambosEncontrados = false;
            disableBotones();
        }
        
        System.out.println("Ambos Encontrados: "+ambosEncontrados);
    }
    
    public MTDS_Matriculas getMetodos()
    {
        return controlador.getMetodos();
    }
    
    /**
     * Habilita únicamente Agregar
     */
    public boolean agregarConf()
    {
        jp_Botones.agregarConf();
        
        return true;
    }
    
    /*
    * Habilita únicamente Eliminar
    */
    public boolean eliminarConf()
    {
        jp_Botones.eliminarConf();
        
        return true;
    }
    
    /**
     * Deshabilita Ambos Botones
     */
    public boolean disableBotones()
    {
        jp_Botones.disableBoth();
        return true;
    }
    
    public boolean fieldsNoEditables()
    {
        jp_InfoEstudiante.setNoEditable();
        jp_InfoCurso.setNoEditable();
        
        return true;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
        controlador.setTipo(tipo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_Estudiante = new javax.swing.JLabel();
        jl_Curso = new javax.swing.JLabel();
        jp_InfoEstudiante = new Vista.Panel.Info.JP_InfoEstudiante();
        jp_InfoCurso = new Vista.Panel.Info.JP_InfoCurso();
        jp_Botones = new Vista.Panel.Botones.JP_BotonesDos();
        jbtn_Reiniciar = new javax.swing.JButton();

        setTitle("Administrar Matrículas");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jl_Estudiante.setText("<html><b>Estudiante</b></html>");

        jl_Curso.setText("<html><b>Curso</b></html>");

        jp_Botones.setToolTipText("");

        jbtn_Reiniciar.setText("Reiniciar");
        jbtn_Reiniciar.setToolTipText("Reiniciar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jp_InfoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_Estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_Curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jp_InfoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtn_Reiniciar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtn_Reiniciar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_Estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_Curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jp_InfoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jp_InfoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == 10)
        {
            if(agregarConf())
            {
                controlador.realizar();
            }
            if(eliminarConf())
            {
                controlador.realizar();
            }
        }
    }//GEN-LAST:event_formKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtn_Reiniciar;
    private javax.swing.JLabel jl_Curso;
    private javax.swing.JLabel jl_Estudiante;
    private Vista.Panel.Botones.JP_BotonesDos jp_Botones;
    private Vista.Panel.Info.JP_InfoCurso jp_InfoCurso;
    private Vista.Panel.Info.JP_InfoEstudiante jp_InfoEstudiante;
    // End of variables declaration//GEN-END:variables
}
