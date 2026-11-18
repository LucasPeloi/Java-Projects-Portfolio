package peloi.lucas;

import peloi.lucas.database.ConnectionFactory;

import java.sql.Connection;


public class Main {
    static void main() {
        String qr;
        System.out.println("Tentanco conectar ao banco de dados");

        try(Connection connection = ConnectionFactory.getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        qr = "CREATE TABLE IF NOT EXISTS profissional" +
                "(id int, nome varchar(30), setor varchar(15), " +
                "salario_base double, tipo_profissional varchar(20)," +
                "crm int, cirurgias int, coren int, horas_noturnas int)";

    }
}
