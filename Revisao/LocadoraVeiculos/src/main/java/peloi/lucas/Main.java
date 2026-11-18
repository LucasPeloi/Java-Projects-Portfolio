package peloi.lucas;

import peloi.lucas.dao.VeiculoDAO;
import peloi.lucas.model.CarroEletrico;
import peloi.lucas.model.PatineteEletrico;
import peloi.lucas.model.VeiculoEco;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ArrayList<VeiculoEco> veiculoEcos = new ArrayList<>();
        CarroEletrico carroEletrico01 = new CarroEletrico("Chassi01", 10, 4);
        CarroEletrico carroEletrico02 = new CarroEletrico("Chassi02", 20, 2);
        CarroEletrico carroEletrico03 = new CarroEletrico("Chassi03", 30, 6);
        PatineteEletrico patineteEletrico01 = new PatineteEletrico("ChassiP01", 10, true);
        PatineteEletrico patineteEletrico02 = new PatineteEletrico("ChassiP02", 10, false);

        veiculoEcos.add(carroEletrico01);
        veiculoEcos.add(carroEletrico02);
        veiculoEcos.add(carroEletrico03);
        veiculoEcos.add(patineteEletrico01);
        veiculoEcos.add(patineteEletrico02);

        for (VeiculoEco veiculoEco : veiculoEcos) {
            System.out.println("--- " + veiculoEco.getChassi() + " " + veiculoEco.calcularCustoAluguel(5));
        }

        veiculoDAO.cadastrarVeiculosEco(carroEletrico01);
        veiculoDAO.cadastrarVeiculosEco(carroEletrico02);
        veiculoDAO.cadastrarVeiculosEco(carroEletrico03);
        veiculoDAO.cadastrarVeiculosEco(patineteEletrico01);
        veiculoDAO.cadastrarVeiculosEco(patineteEletrico02);

        System.out.println("Numero de patinetes cadastrados: " + veiculoDAO.contarPatinetesCadastrados());
    }
}
