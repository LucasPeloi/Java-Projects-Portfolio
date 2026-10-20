package peloi.lucas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peloi.lucas.exception.SaldoInsuficienteException;
import peloi.lucas.exception.ValorInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    private Cliente cliente;
    private ContaCorrente contaA;
    private ContaCorrente contaB;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));
        contaA = new ContaCorrente("0001", cliente, BigDecimal.ZERO);
        contaB = new ContaCorrente("0002", cliente, BigDecimal.ZERO);
    }

    private void assertSaldo(String esperado, Conta conta) {
        assertEquals(0, new BigDecimal(esperado).compareTo(conta.getSaldo()),
                "Saldo esperado " + esperado + " mas foi " + conta.getSaldo());
    }

    @Test
    void depositoDeveAumentarSaldoERegistrarTransacao() {
        contaA.depositar(new BigDecimal("100.00"));

        assertSaldo("100.00", contaA);
        assertEquals(1, contaA.getExtrato().size());
        assertEquals(TipoTransacao.DEPOSITO, contaA.getExtrato().get(0).getTipo());
    }

    @Test
    void depositoComValorZeroOuNegativoDeveFalhar() {
        assertThrows(ValorInvalidoException.class, () -> contaA.depositar(BigDecimal.ZERO));
        assertThrows(ValorInvalidoException.class, () -> contaA.depositar(new BigDecimal("-10")));
        assertThrows(ValorInvalidoException.class, () -> contaA.depositar(null));
        assertSaldo("0", contaA);
        assertTrue(contaA.getExtrato().isEmpty());
    }

    @Test
    void saqueDeveDiminuirSaldoERegistrarTransacao() {
        contaA.depositar(new BigDecimal("100.00"));
        contaA.sacar(new BigDecimal("30.00"));

        assertSaldo("70.00", contaA);
        assertEquals(TipoTransacao.SAQUE, contaA.getExtrato().get(1).getTipo());
    }

    @Test
    void saqueComValorInvalidoDeveFalhar() {
        assertThrows(ValorInvalidoException.class, () -> contaA.sacar(BigDecimal.ZERO));
    }

    @Test
    void transferenciaDeveMoverDinheiroERegistrarNosDoisExtratos() {
        contaA.depositar(new BigDecimal("100.00"));
        contaA.transferir(contaB, new BigDecimal("40.00"));

        assertSaldo("60.00", contaA);
        assertSaldo("40.00", contaB);
        assertEquals(TipoTransacao.TRANSFERENCIA_ENVIADA, contaA.getExtrato().get(1).getTipo());
        assertEquals(TipoTransacao.TRANSFERENCIA_RECEBIDA, contaB.getExtrato().get(0).getTipo());
    }

    @Test
    void transferenciaSemSaldoNaoDeveAlterarNenhumaConta() {
        contaA.depositar(new BigDecimal("100.00"));

        assertThrows(SaldoInsuficienteException.class,
                () -> contaA.transferir(contaB, new BigDecimal("500.00")));

        assertSaldo("100.00", contaA);
        assertSaldo("0", contaB);
        assertEquals(1, contaA.getExtrato().size()); // só o depósito
        assertTrue(contaB.getExtrato().isEmpty());
    }

    @Test
    void transferenciaParaMesmaContaDeveFalhar() {
        contaA.depositar(new BigDecimal("100.00"));
        assertThrows(IllegalArgumentException.class,
                () -> contaA.transferir(contaA, new BigDecimal("10.00")));
    }

    @Test
    void transferenciaParaDestinoNuloDeveFalhar() {
        assertThrows(IllegalArgumentException.class,
                () -> contaA.transferir(null, new BigDecimal("10.00")));
    }

    @Test
    void extratoDeveSerImutavel() {
        contaA.depositar(new BigDecimal("100.00"));
        assertThrows(UnsupportedOperationException.class,
                () -> contaA.getExtrato().clear());
    }
}