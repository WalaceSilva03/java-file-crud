package services;

import java.util.Scanner;

public class PrintMenu {

    public int printarMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1. Cadastrar um novo pet");
        System.out.println("2. Listar pets por algum critério (idade, nome, raça)");
        System.out.println("3. Deletar um pet cadastrado");
        System.out.println("4. Listar todos os pets cadastrados");
        System.out.println("5. Alterar os dados do pet cadastrados");
        System.out.println("6. Sair");
        int op = numberValidator(sc);
        return op;
    }

    public int numberValidator(Scanner sc) {

        System.out.print("Opção: ");
        if (sc.hasNextInt()) {
            int num = sc.nextInt();
            if (num > 0 && num < 7) {
                return num;
            } else {
                System.out.println("Erro: Digite um número entre 1 e 6");
                return numberValidator(sc);
            }
        } else {
            System.out.println("Erro: Digite um caractere válido.");
            return numberValidator(sc);
        }

    }

}

