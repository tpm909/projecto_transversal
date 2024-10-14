/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;

import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import Persistencia.AlumnoData;
import Persistencia.InscripcionData;
import Persistencia.MateriaData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tomi
 */
public class VistaInscripcion extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    public VistaInscripcion() {
        initComponents();
        CargarAlumnos();
        jbAnular.setEnabled(true);
        jbAnotar.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButton_Grupo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jbAnotar = new javax.swing.JButton();
        MostrarInscriptos = new javax.swing.JRadioButton();
        mostrarNoInscriptos = new javax.swing.JRadioButton();
        jcMateria = new javax.swing.JComboBox<>();
        jlAlumno = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jbAnular = new javax.swing.JButton();
        jlMateria = new javax.swing.JLabel();
        jcAlumnos = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("INSCRIPCIONES A MATERIAS");

        jbAnotar.setText("Anotar");
        jbAnotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnotarActionPerformed(evt);
            }
        });

        radioButton_Grupo.add(MostrarInscriptos);
        MostrarInscriptos.setSelected(true);
        MostrarInscriptos.setText("Materias Inscriptas");
        MostrarInscriptos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarInscriptosActionPerformed(evt);
            }
        });

        radioButton_Grupo.add(mostrarNoInscriptos);
        mostrarNoInscriptos.setText("Materias No Inscriptas");
        mostrarNoInscriptos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarNoInscriptosActionPerformed(evt);
            }
        });

        jlAlumno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlAlumno.setText("Alumnos");

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jbAnular.setText("Anular");
        jbAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularActionPerformed(evt);
            }
        });

        jlMateria.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlMateria.setText("Mostrar materias");

        jcAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcAlumnosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jcAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(MostrarInscriptos)
                                .addGap(30, 30, 30)
                                .addComponent(mostrarNoInscriptos))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(jlAlumno))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbAnotar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbAnular))
                                    .addComponent(jcMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(jlMateria)))
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jlAlumno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jlMateria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MostrarInscriptos)
                    .addComponent(mostrarNoInscriptos))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAnular)
                    .addComponent(jbAnotar))
                .addGap(18, 18, 18)
                .addComponent(jcMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcAlumnosActionPerformed
         if(MostrarInscriptos.isSelected()){
            CargarMateriasInscriptas();
        }else if(mostrarNoInscriptos.isSelected()){
            cargarMateriasNoInscriptas();
        }
    }//GEN-LAST:event_jcAlumnosActionPerformed

    private void jbAnotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnotarActionPerformed
        AnotarInscripcion();
        cargarMateriasNoInscriptas();
    }//GEN-LAST:event_jbAnotarActionPerformed

    private void MostrarInscriptosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarInscriptosActionPerformed
        CargarMateriasInscriptas();
        jbAnular.setEnabled(true);
        jbAnotar.setEnabled(false);
    }//GEN-LAST:event_MostrarInscriptosActionPerformed

    private void mostrarNoInscriptosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarNoInscriptosActionPerformed
        cargarMateriasNoInscriptas();
        jbAnular.setEnabled(false);
        jbAnotar.setEnabled(true);
    }//GEN-LAST:event_mostrarNoInscriptosActionPerformed

    private void jbAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularActionPerformed
        AnularInscripcion();
        CargarMateriasInscriptas();        
    }//GEN-LAST:event_jbAnularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton MostrarInscriptos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbAnotar;
    private javax.swing.JButton jbAnular;
    private javax.swing.JComboBox<Alumno> jcAlumnos;
    private javax.swing.JComboBox<Materia> jcMateria;
    private javax.swing.JLabel jlAlumno;
    private javax.swing.JLabel jlMateria;
    private javax.swing.JRadioButton mostrarNoInscriptos;
    private javax.swing.ButtonGroup radioButton_Grupo;
    // End of variables declaration//GEN-END:variables

    
    private void CargarAlumnos(){
        //Instanciamos AlumnoData, para poder usar sus metodos
        AlumnoData alumnoData = new AlumnoData();
        
        //Obtenemos la lista de alumnos
        List<Alumno> alumnos = alumnoData.listarAlumno();
        
        //Borramos los elementos
        jcAlumnos.removeAllItems();
        
        //Recorremos la lista para agregar al jcomboBox
        for(Alumno unAlumno : alumnos){
            jcAlumnos.addItem(unAlumno);
        }
    }
    
    private int obtenerIdAlumno(){
        // Obtenemos el id del Alumno seleccionado en el comboBox
        Alumno alumnoId = (Alumno) jcAlumnos.getSelectedItem();
        
        //Retornamos el id del alumno seleccionado
        return alumnoId.getId_alumno();
    }
    
    private int obtenerIdMateria(){
        // Obtenemos el id de la materia seleccionado en el comboBox
        Materia materiaId = (Materia) jcMateria.getSelectedItem();
        
        //Retornamos el id de la materia seleccionado
        return materiaId.getId_materia();
    }
    
    private List<Integer> obtenerIdMateriasInscriptas(){
        //Instanciamos MateriaData para poder usar sus metodos
        InscripcionData inscData = new InscripcionData();
        
        //Obtenemos las inscripciones del alumno seleccionado en el comboBox
        List<Inscripcion> inscripcion = inscData.obtenerInscripcionesPorAlumno(obtenerIdAlumno());
        List<Integer> idMaterias = new ArrayList<>();
    
        //Recorremos la lista de inscripciones
        for(Inscripcion unInscripcion : inscripcion){
            //Agregamos los ID de las materias a la lista
            idMaterias.add(unInscripcion.getMateria().getId_materia());
        }
        
        //Retornamos la lista de ID de las materias inscriptas
        return idMaterias;
    }
    
    private List<Materia> obtenerMateriasInscriptas(){
        // Obtener los IDs de las materias inscriptas del alumno
        List<Integer> idMateriasInscriptas = obtenerIdMateriasInscriptas();

        // Creamos una lista para almacenar los objetos Materia
        List<Materia> materiasInscriptas = new ArrayList<>();

        //Instanciamos MateriaData para poder usar sus metodos
        MateriaData materiaData = new MateriaData();

        // Iteramos sobre los IDs de las materias y obtenemos los objetos Materia
        for(int idMateria : idMateriasInscriptas){
            Materia materia = materiaData.obtenerMateriaPorId(idMateria);

            // Si la materia no es null, se agrega a la lista de materias inscriptas
            if(materia != null){
                materiasInscriptas.add(materia);
            }
        }
        return materiasInscriptas; // Devolvemos la lista de objetos Materia
    }
    
    private void CargarMateriasInscriptas() {
        // Obtener la lista de objetos Materia inscriptas
        List<Materia> materiasInscriptas = obtenerMateriasInscriptas();

        // Limpiar el comboBox antes de cargar nuevos elementos
        jcMateria.removeAllItems();

        // Recorrer la lista de materias inscriptas y agregar cada nombre de la materia
        for (Materia materia : materiasInscriptas) {
            jcMateria.addItem(materia);
        }
    }
    
    private void cargarMateriasNoInscriptas() {
        // Obtener el ID del alumno seleccionado en el comboBox de alumnos
        int idAlumno = obtenerIdAlumno();

        // Instanciamos para usar sus metodos
        InscripcionData inscData = new InscripcionData();

        // Obtener la lista de materias no inscriptas del alumno seleccionado
        List<Materia> materiasNoInscriptas = inscData.obtenerMateriasNOCursadas(idAlumno);

        // Limpiar el comboBox antes de cargar nuevos elementos
        jcMateria.removeAllItems();

        // Recorrer la lista de materias no inscriptas y agregar cada una al comboBox
        for (Materia materia : materiasNoInscriptas) {
            jcMateria.addItem(materia);
        }
    }
    
    private void AnotarInscripcion(){
        //Obtenemos el alumno y la materia, usando los metodos creados anteriormente
        int idAlumno = obtenerIdAlumno();
        int idMateria = obtenerIdMateria();
        
        //Instanciamos para obtener los metodos
        AlumnoData alumnoData = new AlumnoData();
        MateriaData materiaData = new MateriaData();
        Inscripcion insc = new Inscripcion();
        InscripcionData inscData = new InscripcionData();
        
        
        //Creamos un objeto alumno y materia
        Alumno alumno = alumnoData.obtenerAlumnoPorId(idAlumno);
        Materia materia = materiaData.obtenerMateriaPorId(idMateria);
        
        insc.setAlumno(alumno);
        insc.setMateria(materia);
        
        //Utilizamos todo lo anterior, para inscribir el alumno a la materia
        inscData.guardarInscripcion(insc);
            }
    
    private void AnularInscripcion(){
        //Obtenemos el alumno y la materia, usando los metodos creados anteriormente
        int idAlumno = obtenerIdAlumno();
        int idMateria = obtenerIdMateria();
        InscripcionData inscData = new InscripcionData();

        inscData.borrarInscripcionMateriaAlumno(idAlumno, idMateria);
        

    }
    
}
