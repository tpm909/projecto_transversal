/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Roma
 */
public class Inscripcion {
    private int idInscripcion;
    private Alumno alumno;
    private Materia materia;
    private LocalDate fecha_inscripcion;
    private double nota;

    // Constructor con todos los atributos
    public Inscripcion(int idInscripcion, Alumno alumno, Materia materia,LocalDate fecha_inscripcion, double nota) {
        this.idInscripcion = idInscripcion;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    // Constructor sin el id
    public Inscripcion(Alumno alumno, Materia materia,LocalDate fecha_inscripcion, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    // Constructor vacío
    public Inscripcion() {
    }

    // Constructor con solo la nota
    public Inscripcion(double nota) {
        this.nota = nota;
    }

    // Getters y Setters
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public LocalDate getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(LocalDate fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
