package peloi.lucas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peloi.lucas.exception.SaldoInsuficienteException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContaCorrenteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));
    }

    private void assertSaldo(String esperado, Conta conta) {
        assertEquals(0, new BigDecimal(esperado).compareTo(conta.getSaldo()),
                "Saldo esperado " + esperado + " mas foi " + conta.getSaldo());
    }

    private long contar(Conta conta, TipoTransacao tipo) {
        return conta.getExtrato().stream().filter(t -> t.getTipo() == tipo).count();
    }

    @Test
    void deveSacarUsandoChequeEspecial() {
        ContaCorrente cc = new ContaCorrente("0001", cliente, new BigDecimal("200.00"));
        cc.depositar(new BigDecimal("100.00"));

        cc.sacar(new BigDecimal("150.00"));

        assertSaldo("-50.00", cc);
    }

    @Test
    void deveRejeitarSaqueQueEstouraOLimite() {
        ContaCorrente cc = new ContaCorrente("0001", cliente, new BigDecimal("200.00"));
        cc.depositar(new BigDecimal("100.00"));

        assertThrows(SaldoInsuficienteException.class,
                () -> cc.sacar(new BigDecimal("301.00")));

        assertSaldo("100.00", cc);
    }

    @Test
    void saldoDisponivelDeveSomarOLimite() {
        ContaCorrente cc = new ContaCorrente("0001", cliente, new BigDecimal("200.00"));
        cc.depositar(new BigDecimal("100.00"));

        assertEquals(0, new BigDecimal("300.00").compareTo(cc.getSaldoDisponivel()));
    }

    @Test
    void tresPrimeirosSaquesNaoCobramTarifa() {
        ContaCorrente cc = new ContaCorrente("0001", cliente, BigDecimal.ZERO);
        cc.depositar(new BigDecimal("1000.00"));

        for (int i = 0; i < 3; i++) {
            cc.sacar(new BigDecimal("10.00"));
        }

        assertSaldo("970.00", cc);
        assertEquals(0, contar(cc, TipoTransacao.TARIFA));
    }

    @Test
    void quartoSaqueDoMesDeveCobrarTarifa() {
        ContaCorrente cc = new ContaCorrente("0001", cliente, BigDecimal.ZERO);
        cc.depositar(new BigDecimal("1000.00"));

        for (int i = 0; i < 4; i++) {
            cc.sacar(new BigDecimal("10.00"));
        }

        assertSaldo("957.50", cc); // 1000 - 40 - 2,50
        assertEquals(4, contar(cc, TipoTransacao.SAQUE));
        assertEquals(1, contar(cc, TipoTransacao.TARIFA));
    }

    @Test
    void saqueQueNaoCobreValorMaisTarifaDeveSerRejeitadoPorInteiro() {
        ContaCorrente cc = new ContaCorrente("0001", cliente, BigDecimal.ZERO);
        cc.depositar(new BigDecimal("100.00"));
        for (int i = 0; i < 3; i++) {
            cc.sacar(new BigDecimal("30.00"));
        }

        assertThrows(SaldoInsuficienteException.class,
                () -> cc.sacar(new BigDecimal("10.00")));

        assertSaldo("10.00", cc);
        assertEquals(3, contar(cc, TipoTransacao.SAQUE));
        assertEquals(0, contar(cc, TipoTransacao.TARIFA));
    }

    @Test
    void transferenciaNaoDeveContarComoSaque() {
        ContaCorrente origem = new ContaCorrente("0001", cliente, BigDecimal.ZERO);
        ContaCorrente destino = new ContaCorrente("0002", cliente, BigDecimal.ZERO);
        origem.depositar(new BigDecimal("1000.00"));

        for (int i = 0; i < 5; i++) {
            origem.transferir(destino, new BigDecimal("10.00"));
        }
        origem.sacar(new BigDecimal("10.00")); // 1º saque de verdade

        assertEquals(0, contar(origem, TipoTransacao.TARIFA));
    }

    @Test
    void naoDeveAceitarLimiteNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new ContaCorrente("0001", cliente, new BigDecimal("-1")));
    }
}