package ma.projet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion {

    private static String login    = "root";
    private static String password = "";
    private static String url      = "jdbc:mysql://localhost/demoJDBC";
    private static Connection cn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            cn = DriverManager.getConnection(url, login, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
        }
    }

    public static Connection getCn() {
        return cn;
    }
}