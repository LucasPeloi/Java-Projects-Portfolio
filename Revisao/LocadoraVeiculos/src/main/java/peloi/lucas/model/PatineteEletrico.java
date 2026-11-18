package peloi.lucas.model;

public class PatineteEletrico extends VeiculoEco{
    private boolean requerCapacete;

    public PatineteEletrico(String chassi, double taxaDiaria, int autonomiaBateriaAtual, boolean requerCapacete) {
        super(chassi, taxaDiaria, autonomiaBateriaAtual);
        this.requerCapacete = requerCapacete;
    }

    public PatineteEletrico(String chassi, double taxaDiaria, boolean requerCapacete) {
        super(chassi, taxaDiaria);
        this.requerCapacete = requerCapacete;
    }

    public boolean isRequerCapacete() {
        return requerCapacete;
    }

    public void setRequerCapacete(boolean requerCapacete) {
        this.requerCapacete = requerCapacete;
    }

    @Override
    public double calcularCustoAluguel(int dias) {
        double custo;

        custo = this.getTaxaDiaria() * dias;

        if (this.requerCapacete) {
            custo += 15;
        }

        return custo;
    }
}
