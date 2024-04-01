package br.com.es.concorrencia.impl.exercicio.sinaleiro.repo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Cria um Scanner para ler entrada do teclado
        Scanner scanner = new Scanner(System.in);
        
        // Thread para imprimir no console
        Thread printerThread = new Thread(() -> {
            while (true) {
                System.out.println("Imprimindo...");
                try {
                    // Faz a thread dormir por 1 segundo antes de imprimir novamente
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        // Inicia a thread de impressão
        printerThread.start();
        
        // Loop para ler entrada do teclado
        while (true) {
            // Lê a entrada do teclado
            String input = scanner.nextLine();
            System.out.println("Você digitou: " + input);
        }
    }
}
