package peloi.lucas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void criarTabelas(){
        String sql = "CREATE TABLE IF NOT EXISTS Dispositivo (macAddress VARCHAR(20), consumoWatts int, statusLigado boolean, tipo_dispositivo VARCHAR(20)); ";

        try (Connection connection = ConnectionFactory.getConnection(); Statement statement = connection.createStatement()){
                statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
