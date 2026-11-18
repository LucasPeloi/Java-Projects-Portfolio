package peloi.lucas.model;

public class ArCondicionado extends DispositivoInteligente{
    private int temperaturaAlvo;

    public ArCondicionado(String macAddresss, int consumoWatts, boolean statusLigado, int temperaturaAlvo) {
        super(macAddresss, consumoWatts, statusLigado);
        this.temperaturaAlvo = temperaturaAlvo;
    }
}
