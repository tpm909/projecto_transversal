/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Roma
 */
public class Materia {
    private int id_materia =-1;
    private String nombre_materia;
    private String year;
    private boolean estado;

    public Materia() {
    }

    public Materia(String nombre_materia, String year, boolean estado) {
        this.nombre_materia = nombre_materia;
        this.year = year;
        this.estado = estado;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_materia;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materia other = (Materia) obj;
        return this.id_materia == other.id_materia;
    }
     @Override
    public String toString() {
        return nombre_materia + " " + year;
    }
    
}


