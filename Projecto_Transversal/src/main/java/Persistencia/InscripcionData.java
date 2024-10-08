/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roma
 */
public class InscripcionData {
    private Connection con;
    private MateriaData matData;
    private AlumnoData aluData;
    
    // Constructor
    public InscripcionData() {
        this.con = Conexion.getConexion();
        this.aluData = new AlumnoData();
        this.matData = new MateriaData();
    }

    public InscripcionData(Connection conexion, MateriaData matData, AlumnoData aluData) {
        this.con = conexion;
        this.matData = matData;
        this.aluData = aluData;
    }

    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion (id_alumno, id_materia, fecha_inscripcion, nota ) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, insc.getAlumno().getId_alumno());
            ps.setInt(2, insc.getMateria().getId_materia());
            ps.setDate(3, Date.valueOf(LocalDate.now())); // Guardar la fecha de inscripción actual
            ps.setDouble(4, insc.getNota());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al guardar la inscripción: " + ex.getMessage());
        }
    }

    // Método para obtener todas las inscripciones
    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("id_inscripcion"));
                insc.setAlumno(aluData.obtenerAlumnoPorId(rs.getInt("id_alumno")));
                insc.setMateria(matData.obtenerMateriaPorId(rs.getInt("id_materia")));
                insc.setNota(rs.getDouble("nota"));
                insc.setFecha_inscripcion(rs.getDate("fecha_inscripcion").toLocalDate());
                inscripciones.add(insc);
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener inscripciones: " + ex.getMessage());
        }

        return inscripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id_alumno) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion WHERE id_alumno = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("id_inscripcion"));
                insc.setAlumno(aluData.obtenerAlumnoPorId(id_alumno));
                insc.setMateria(matData.obtenerMateriaPorId(rs.getInt("id_materia")));
                insc.setNota(rs.getDouble("nota"));
                insc.setFecha_inscripcion(rs.getDate("fecha_inscripcion").toLocalDate());
                inscripciones.add(insc);
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener inscripciones por alumno: " + ex.getMessage());
        }

        return inscripciones;
    }

    public void actualizarNota(int id_alumno, int id_materia, LocalDate fecha_inscripcion, double nota) { 
        String sql = "UPDATE inscripcion SET nota = ?, fecha_inscripcion = ? WHERE id_alumno = ? AND id_materia = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setDate(2, java.sql.Date.valueOf(fecha_inscripcion));
            ps.setInt(3, id_alumno);
            ps.setInt(4, id_materia);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la nota: " + ex.getMessage());
        }
    }


    public List<Materia> obtenerMateriasCursadas(int id_alumno) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT m.* FROM materia m "
                   + "JOIN inscripcion i ON m.id_materia = i.id_materia "
                   + "WHERE i.id_alumno = ? AND i.nota IS NOT NULL";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre_materia(rs.getString("nombre_materia"));
                materia.setYear(rs.getString("year"));
                materias.add(materia);
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener materias cursadas: " + ex.getMessage());
        }

        return materias;
    }

    public List<Materia> obtenerMateriasNOCursadas(int id_alumno) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT m.* FROM materia m "
                   + "WHERE m.id_materia NOT IN (SELECT id_materia FROM inscripcion WHERE id_alumno = ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre_materia(rs.getString("nombre_materia"));
                materia.setYear(rs.getString("year"));
                materias.add(materia);
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener materias no cursadas: " + ex.getMessage());
        }

        return materias;
    }

    public void borrarInscripcionMateriaAlumno(int id_alumno, int id_materia) {
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al borrar la inscripción: " + ex.getMessage());
        }
    }

    public List<Alumno> obtenerAlumnosXMateria(int id_materia) { 
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT a.* FROM alumno a "
                   + "JOIN inscripcion i ON a.id_alumno = i.id_alumno "
                   + "WHERE i.id_materia = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                alumno.setEmail(rs.getString("email"));
                alumno.setTelefono(rs.getLong("telefono"));
                alumno.setEstado(rs.getBoolean("estado"));

                alumnos.add(alumno);
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener alumnos por materia: " + ex.getMessage());
        }

        return alumnos;
    }


    
}
  



