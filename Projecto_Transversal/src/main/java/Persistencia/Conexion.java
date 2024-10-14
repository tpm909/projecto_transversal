
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static String url = "jdbc:mariadb://localhost/tp3_transversal"; //Nombre de la BD
    private static String usuario = "root";
    private static String password = "";
    private static Conexion conexion = null;

    private Conexion() { // única conexión - Singleton
        try {
            // Esta línea puede ser eliminada en versiones recientes de MariaDB JDBC, ya que se carga automáticamente.
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase Conexion: Error al cargar Driver"); // Error en el driver de MariaDB
        }
    }

    public static Connection getConexion() { // establecer la conexión a la base de datos
        Connection conn = null;
        if (conexion == null) {
            conexion = new Conexion();
        }

        try {
            conn = DriverManager.getConnection(url + "?useLegacyDatetimeCode=false&serverTimezone=UTC" + "&user=" + usuario + "&password=" + password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Clase Conexion: Error de Conexion: " + ex.getMessage());
        }

        return conn;
    }
}
