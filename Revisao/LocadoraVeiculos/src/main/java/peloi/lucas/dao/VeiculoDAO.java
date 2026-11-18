package peloi.lucas.dao;

import peloi.lucas.database.ConnectionFactory;
import peloi.lucas.model.CarroEletrico;
import peloi.lucas.model.VeiculoEco;

import java.sql.*;

public class VeiculoDAO {
    private Connection connection;

    public VeiculoDAO() {
        this.connection = ConnectionFactory.getConnection();

        try {
            String sql = "CREATE TABLE IF NOT EXISTS veiculo (chassi VARCHAR(20),taxaDiaria double,autonomiaBateriaAtual int, tipo VARCHAR(20)); ";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarVeiculosEco(VeiculoEco veiculoEco){
        try {
            String sql = "INSERT INTO veiculo(chassi,taxaDiaria,autonomiaBateriaAtual,tipo) " +
                         "VALUES(?,?,?,?); ";
            String tipo;
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,veiculoEco.getChassi());
            statement.setDouble(2,veiculoEco.getTaxaDiaria());
            statement.setInt(3,veiculoEco.getAutonomiaBateriaAtual());
            if (veiculoEco instanceof CarroEletrico){
                tipo = "CARRO";
            }else{
                tipo = "PATINETE";
            }
            statement.setString(4,tipo);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("ERRO: "+ e.getMessage(), e);
        }
    }

    public int contarPatinetesCadastrados(){
        int numeroPatinetes = 0;
        ResultSet resultSet;

        try {
            String sql = "SELECT COUNT(*) as NumeroPatinetes " +
                         "FROM Veiculo " +
                         "WHERE tipo = 'PATINETE'; ";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                numeroPatinetes = resultSet.getInt(1);
            }


        }catch(SQLException e){
            throw new RuntimeException("Erro ao contar Patinetes \n" + e.getMessage(),e);
        }

        return numeroPatinetes;
    }
}
