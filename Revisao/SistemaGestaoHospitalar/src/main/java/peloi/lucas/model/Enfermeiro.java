package peloi.lucas.model;

import peloi.lucas.exception.PlantaoInvalidoException;

public class Enfermeiro extends ProfissionalSaude{
    private int coren;
    private int horasAdicionaisNoturnas;

    public Enfermeiro(long id, String nome, String setor, double salariobase, int coren, int horasAdicionaisNoturnas) {
        super(id, nome, setor, salariobase);
        this.coren = coren;
        this.horasAdicionaisNoturnas = horasAdicionaisNoturnas;
    }

    @Override
    public double calcularPagamentoFinal() {
        double salarioFinal = getSalariobase() + (80 * horasAdicionaisNoturnas);
        return salarioFinal;
    }

    public int getCoren() {
        return coren;
    }

    public void setCoren(int coren) {
        this.coren = coren;
    }

    public int getHorasAdicionaisNoturnas() {
        return horasAdicionaisNoturnas;
    }

    public void setHorasAdicionaisNoturnas(int horasAdicionaisNoturnas) {
        if ((horasAdicionaisNoturnas < 0) || (horasAdicionaisNoturnas > 120))
            throw new PlantaoInvalidoException("Horas inválidas para o mês");
        this.horasAdicionaisNoturnas = horasAdicionaisNoturnas;
    }
}
