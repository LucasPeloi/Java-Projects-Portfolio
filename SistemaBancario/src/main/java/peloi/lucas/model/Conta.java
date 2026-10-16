package peloi.lucas.model;

import peloi.lucas.exception.SaldoInsuficienteException;
import peloi.lucas.exception.ValorInvalidoException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Conta {

    private final String numero;
    private final Cliente titular;
    private BigDecimal saldo;
    private final List<Transacao> transacoes;

    protected Conta(String numero, Cliente titular) {
        this.numero = Objects.requireNonNull(numero, "Número da conta é obrigatório");
        this.titular = Objects.requireNonNull(titular, "Titular é obrigatório");
        this.saldo = BigDecimal.ZERO;
        this.transacoes = new ArrayList<>();
    }

    public void depositar(BigDecimal valor) {
        validarValor(valor);
        creditar(valor);
        registrar(TipoTransacao.DEPOSITO, valor, "Depósito");
    }

    public void sacar(BigDecimal valor) {
        validarValor(valor);
        validarSaldoSuficiente(valor);
        debitar(valor);
        registrar(TipoTransacao.SAQUE, valor, "Saque");
    }

    public void transferir(Conta destino, BigDecimal valor) {
        if (destino == null) {
            throw new IllegalArgumentException("Conta de destino não pode ser nula");
        }
        if (destino == this) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta");
        }
        validarValor(valor);
        validarSaldoSuficiente(valor);

        this.debitar(valor);
        destino.creditar(valor);

        this.registrar(TipoTransacao.TRANSFERENCIA_ENVIADA, valor, "Transferência para conta " + destino.getNumero());
        destino.registrar(TipoTransacao.TRANSFERENCIA_RECEBIDA, valor, "Transferência da conta " + this.getNumero());
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public List<Transacao> getExtrato() {
        return Collections.unmodifiableList(transacoes);
    }

    public abstract BigDecimal getSaldoDisponivel();

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    protected void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    protected void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    protected void registrar(TipoTransacao tipo, BigDecimal valor, String descricao) {
        transacoes.add(new Transacao(tipo, valor, descricao));
    }

    protected void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("Valor deve ser maior que zero");
        }
    }

    protected void validarSaldoSuficiente(BigDecimal valor) {
        if (valor.compareTo(getSaldoDisponivel()) > 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + numero + " - " + titular.getNome()
                + " - saldo: " + saldo;
    }

}
