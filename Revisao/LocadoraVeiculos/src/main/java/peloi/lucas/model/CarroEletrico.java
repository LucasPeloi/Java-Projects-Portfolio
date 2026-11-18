package peloi.lucas.model;

public class CarroEletrico extends VeiculoEco{
    private int quantidadeAssentos;

    public CarroEletrico(String chassi, double taxaDiaria, int autonomiaBateriaAtual, int quantidadeAssentos) {
        super(chassi, taxaDiaria, autonomiaBateriaAtual);
        this.quantidadeAssentos = quantidadeAssentos;
    }

    public CarroEletrico(String chassi, double taxaDiaria, int quantidadeAssentos) {
        super(chassi, taxaDiaria);
        this.quantidadeAssentos = quantidadeAssentos;
    }

    public int getQuantidadeAssentos() {
        return quantidadeAssentos;
    }

    public void setQuantidadeAssentos(int quantidadeAssentos) {
        this.quantidadeAssentos = quantidadeAssentos;
    }

    @Override
    public double calcularCustoAluguel(int dias) {
        double custo;
        custo = this.getTaxaDiaria() + dias + 1.15;
        return custo;
    }
}
