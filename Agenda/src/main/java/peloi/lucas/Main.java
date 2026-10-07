package peloi.lucas;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static Agenda agenda = new Agenda();

    public static void main(String[] args){
        int opcao;

        do {
            mostrarMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionar();
                    break;
                case 2:
                    agenda.listarContatos();
                    break;
                case 3:
                    System.out.println("Consulta: ");
                    agenda.buscarContato(scanner.nextLine().trim());
                    break;
                case 4:
                    agenda.listarContatos();
                    System.out.print("ID do contato a remover: ");
                    agenda.removerContato(lerOpcao());
                    break;
                case 0:
                    System.out.println("Sistema encerrado");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }while(opcao != 0);
    }

    private static void mostrarMenu(){
        System.out.println("\n===== AGENDA =====");
        System.out.println("1 - Adicionar contato");
        System.out.println("2 - Listar contatos");
        System.out.println("3 - Buscar por nome");
        System.out.println("4 - Remover contato");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    private static int lerOpcao() {
        try{
            return Integer.parseInt(scanner.nextLine().trim());
        }catch (NumberFormatException e){
            return -1;
        }
    }

    private static void adicionar(){
        System.out.println("Nome: ");
        String nome = scanner.nextLine().trim();

        if(nome.isEmpty()){
            System.out.println("O nome não pode ficar vazio.");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();

        Contato contato = new Contato(nome, telefone, email);
        agenda.adicionarContato(contato);
    }
}
