package peloi.lucas.dao;

import peloi.lucas.database.ConnectionFactory;
import peloi.lucas.model.Partida;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO implements PartidaDAOInterface{
    private Connection cnn;

    public PartidaDAO() {
        cnn = ConnectionFactory.getConnection();

        try {
            String sql = "CREATE TABLE IF NOT EXISTS partida(id int PRIMARY KEY AUTO_INCREMENT, duracaoMinutos int, vencedor int)";

            Statement statement = cnn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void salvar(Partida p) {
        String sql;

        try {
            Statement statement = cnn.createStatement();

            sql = "insert into partida (duracaoMinutos, vencedor)\n" +
                    "values (" + p.getDuracaoMinutos() + ", " + p.getVencedor() + ")";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(long id) {

    }

    @Override
    public List<Partida> buscarTodas() {
        return List.of();
    }

    public void consultarPartidas(){
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> duracaoMinutos = new ArrayList<String>();
        ArrayList<String> vencedor = new ArrayList<String>();
        int index = 0 ;
        try {
            String sql;
            Statement statement = cnn.createStatement();
            ResultSet rt;

            sql = "SELECT id, duracaoMinutos, vencedor FROM PARTIDA";

            rt = statement.executeQuery(sql);
            while(rt.next()){
                id.add(rt.getString("id"));
                duracaoMinutos.add(rt.getString("duracaoMinutos"));
                vencedor.add(rt.getString("vencedor"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : id) {
            System.out.println("ID: "+ s + " Duração " + duracaoMinutos.get(index) + " Vencedorid: " + vencedor.get(index) );

            index++;
        }
    }
}
