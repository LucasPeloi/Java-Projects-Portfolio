package peloi.lucas.dao;

import peloi.lucas.database.ConnectionFactory;
import peloi.lucas.model.Guerreiro;
import peloi.lucas.model.Mago;
import peloi.lucas.model.Personagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    public void salvar(Personagem p) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String classe_personagem = "";
            int armadura = 0;
            int mana = 0;

            String sql = "INSERT INTO personagem(nome, vida, poder_ataque, classe_personagem, armadura, mana) " +
                    "VALUES (?,?,?,?,?,?); ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (p instanceof Guerreiro guerreiro) {
                armadura = guerreiro.getArmadura();
                classe_personagem = "GUERREIRO";
                preparedStatement.setNull(6,Types.INTEGER);
            }
            if (p instanceof Mago mago) {
                mana = mago.getMana();
                classe_personagem = "MAGO";
                preparedStatement.setNull(5,Types.INTEGER);
            }

            preparedStatement.setString(1, p.getNome());
            preparedStatement.setInt(2, p.getVida());
            preparedStatement.setInt(3, p.getPoderAtaque());
            preparedStatement.setString(4, classe_personagem);
            preparedStatement.setInt(5, armadura);
            preparedStatement.setInt(6, mana);
            preparedStatement.executeUpdate();
            System.out.println("Inserido Personagem com Sucesso!");
        } catch (SQLException e) {
            System.out.println("ERRO em salvar personagem\n" + e.getMessage());
            ;
        }
    }

    public List<Personagem> buscarTodos() {
        List<Personagem> personagens = new ArrayList<>();

        String sql = "SELECT nome, vida, poder_ataque, classe_personagem, armadura, mana " +
                "FROM personagem; ";

        try (Connection conn = ConnectionFactory.getConnection()) {
            int vida, poder_ataque;
            String nome, classe_personagem;

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nome = resultSet.getString("nome");
                vida = resultSet.getInt("vida");
                poder_ataque = resultSet.getInt("poder_ataque");
                classe_personagem = resultSet.getString("classe_personagem");

                if (classe_personagem.compareTo("GUERREIRO") == 0){
                    Guerreiro guerreiro = new Guerreiro(nome,vida,poder_ataque,resultSet.getInt("armadura"));
                    personagens.add(guerreiro);
                }else if (classe_personagem.compareTo("MAGO") == 0){
                    Mago mago = new Mago(nome,vida,poder_ataque,resultSet.getInt("mana"));
                    personagens.add(mago);
                }
            }
            return personagens;
        } catch (SQLException e) {
            System.out.println("ERRO ao buscar registros\n" + e.getMessage());
        }
        return null;
    }
}
