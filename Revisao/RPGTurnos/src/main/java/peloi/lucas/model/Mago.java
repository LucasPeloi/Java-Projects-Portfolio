package peloi.lucas.model;

public class Mago extends Personagem {
    private int mana;

    public Mago(String nome, int vida, int poderAtaque, int mana) {
        super(nome, vida, poderAtaque);
        this.mana = mana;
    }

    @Override
    public int atacar() {
        return (getPoderAtaque() + (mana/2));
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
