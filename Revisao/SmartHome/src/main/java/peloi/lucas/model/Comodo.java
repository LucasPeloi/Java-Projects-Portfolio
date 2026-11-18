package peloi.lucas.model;

import peloi.lucas.exception.SobrecargaEletricaException;

import java.util.ArrayList;

public class Comodo {
    private String nome;
    private int cargaMaximaWatts;
    ArrayList<DispositivoInteligente> dispositivoInteligentes;

    public Comodo(String nome, int cargaMaximaWatts) {
        this.nome = nome;
        this.cargaMaximaWatts = cargaMaximaWatts;
        this.dispositivoInteligentes = new ArrayList<>();
    }

    public void instalarDispositivo(DispositivoInteligente d){
        int somaConsumo = 0;
        for (DispositivoInteligente dispositivoInteligente : dispositivoInteligentes) {
            somaConsumo += dispositivoInteligente.getConsumoWatts();
            if (somaConsumo + d.getConsumoWatts() > cargaMaximaWatts){
                throw new SobrecargaEletricaException("ERRO: Carga máxima ultrapassada");
            }
        }
        dispositivoInteligentes.add(d);
        System.out.println("Dispositivo --"+d.getMacAddresss()+"-- Adicionado ao comodo!");
    }
}
