package peloi.lucas.model;

public abstract class VeiculoEco {
    private String chassi;
    private double taxaDiaria;
    private int autonomiaBateriaAtual;

    public VeiculoEco(String chassi, double taxaDiaria, int autonomiaBateriaAtual) {
        this.chassi = chassi;
        this.taxaDiaria = taxaDiaria;
        this.autonomiaBateriaAtual = autonomiaBateriaAtual;
    }

    public VeiculoEco(String chassi, double taxaDiaria) {
        this.chassi = chassi;
        this.taxaDiaria = taxaDiaria;
        this.autonomiaBateriaAtual = 100;
    }

    public abstract double calcularCustoAluguel(int dias);

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public double getTaxaDiaria() {
        return taxaDiaria;
    }

    public void setTaxaDiaria(double taxaDiaria) {
        this.taxaDiaria = taxaDiaria;
    }

    public int getAutonomiaBateriaAtual() {
        return autonomiaBateriaAtual;
    }

    public void setAutonomiaBateriaAtual(int autonomiaBateriaAtual) {
        this.autonomiaBateriaAtual = autonomiaBateriaAtual;
    }
}
