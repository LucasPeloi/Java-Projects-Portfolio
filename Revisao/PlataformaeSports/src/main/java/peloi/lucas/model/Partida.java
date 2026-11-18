package peloi.lucas.model;

public abstract class Partida {
     int id;
     int duracaoMinutos;
     int vencedor;

    public Partida(int duracaoMinutos, int vencedor) {
        this.duracaoMinutos = duracaoMinutos;
        this.vencedor = vencedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getVencedor() {
        return vencedor;
    }

    public void setVencedor(int vencedor) {
        this.vencedor = vencedor;
    }
}
