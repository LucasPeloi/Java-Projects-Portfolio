package peloi.lucas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private final LocalDateTime dataHora;
    private final TipoTransacao tipo;
    private final BigDecimal valor;
    private final String descricao;

    public Transacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
