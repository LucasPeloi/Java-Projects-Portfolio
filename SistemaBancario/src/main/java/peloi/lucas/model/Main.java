package peloi.lucas;

import peloi.lucas.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("529.982.247-25", "Lucas", LocalDate.of(2000, 1, 1));

        // Cenário 1: depósito
        ContaCorrente cc = new ContaCorrente("0001", cliente, new BigDecimal("200.00"));
        cc.depositar(new BigDecimal("100.00"));
        System.out.println("1) Saldo: " + cc.getSaldo() + " | transações: " + cc.getExtrato().size());

        // Cenário 2: saque usando cheque especial
        cc.sacar(new BigDecimal("150.00"));
        System.out.println("2) Saldo: " + cc.getSaldo()); // -50.00

        // Cenário 3: saque inválido na poupança
        ContaPoupanca cp = new ContaPoupanca("0002", cliente, new BigDecimal("0.005"));
        cp.depositar(new BigDecimal("100.00"));
        try {
            cp.sacar(new BigDecimal("150.00"));
        } catch (Exception e) {
            System.out.println("3) " + e.getMessage() + " | saldo: " + cp.getSaldo()
                    + " | transações: " + cp.getExtrato().size());
        }

        // Cenário 4: transferência que falha
        ContaCorrente a = new ContaCorrente("0003", cliente, BigDecimal.ZERO);
        ContaCorrente b = new ContaCorrente("0004", cliente, BigDecimal.ZERO);
        a.depositar(new BigDecimal("100.00"));
        try {
            a.transferir(b, new BigDecimal("500.00"));
        } catch (Exception e) {
            System.out.println("4) " + e.getMessage() + " | A: " + a.getSaldo()
                    + " | B: " + b.getSaldo());
        }

        // Cenário 5: tarifa no 4º saque
        ContaCorrente c5 = new ContaCorrente("0005", cliente, BigDecimal.ZERO);
        c5.depositar(new BigDecimal("1000.00"));
        for (int i = 1; i <= 4; i++) {
            c5.sacar(new BigDecimal("10.00"));
        }
        System.out.println("5) Saldo: " + c5.getSaldo());
        c5.getExtrato().forEach(System.out::println);

        // Extra: rendimento
        cp.aplicarRendimento();
        System.out.println("Rendimento: saldo poupança = " + cp.getSaldo());
    }
}