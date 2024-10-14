package Persistencia;

import Modelo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlumnoData {
    private Connection con = null;

    public AlumnoData() {
        con = Conexion.getConexion();
    }

    public void agregarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno(nombre, apellido, dni, fecha_nacimiento, email, telefono, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Convertir la fecha de LocalDate a java.sql.Date
            java.sql.Date sqlFechaNacimiento = java.sql.Date.valueOf(alumno.getFecha_nacimiento());

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getDni());
            ps.setDate(4, sqlFechaNacimiento); // Insertar la fecha
            ps.setString(5, alumno.getEmail());
            ps.setLong(6, alumno.getTelefono());
            ps.setBoolean(7, alumno.isEstado());

            // Ejecutar la actualización
            ps.executeUpdate();

            // Manejar claves generadas (si es necesario)
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setId_alumno(rs.getInt(1));
            }

            rs.close();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alumno agregado exitosamente!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar un alumno.");
        }
    }

    public Alumno buscarAlumno(int dni) {
        String sql = "SELECT * FROM alumno WHERE dni = ?";

        Alumno alumno = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate()); // Convertir a LocalDate
                alumno.setEmail(rs.getString("email"));
                alumno.setTelefono(rs.getLong("telefono"));
                alumno.setEstado(rs.getBoolean("estado"));
            }

            rs.close();
            ps.close();

            if (alumno != null) {
                JOptionPane.showMessageDialog(null, "Alumno encontrado.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró alumno con el DNI proporcionado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el alumno: " + e.getMessage());
        }

        return alumno;
    }

    public void modificarAlumno(int id_alumno, String nombre, String apellido, int dni, LocalDate fecha_nacimiento, String email, long telefono, boolean estado) {
        String sql = "UPDATE alumno SET nombre = ?, apellido = ?, dni = ?, fecha_nacimiento = ?, email = ?, telefono = ?, estado = ? WHERE id_alumno = ?";

        try {
            // Convertir la fecha de LocalDate a java.sql.Date
            java.sql.Date sqlFechaNacimiento = java.sql.Date.valueOf(fecha_nacimiento);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, dni);
            ps.setDate(4, sqlFechaNacimiento); // Actualizar la fecha
            ps.setString(5, email);
            ps.setLong(6, telefono);
            ps.setBoolean(7, estado);
            ps.setInt(8, id_alumno);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Alumno modificado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe alumno con ese ID.");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar alumno: " + ex.getMessage());
        }
    }

    public List<Alumno> listarAlumno() {
        ArrayList<Alumno> listarAlumno = new ArrayList<>();

        String sql = "SELECT * FROM alumno";

        try {
            // Preparar la consulta
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Iterar sobre los resultados
            while (rs.next()) {
                // Crear un nuevo objeto Alumno
                Alumno alumno = new Alumno();

                // Setear los valores de los campos
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));

                // Convertir java.sql.Date a java.time.LocalDate
                alumno.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate()); // Convertir a LocalDate

                alumno.setEmail(rs.getString("email"));
                alumno.setTelefono(rs.getLong("telefono"));
                alumno.setEstado(rs.getBoolean("estado"));

                // Agregar el alumno a la lista
                listarAlumno.add(alumno);
            }

            // Cerrar los recursos
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener alumnos: " + ex.getMessage());
        }

        return listarAlumno;
    }

    public void darDeBajaAlumno(int id) {
        String sql = "UPDATE alumno SET estado = ? WHERE id_alumno = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El alumno ha sido dado de baja correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno con el ID proporcionado.");
            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al dar de baja al alumno: " + e.getMessage());
        }
    }

    public void darDeAltaAlumno(int id) {
        String sql = "UPDATE alumno SET estado = ? WHERE id_alumno = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El alumno ha sido dado de alta correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno con el ID proporcionado.");
            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al dar de alta al alumno: " + e.getMessage());
        }
    }

    public void eliminarAlumno(int id) {
        try {
            String sql = "DELETE FROM alumno WHERE id_Alumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "El alumno fue eliminado de forma permanente correctamente!");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún alumno con el ID: " + id);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al acceder a la tabla Alumno!");
        }
    }

    public Alumno obtenerAlumnoPorId(int id) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno WHERE id_alumno = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                alumno.setEmail(rs.getString("email"));
                alumno.setTelefono(rs.getLong("telefono"));
                alumno.setEstado(rs.getBoolean("estado"));
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el alumno!");
        }

        return alumno;
    }
}

