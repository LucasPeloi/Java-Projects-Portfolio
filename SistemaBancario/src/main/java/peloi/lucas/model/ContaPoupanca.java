package peloi.lucas.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta implements Rentavel {

    private final BigDecimal taxaRendimento;

    public ContaPoupanca(String numero, Cliente titular, BigDecimal taxaRendimento) {
        super(numero, titular);
        if (taxaRendimento == null || taxaRendimento.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxa de rendimento inválida");
        }
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public BigDecimal getSaldoDisponivel() {
        return getSaldo();
    }

    @Override
    public void aplicarRendimento() {
        BigDecimal rendimento = getSaldo().multiply(taxaRendimento).setScale(2, RoundingMode.HALF_EVEN);

        if (rendimento.compareTo(BigDecimal.ZERO) > 0) {
            creditar(rendimento);
            registrar(TipoTransacao.RENDIMENTO, rendimento, "Rendimento mensal");
        }
    }

    public BigDecimal getTaxaRendimento() {
        return taxaRendimento;
    }
}
