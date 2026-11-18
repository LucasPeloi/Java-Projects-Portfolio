package peloi.lucas.model;

public class LampadaRGB extends DispositivoInteligente{
    private int corHexadecimal;

    public LampadaRGB(String macAddresss, int consumoWatts, boolean statusLigado, int corHexadecimal) {
        super(macAddresss, consumoWatts, statusLigado);
        this.corHexadecimal = corHexadecimal;
    }
}
