/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Roma
 */
public class MateriaData {
     private Connection con;
    
    public MateriaData() {
        this.con = Conexion.getConexion();
    }
    public void agregarMateria(Materia materia){
        String sql = "INSERT INTO materia( nombre_materia, year, estado) VALUES (? ,?, ? )";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, materia.getNombre_materia());
            ps.setString(2, materia.getYear());
            ps.setBoolean(3, materia.isEstado());
            
            // Ejecutar la actualización
            ps.executeUpdate();
        
            // Manejar claves generadas (si es necesario)
            ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    materia.setId_materia(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "La materia ha sido agregada correctamente.");
            }
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la materia!");
        }
    }
    
    public Materia buscarMateria(String nombre_materia){  
        String sql = "SELECT * FROM materia WHERE nombre_materia= ?";
    
        Materia materia = null; // Inicializamos el materia como nulo para devolverlo al final si lo encontramos

        try {
            // Preparar la consulta SQL
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre_materia);  // Asignar el nombre_materia al parámetro
        
            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();
        
            // Si se encuentra un resultado
            if (rs.next()) {
                // Crear un nuevo objeto materia
                materia = new Materia();
            
                // Asignar los valores recuperados al objeto Alumno
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre_materia(rs.getString("nombre_materia"));
                materia.setYear(rs.getString("year"));
                materia.setEstado(rs.getBoolean("estado")); // Asignar el estado
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una materia con el ID proporcionado.");
            }
        
            // Cerrar los recursos
            rs.close();
            ps.close();
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar la materia!");
        }

        // Devolver materia encontrada (o null si no se encontró)
        return materia;
    }

    public void modificarMateria(int id_materia, String nombre_materia, String year, boolean estado) {
    String sql = "UPDATE materia SET nombre_materia = ?, year = ?, estado = ? WHERE id_materia = ?";

        try {
            // Preparar la declaración SQL
            PreparedStatement ps = con.prepareStatement(sql);

            // Asignar los parámetros en el orden correcto
            ps.setString(1, nombre_materia);
            ps.setString(2, year);
            ps.setBoolean(3, estado);
            ps.setInt(4, id_materia); // Aquí estás usando el id_materia para buscar el registro correcto

            // Ejecutar la actualización
            int filasAfectadas = ps.executeUpdate();

            // Validar si se modificó alguna fila
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Materia modificada exitosamente.");

            } else {
                JOptionPane.showMessageDialog(null, "No existe materia con ese ID.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la materia!");

        }
    }

    
    public List<Materia> listarMateria(){
        ArrayList<Materia> listarMateria = new ArrayList<>();
        
        String sql = "SELECT * FROM Materia";
        
        try {
            // Preparar la consulta
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        
        // Iterar sobre los resultados
        while (rs.next()) {
            // Crear un nuevo objeto Materia
            Materia materia = new Materia();
            
            // Setear los valores de los campos
            materia.setId_materia(rs.getInt("id_materia"));
            materia.setNombre_materia(rs.getString("nombre_materia"));
            materia.setYear(rs.getString("year"));
            materia.setEstado(rs.getBoolean("estado"));
            
            // Agregar el alumno a la lista
            listarMateria.add(materia);
        }
        
       
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Materias!");
        }
    
        return listarMateria;
    }
    
    public void darDeBajaMateria(int id) { 
    String sql = "UPDATE materia SET estado = ? WHERE id_Materia = ?";
    
        try {
            // Preparar la declaración SQL
            PreparedStatement ps = con.prepareStatement(sql);
        
            // Cambiar el estado del materia a false (0 = inactivo)
            ps.setBoolean(1, false);  // o puedes usar ps.setInt(1, 0);
        
            // Establecer el id de la materia para identificarlo
            ps.setInt(2, id);
        
            // Ejecutar la actualización
            int filasAfectadas = ps.executeUpdate();
        
            // Verificar si se actualizó correctamente
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La materia ha sido dada de baja correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la materia con el nombre proporcionado.");
            }      
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al dar de baja la materia!");
        }
    }
    
    public void darDeAltaMateria(int id) { 
    String sql = "UPDATE materia SET estado = ? WHERE id_materia = ?";
    
        try {
            // Preparar la declaración SQL
            PreparedStatement ps = con.prepareStatement(sql);
        
            // Cambiar el estado del materia a false (0 = inactivo)
            ps.setBoolean(1, true);  // o puedes usar ps.setInt(1, 0);
        
            // Establecer el id de la materia para identificarlo
            ps.setInt(2, id);
        
            // Ejecutar la actualización
            int filasAfectadas = ps.executeUpdate();
        
            // Verificar si se actualizó correctamente
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La materia ha sido dada de alta correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la materia con el nombre proporcionado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al dar de alta la materia!");
        }
    }
    
    public void eliminarMateria(int id){ //No es recomendable borrar con el DELETE, despues no podemos recuperar los datos
        try{
            String sql = "DELETE FROM materia WHERE id_Materia = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Reemplazamos el valor de la consulta (?)
            int fila = ps.executeUpdate(); //Si se encuentra un materia con ese ID, Me va a mostrar la cantidad de filas afectadas, En este caso me deberia dar 1
            
            if(fila == 1){ //Si se encuentra, se eliminara
                JOptionPane.showMessageDialog(null, "El materia fue eliminado de forma permanente correctamente!");
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro ningun materia con el ID: "+id);
            }
            
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Ocurrio un error al acceder a la tabla materia");
            }
        }
    
    public Materia obtenerMateriaPorId(int id) {
            Materia materia = null;
            String sql = "SELECT * FROM materia WHERE id_materia = ?";
    
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
    
                if (rs.next()) {
                    materia = new Materia();
                    materia.setId_materia(rs.getInt("id_materia"));
                    materia.setNombre_materia(rs.getString("nombre_materia"));
                    materia.setYear(rs.getString("year"));
                    
                }
    
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener la materia!");
            }    
            return materia;
        }
}
