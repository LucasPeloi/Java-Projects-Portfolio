package peloi.lucas.dao;

import peloi.lucas.database.ConnectionFactory;
import peloi.lucas.model.ArCondicionado;
import peloi.lucas.model.DispositivoInteligente;
import peloi.lucas.model.LampadaRGB;

import java.sql.*;

public class DispositivoDAO {
    public void inserir(DispositivoInteligente disp) {
        String sql = ("INSERT INTO Dispositivo(macAddress, consumoWatts, statusLigado, tipo_dispositivo) " +
                "VALUES (?,?,?,?); ");
        String tipo_dispositivo;

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, disp.getMacAddresss());
            preparedStatement.setInt(2, disp.getConsumoWatts());
            preparedStatement.setBoolean(3, disp.isLigado());

            if (disp instanceof ArCondicionado) {
                tipo_dispositivo = "ArCondicionado";
            } else if (disp instanceof LampadaRGB) {
                tipo_dispositivo = "LampadaRGB";
            } else {
                tipo_dispositivo = "";
            }
            preparedStatement.setString(4, tipo_dispositivo);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterarStatus(String macAddress, boolean novoStatus) {
        String sql = "UPDATE Dispositivo SET statusLigado = ? " +
                "WHERE macAddress = ?; ";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, novoStatus);
            statement.setString(2, macAddress);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerDispositivo(String macAddress) {
        String sql = "DELETE Dispositivo " +
                "WHERE ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, macAddress);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listarDispostivos() {
        String sql = "SELECT macAddress, consumoWatts, statusLigado, tipo_dispositivo " +
                "FROM Dispositivo";
        String macAddresss;
        int consumoWatts;
        boolean statusLigado;
        String tipo_dispositivo;

        try (Connection connection = ConnectionFactory.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                macAddresss = resultSet.getString("macAddress");
                consumoWatts = resultSet.getInt("consumoWatts");
                statusLigado = resultSet.getBoolean("statusLigado");
                tipo_dispositivo = resultSet.getString("tipo_dispositivo");
                System.out.println(macAddresss + " - " + consumoWatts + " - " + statusLigado + " - " + tipo_dispositivo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
