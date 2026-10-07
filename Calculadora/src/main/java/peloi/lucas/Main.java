package peloi.lucas;

import java.util.Scanner;

public class Main {
    static void main(){
        double numero1;
        double numero2;
        char operacao;
        char continuar;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Digite o primeiro número: ");
            numero1 = scanner.nextDouble();

            System.out.println("Digite a operação (+, -, *, /): ");
            operacao = scanner.next().charAt(0);

            System.out.println("Digite o segundo número: ");
            numero2 = scanner.nextDouble();

            switch(operacao){
                case '+':
                    System.out.println("Resultado: " + (numero1 + numero2));
                    break;
                case '-':
                    System.out.println("Resultado: " + (numero1 - numero2));
                    break;
                case '*':
                    System.out.println("Resultado: " + (numero1 * numero2));
                    break;
                case '/':
                    if (numero2 == 0) {
                        System.out.println("Erro: divisão por zero!");
                    } else {
                        System.out.println("Resultado: " + (numero1 / numero2));
                    }
                    break;
                default:
                    System.out.println("Operação inválida!");
            }

            System.out.print("Deseja fazer outro cálculo? (s/n): ");
            continuar = scanner.next().charAt(0);

        }while((continuar == 's') || (continuar == 'S'));

        System.out.println("Sistema Finalizado!");
        scanner.close();
    }
}
