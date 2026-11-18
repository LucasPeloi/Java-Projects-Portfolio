package peloi.lucas.model;

public abstract class DispositivoInteligente implements ControleRemotoInterface {
    private String macAddresss;
    private int consumoWatts;
    private boolean statusLigado;

    public DispositivoInteligente(String macAddresss, int consumoWatts, boolean statusLigado) {
        this.macAddresss = macAddresss;
        this.consumoWatts = consumoWatts;
        this.statusLigado = statusLigado;
    }

    public String getMacAddresss() {
        return macAddresss;
    }

    public void setMacAddresss(String macAddresss) {
        this.macAddresss = macAddresss;
    }

    public int getConsumoWatts() {
        return consumoWatts;
    }

    public void setConsumoWatts(int consumoWatts) {
        this.consumoWatts = consumoWatts;
    }

    public void setStatusLigado(boolean statusLigado) {
        this.statusLigado = statusLigado;
    }

    @Override
    public void ligar() {
        this.statusLigado = true;
    }

    @Override
    public void desligar() {
        this.statusLigado = false;
    }

    @Override
    public boolean isLigado() {
        return statusLigado;
    }
}
