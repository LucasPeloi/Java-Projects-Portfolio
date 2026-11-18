package peloi.lucas;

import peloi.lucas.dao.PartidaDAO;
import peloi.lucas.database.ConnectionFactory;
import peloi.lucas.model.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void main() {
        int opcao = 0;
        ArrayList<Equipe> listaEquipes = new ArrayList<>();
        ArrayList<Jogador> listaJogadores = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Connection connection = ConnectionFactory.getConnection();

        do {
            System.out.println("=========== M E N U ============");
            System.out.println("1. Criar Equipe");
            System.out.println("2. Mostrar Equipes criadas");
            System.out.println("3. Criar Jogador");
            System.out.println("4. Mostrar Jogadores criados");
            System.out.println("5. Adicionar jogador a Equipe");
            System.out.println("6. Criar Partidas e Salvar");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    listaEquipes.add(criarEquipe());
                    break;
                case 2:
                    mostraListaEquipes(listaEquipes, true);
                    break;
                case 3:
                    listaJogadores.add(criarJogador());
                    break;
                case 4:
                    mostrarListaJogadores(listaJogadores);
                    break;
                case 5:
                    adicionarJogadorEquipe(listaEquipes, listaJogadores);
                    break;
                case 6:
                    PartidaRanqueada partidaRanqueada1 = new PartidaRanqueada(1,1);
                    PartidaRanqueada partidaRanqueada2 = new PartidaRanqueada(20,2);
                    PartidaTreino partidaTreino1 = new PartidaTreino(60,1);

                    PartidaDAO partidaDAO = new PartidaDAO();
                    partidaDAO.salvar(partidaRanqueada1);
                    partidaDAO.salvar(partidaRanqueada2);
                    partidaDAO.salvar(partidaTreino1);
                    System.out.println("Criado e adicionado ao banco de dados!");
                    partidaDAO.consultarPartidas();
                    break;
            }
        }while(opcao != 0);

    }

    private static Equipe criarEquipe(){
        String nomeEquipe;
        Equipe equipe;

        System.out.print("Nome da Equipe: ");
        Scanner scanner = new Scanner(System.in);

        nomeEquipe = scanner.nextLine();

        equipe = new Equipe(nomeEquipe);

        return equipe;
    }

    private static void mostraListaEquipes(ArrayList<Equipe> equipes, boolean mostraJogador){
        int index =0;

        for (Equipe equipe : equipes){
            System.out.println("--- " + index + " " + equipe.getNome() + " ---");

            if (mostraJogador){
                for (Jogador jogador : equipe.getJogadores())
                    System.out.println("----- " + jogador.getNickname() );
            }

            index ++;
        }
    }

    private static Jogador criarJogador(){
        String nickname;
        int nivel;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nickname: ");
        nickname = scanner.nextLine();
        System.out.print("Nivel: ");
        nivel = scanner.nextInt();

        Jogador jogador = new Jogador(nickname,nivel);

        return jogador;
    }

    private static void mostrarListaJogadores(ArrayList<Jogador> jogadores){
        for (Jogador jogador : jogadores){
            System.out.println("--- " + jogador.getNickname() + " | " + jogador.getNivel() + " | " + jogador.getEquipeAtual() + " ---");
        }
    }

    private static void adicionarJogadorEquipe(ArrayList<Equipe> equipes, ArrayList<Jogador> jogadores){
        Scanner scanner = new Scanner(System.in);
        int indexEquipe;
        int indexJogador;

        System.out.println("- Selecione uma equipe : ");
        mostraListaEquipes(equipes,false);
        System.out.print("Opção: ");
        indexEquipe = scanner.nextInt();

        System.out.println("- Selecione um jogador para adicionar: ");
        mostrarListaJogadores(jogadores);
        System.out.println("Opção: ");
        indexJogador = scanner.nextInt();

        equipes.get(indexEquipe).adicionarJogador(jogadores.get(indexJogador));
        jogadores.get(indexJogador).setEquipeAtual(equipes.get(indexEquipe).getNome());
    }

}
