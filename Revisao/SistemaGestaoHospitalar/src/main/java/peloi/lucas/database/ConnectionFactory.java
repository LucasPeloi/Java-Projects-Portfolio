package peloi.lucas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(){
        String url = "jdbc:h2:./biblioteca_db";
        String user = "sa";
        String password = "";

        try {
            return DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            throw new RuntimeException("Falha ao conectar com o banco de dados" + e.getMessage(), e);
        }
    }

}
