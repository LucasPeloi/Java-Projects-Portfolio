package peloi.lucas.model;

public class Hospital implements AuditoriaInterface{
    private String nome;
    private ProfissionalSaude[] ProfissionaisSaude = new ProfissionalSaude[50];

    private void adicionarProfissional(ProfissionalSaude novoProfissionalSaude){
        for (ProfissionalSaude profissionalSaude : ProfissionaisSaude){
            if (profissionalSaude == null){
                profissionalSaude = novoProfissionalSaude;
            }
        }
    }

    @Override
    public void gerarLog(String acao) {
        System.out.println("LOG: " + this.nome + acao);
    }
}
