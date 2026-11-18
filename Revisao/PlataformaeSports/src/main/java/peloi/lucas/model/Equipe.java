package peloi.lucas.model;

import java.util.ArrayList;

public class Equipe {
    private String nome;
    private ArrayList<Jogador> jogadores;

    public Equipe(String nome) {
        this.nome = nome;
        jogadores = new ArrayList<>();
    }

    public void adicionarJogador(Jogador j){
        jogadores.add(j);
        j.setEquipeAtual(this.nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
