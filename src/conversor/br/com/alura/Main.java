package conversor.br.com.alura;

import conversor.br.com.alura.Conversao;
import conversor.br.com.alura.TaxaDeConversao;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n******* Conversor de Moedas *******");
            System.out.println("Escolha uma das opções abaixo:");
            System.out.println("1 - BRL para CAD");
            System.out.println("2 - BRL para EUR");
            System.out.println("3 - BRL para USD");
            System.out.println("4 - USD para CAD");
            System.out.println("5 - USD para EUR");
            System.out.println("6 - CAD para EUR");
            System.out.print("Digite o número da opção desejada: ");

            int opcao = scanner.nextInt();
            String fromCurrency = "";
            String toCurrency = "";

            // Definir as moedas com base na opção escolhida
            switch (opcao) {
                case 1:
                    fromCurrency = "BRL";
                    toCurrency = "CAD";
                    break;
                case 2:
                    fromCurrency = "BRL";
                    toCurrency = "EUR";
                    break;
                case 3:
                    fromCurrency = "BRL";
                    toCurrency = "USD";
                    break;
                case 4:
                    fromCurrency = "USD";
                    toCurrency = "CAD";
                    break;
                case 5:
                    fromCurrency = "USD";
                    toCurrency = "EUR";
                    break;
                case 6:
                    fromCurrency = "CAD";
                    toCurrency = "EUR";
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double amount = scanner.nextDouble();

            try {
                double exchangeRate = TaxaDeConversao.getExchangeRate(fromCurrency, toCurrency);
                double convertedAmount = Conversao.convert(amount, exchangeRate);

                System.out.printf("%.2f %s é equivalente a %.2f %s.%n",
                        amount, fromCurrency, convertedAmount, toCurrency);
            } catch (IOException e) {
                System.out.println("Erro ao buscar a taxa de câmbio: " + e.getMessage());
            }

            // Perguntar ao usuário se deseja continuar
            System.out.print("\nDeseja realizar outra operação? (S/N): ");
            String resposta = scanner.next().toUpperCase();
            if (!resposta.equals("S")) {
                continuar = false;
            }
        }

        System.out.println("Obrigado por usar o Conversor de Moedas. Até a próxima!");
        scanner.close();
    }
}
