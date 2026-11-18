package peloi.lucas.model;

public class Guerreiro extends Personagem{
    private int armadura;

    public Guerreiro(String nome, int vida, int poderAtaque, int armadura) {
        super(nome, vida, poderAtaque);
        this.armadura = armadura;
    }

    @Override
    public int atacar() {
        return (getPoderAtaque() + 10);
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }
}
