package peloi.lucas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        String url = "jdbc:h2:./database";
        String user = "ra";
        String password = "";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar Banco de dados \n" + e.getMessage(), e);
        }
    }

}
