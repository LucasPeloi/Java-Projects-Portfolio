package peloi.lucas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitialize {
    public static void criarTabelas(){
        try(Connection connection = ConnectionFactory.getConnection(); Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS personagem " +
                    "(id int AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(20), vida int, poder_ataque int, classe_personagem VARCHAR(20), armadura int, mana int)";
            statement.execute(sql);
            System.out.println("Tabelas criadas com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
