package peloi.lucas.model;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ContaCorrente extends Conta{

    private final BigDecimal limiteChequeEspecial;
    private static final BigDecimal TARIFA_SAQUE = new BigDecimal("2.50");
    private static final int SAQUES_GRATUITOS_POR_MES = 3;

    public ContaCorrente(String numero, Cliente titular, BigDecimal limiteChequeEspecial) {
        super(numero, titular);
        if (limiteChequeEspecial == null || limiteChequeEspecial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Limite inválido");
        }
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public BigDecimal getSaldoDisponivel() {
        return getSaldo().add(limiteChequeEspecial);
    }

    public BigDecimal getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void sacar(BigDecimal valor) {
        long saquesNoMes = contarSaquesDoMes();
        boolean cobrarTarifa = saquesNoMes >= SAQUES_GRATUITOS_POR_MES;

        if (cobrarTarifa) {
            // Valida valor + tarifa juntos
            validarValor(valor);
            validarSaldoSuficiente(valor.add(TARIFA_SAQUE));
        }

        super.sacar(valor);

        if (cobrarTarifa) {
            debitar(TARIFA_SAQUE);
            registrar(TipoTransacao.TARIFA, TARIFA_SAQUE, "Tarifa de saque");
        }
    }

    private long contarSaquesDoMes() {
        YearMonth mesAtual = YearMonth.now();
        return getExtrato().stream()
                .filter(t -> t.getTipo() == TipoTransacao.SAQUE)
                .filter(t -> YearMonth.from(t.getDataHora()).equals(mesAtual))
                .count();
    }
}
