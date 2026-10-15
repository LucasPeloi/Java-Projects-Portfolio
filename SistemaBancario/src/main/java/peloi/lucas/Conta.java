package peloi.lucas;

import java.math.BigDecimal;
import java.util.List;

public abstract class Conta {

    private String numero;
    private Cliente titular;
    private BigDecimal saldo;
    private List<Transacao> transacoes;

    public void depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }
    public void sacar(BigDecimal valor){

    }
    public void transferir(Conta destino, BigDecimal valor){}

}
