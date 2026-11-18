package peloi.lucas.model;

public abstract class ProfissionalSaude {
    private long id;
    private String nome;
    private String setor;
    private double salariobase;

    public ProfissionalSaude(long id, String nome, String setor, double salariobase) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.salariobase = salariobase;
    }

    double calcularPagamentoFinal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public double getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(double salariobase) {
        this.salariobase = salariobase;
    }

    protected abstract double calcularPagamentoFinal();
}

