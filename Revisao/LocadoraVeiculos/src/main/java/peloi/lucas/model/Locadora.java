package peloi.lucas.model;

import java.util.ArrayList;

public class Locadora {
    ArrayList<VeiculoEco> frota;

    public Locadora() {
        this.frota = new ArrayList<>();
    }

    public void listarApenasCarros(){
        for (VeiculoEco veiculoEco : frota) {
            if (veiculoEco instanceof CarroEletrico carroEletrico){
                System.out.println("--- " + carroEletrico.getChassi());
            }
        }
    }
}
