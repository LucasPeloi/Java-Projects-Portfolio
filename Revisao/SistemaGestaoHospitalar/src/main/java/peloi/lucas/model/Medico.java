package peloi.lucas.model;

public class Medico extends ProfissionalSaude{
    private String crm;
    private int cirurgiasRealizadas;

    public Medico(long id, String nome, String setor, double salariobase, String crm, int cirurgiasRealizadas) {
        super(id, nome, setor, salariobase);
        this.crm = crm;
        this.cirurgiasRealizadas = cirurgiasRealizadas;
    }

    @Override
    public double calcularPagamentoFinal(){
        double salarioFinal = this.getSalariobase() + (500 * this.cirurgiasRealizadas);

        return salarioFinal;
    };

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public int getCirurgiasRealizadas() {
        return cirurgiasRealizadas;
    }

    public void setCirurgiasRealizadas(int cirurgiasRealizadas) {
        this.cirurgiasRealizadas = cirurgiasRealizadas;
    }
}
