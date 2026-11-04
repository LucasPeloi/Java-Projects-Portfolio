package peloi.lucas;

import peloi.lucas.exception.JavaBankException;
import peloi.lucas.model.Cliente;
import peloi.lucas.model.Conta;
import peloi.lucas.model.ContaCorrente;
import peloi.lucas.model.ContaPoupanca;
import peloi.lucas.model.Transacao;
import peloi.lucas.util.ValidadorCpf;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<Conta> contas = new ArrayList<>();

    private static final NumberFormat REAL = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final BigDecimal LIMITE_PADRAO = new BigDecimal("500.00");
    private static final BigDecimal TAXA_POUPANCA = new BigDecimal("0.005");

    public static void main(String[] args) {
        carregarDadosDeExemplo();
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInt("Opção: ");

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarClientePorCpf();
                case 4 -> removerCliente();
                case 5 -> abrirConta();
                case 6 -> listarContas();
                case 7 -> depositar();
                case 8 -> sacar();
                case 9 -> consultarSaldo();
                case 10 -> mostrarExtrato();
                case 11 -> transferir();
                case 12 -> aplicarRendimento();
                case 13 -> relatorioContasNegativas();
                case 14 -> relatorioMaiorSaldo();
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }


    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========== JAVABANK ==========");
        System.out.println("--- Clientes ---");
        System.out.println(" 1 - Cadastrar cliente");
        System.out.println(" 2 - Listar clientes");
        System.out.println(" 3 - Buscar cliente por CPF");
        System.out.println(" 4 - Remover cliente");
        System.out.println("--- Contas ---");
        System.out.println(" 5 - Abrir conta");
        System.out.println(" 6 - Listar contas");
        System.out.println("--- Operações ---");
        System.out.println(" 7 - Depositar");
        System.out.println(" 8 - Sacar");
        System.out.println(" 9 - Consultar saldo");
        System.out.println("10 - Extrato");
        System.out.println("11 - Transferir");
        System.out.println("--- Extras ---");
        System.out.println("12 - Aplicar rendimento nas poupanças");
        System.out.println("13 - Relatório: contas negativas");
        System.out.println("14 - Relatório: cliente com maior saldo");
        System.out.println(" 0 - Sair");
    }

    private static int lerInt(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static BigDecimal lerValor(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            String texto = scanner.nextLine().trim().replace(",", ".");
            try {
                return new BigDecimal(texto);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Exemplo: 150.75");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static Cliente buscarCliente(String cpf) {
        String cpfLimpo = ValidadorCpf.limpar(cpf);
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpfLimpo)) {
                return c;
            }
        }
        return null;
    }

    private static Conta buscarConta(String numero) {
        for (Conta c : contas) {
            if (c.getNumero().equals(numero)) {
                return c;
            }
        }
        return null;
    }

    private static boolean clienteTemContas(Cliente cliente) {
        for (Conta c : contas) {
            if (c.getTitular().getCpf().equals(cliente.getCpf())) {
                return true;
            }
        }
        return false;
    }


    private static void cadastrarCliente() {
        System.out.println("------- Cadastro de Cliente -------");
        String cpf = lerTexto("CPF: ");
        String nome = lerTexto("Nome: ");
        String dataTexto = lerTexto("Data de nascimento (dd/mm/aaaa): ");

        try {
            LocalDate nascimento = LocalDate.parse(dataTexto, FORMATO_DATA);
            Cliente cliente = new Cliente(cpf, nome, nascimento);

            if (buscarCliente(cliente.getCpf()) != null) {
                System.out.println("Já existe um cliente com esse CPF.");
                return;
            }

            clientes.add(cliente);
            System.out.println("Cliente cadastrado!");

        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato dd/mm/aaaa.");
        } catch (JavaBankException | IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        int i = 1;
        for (Cliente c : clientes) {
            System.out.println(i + " - " + c.getCpf() + " - " + c.getNome()
                    + " - " + c.getDataNascimento().format(FORMATO_DATA));
            i++;
        }
    }

    private static void buscarClientePorCpf() {
        String cpf = lerTexto("Digite o CPF: ");
        Cliente cliente = buscarCliente(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println("Cliente encontrado: " + cliente.getNome()
                    + " (nascimento: " + cliente.getDataNascimento().format(FORMATO_DATA) + ")");
        }
    }

    private static void removerCliente() {
        String cpf = lerTexto("CPF do cliente a remover: ");
        Cliente cliente = buscarCliente(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else if (clienteTemContas(cliente)) {
            System.out.println("Não é possível remover: o cliente possui contas.");
        } else {
            clientes.remove(cliente);
            System.out.println("Cliente removido.");
        }
    }

    private static void abrirConta() {
        String cpf = lerTexto("CPF do titular: ");
        Cliente titular = buscarCliente(cpf);

        if (titular == null) {
            System.out.println("Cliente não encontrado. Cadastre-o primeiro.");
            return;
        }

        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        int tipo = lerInt("Tipo: ");

        String numero = String.format("%04d", contas.size() + 1);

        Conta conta;
        if (tipo == 1) {
            conta = new ContaCorrente(numero, titular, LIMITE_PADRAO);
        } else if (tipo == 2) {
            conta = new ContaPoupanca(numero, titular, TAXA_POUPANCA);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        contas.add(conta);
        System.out.println("Conta " + numero + " aberta para " + titular.getNome() + "!");
    }

    private static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta aberta.");
            return;
        }
        for (Conta c : contas) {
            System.out.println(c.getNumero() + " | " + c.getClass().getSimpleName()
                    + " | " + c.getTitular().getNome()
                    + " | saldo: " + REAL.format(c.getSaldo()));
        }
    }

    private static void depositar() {
        Conta conta = buscarConta(lerTexto("Número da conta: "));
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        BigDecimal valor = lerValor("Valor do depósito: ");

        try {
            conta.depositar(valor);
            System.out.println("Depósito realizado. Saldo: " + REAL.format(conta.getSaldo()));
        } catch (JavaBankException | IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void sacar() {
        Conta conta = buscarConta(lerTexto("Número da conta: "));
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        BigDecimal valor = lerValor("Valor do saque: ");

        try {
            conta.sacar(valor);
            System.out.println("Saque realizado. Saldo: " + REAL.format(conta.getSaldo()));
        } catch (JavaBankException | IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void consultarSaldo() {
        Conta conta = buscarConta(lerTexto("Número da conta: "));
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        System.out.println("Saldo: " + REAL.format(conta.getSaldo()));
        System.out.println("Disponível para uso: " + REAL.format(conta.getSaldoDisponivel()));
    }

    private static void mostrarExtrato() {
        Conta conta = buscarConta(lerTexto("Número da conta: "));
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        if (conta.getExtrato().isEmpty()) {
            System.out.println("Nenhuma movimentação.");
            return;
        }
        System.out.println("----- Extrato da conta " + conta.getNumero() + " -----");
        for (Transacao t : conta.getExtrato()) {
            System.out.println(t.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    + " | " + t.getTipo()
                    + " | " + REAL.format(t.getValor())
                    + " | " + t.getDescricao());
        }
        System.out.println("Saldo atual: " + REAL.format(conta.getSaldo()));
    }

    private static void transferir() {
        Conta origem = buscarConta(lerTexto("Conta de origem: "));
        if (origem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }
        Conta destino = buscarConta(lerTexto("Conta de destino: "));
        if (destino == null) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }
        BigDecimal valor = lerValor("Valor da transferência: ");

        try {
            origem.transferir(destino, valor);
            System.out.println("Transferência realizada!");
        } catch (JavaBankException | IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void aplicarRendimento() {
        int quantidade = 0;
        for (Conta c : contas) {
            if (c instanceof ContaPoupanca poupanca) {
                poupanca.aplicarRendimento();
                quantidade++;
            }
        }
        System.out.println("Rendimento aplicado em " + quantidade + " poupança(s).");
    }

    private static void relatorioContasNegativas() {
        boolean achou = false;
        for (Conta c : contas) {
            if (c.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
                System.out.println(c.getNumero() + " | " + c.getTitular().getNome()
                        + " | saldo: " + REAL.format(c.getSaldo()));
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma conta negativa.");
        }
    }

    private static void relatorioMaiorSaldo() {
        Cliente melhor = null;
        BigDecimal maiorTotal = null;

        for (Cliente cliente : clientes) {
            BigDecimal total = BigDecimal.ZERO;
            for (Conta c : contas) {
                if (c.getTitular().getCpf().equals(cliente.getCpf())) {
                    total = total.add(c.getSaldo());
                }
            }
            if (maiorTotal == null || total.compareTo(maiorTotal) > 0) {
                maiorTotal = total;
                melhor = cliente;
            }
        }

        if (melhor == null) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Maior saldo total: " + melhor.getNome()
                    + " com " + REAL.format(maiorTotal));
        }
    }

    private static void carregarDadosDeExemplo() {
        Cliente ana = new Cliente("529.982.247-25", "Ana Souza", LocalDate.of(1995, 3, 10));
        Cliente bruno = new Cliente("111.444.777-35", "Bruno Lima", LocalDate.of(1988, 7, 22));
        clientes.add(ana);
        clientes.add(bruno);

        Conta c1 = new ContaCorrente("0001", ana, LIMITE_PADRAO);
        Conta c2 = new ContaPoupanca("0002", ana, TAXA_POUPANCA);
        Conta c3 = new ContaCorrente("0003", bruno, LIMITE_PADRAO);
        contas.add(c1);
        contas.add(c2);
        contas.add(c3);

        c1.depositar(new BigDecimal("1000.00"));
        c2.depositar(new BigDecimal("500.00"));
        c3.depositar(new BigDecimal("200.00"));
    }
}