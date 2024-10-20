import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {
    private static final String URL = "jdbc:mysql://localhost/electro";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection conn = null;

    public static Connection connexionBD() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (SQLException e) {
            
        }
        return conn;
    }

    public static void deconnexion() {
        if (conn != null) {
            try {
                conn.close();
                
            } catch (SQLException e) {
                
            }
        }
    }
    
    public static void main(String[] args) {
    	
    	Base.connexionBD();
    }
}
