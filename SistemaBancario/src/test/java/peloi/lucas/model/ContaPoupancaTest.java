package peloi.lucas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peloi.lucas.exception.SaldoInsuficienteException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContaPoupancaTest {

    private ContaPoupanca poupanca;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));
        poupanca = new ContaPoupanca("0001", cliente, new BigDecimal("0.005"));
    }

    @Test
    void naoDevePermitirSaldoNegativo() {
        poupanca.depositar(new BigDecimal("100.00"));

        assertThrows(SaldoInsuficienteException.class,
                () -> poupanca.sacar(new BigDecimal("150.00")));

        assertEquals(0, new BigDecimal("100.00").compareTo(poupanca.getSaldo()));
        assertEquals(1, poupanca.getExtrato().size());
    }

    @Test
    void devePermitirSacarTodoOSaldo() {
        poupanca.depositar(new BigDecimal("100.00"));
        poupanca.sacar(new BigDecimal("100.00"));

        assertEquals(0, BigDecimal.ZERO.compareTo(poupanca.getSaldo()));
    }

    @Test
    void rendimentoDeveSerCreditadoERegistrado() {
        poupanca.depositar(new BigDecimal("100.00"));

        poupanca.aplicarRendimento();

        assertEquals(0, new BigDecimal("100.50").compareTo(poupanca.getSaldo()));
        assertEquals(TipoTransacao.RENDIMENTO,
                poupanca.getExtrato().get(1).getTipo());
    }

    @Test
    void rendimentoComSaldoZeroNaoDeveRegistrarTransacao() {
        poupanca.aplicarRendimento();

        assertTrue(poupanca.getExtrato().isEmpty());
    }

    @Test
    void naoDeveAceitarTaxaNegativa() {
        Cliente cliente = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> new ContaPoupanca("0002", cliente, new BigDecimal("-0.01")));
    }
}