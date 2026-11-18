package peloi.lucas.model;

import peloi.lucas.exception.JogadorBanidoException;

public class Jogador {
    private String nickname;
    private int nivel;
    private int pontosRanqueada;
    private String equipeAtual;

    public Jogador(String nickname, int nivel) {
        this.nickname = nickname;
        this.nivel = nivel;
        this.pontosRanqueada = 0;
    }

    public void entrarEmFila() throws JogadorBanidoException {
        if (nivel < 5) {
            throw new JogadorBanidoException("Nível Insuficiente para entrar em fila!");
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPontosRanqueada() {
        return pontosRanqueada;
    }

    public void setPontosRanqueada(int pontosRanqueada) {
        this.pontosRanqueada = pontosRanqueada;
    }

    public String getEquipeAtual() {
        return equipeAtual;
    }

    public void setEquipeAtual(String equipeAtual) {
        this.equipeAtual = equipeAtual;
    }
}
