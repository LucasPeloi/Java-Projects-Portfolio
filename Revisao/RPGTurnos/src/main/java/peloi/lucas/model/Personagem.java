package peloi.lucas.model;

public abstract  class Personagem {
    private int id;
    private String nome;
    private int vida;
    private int poderAtaque;

    public Personagem(String nome, int vida, int poderAtaque) {
        this.nome = nome;
        if (vida >= 0) {
            this.vida = vida;
        }else {
            this.vida = 0;
        }
        this.poderAtaque = poderAtaque;
    }

    public abstract int atacar();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida >= 0) {
            this.vida = vida;
        }else {
            this.vida = 0;
        }
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    public void setPoderAtaque(int poderAtaque) {
        this.poderAtaque = poderAtaque;
    }
}
