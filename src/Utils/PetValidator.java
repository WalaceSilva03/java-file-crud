package Utils;

import model.Pet;
import model.PetAddress;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetValidator {

    public String verifyPetName(Scanner sc) {
        try {
            String IsNameValid = sc.nextLine();
            Pet pet = new Pet();
            pet.setName(IsNameValid);
            return IsNameValid;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return verifyPetName(sc);
        }
    }

    public String NumberHouseValidator(Scanner sc) {
        System.out.print("Número de casa: ");
        String numberHouse = sc.nextLine().trim();

        if(numberHouse.isEmpty()){
            return Pet.noInformed;
        }

        if(numberHouse.matches("\\d+")){
            return numberHouse;
        }else{
            System.out.print("Entrada inválida.");
            return NumberHouseValidator(sc);
        }
    }

    public int lerNValido(Scanner input) {
        try {
            System.out.print("Digite um número: ");
            int numValido = input.nextInt();
            input.nextLine();
            return numValido;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite um número valido.");
            input.nextLine();
            return lerNValido(input);
        }
    }

    public int oneOrTwo(Scanner sc) {
        int choice = 0;
        do {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Digite uma opção válida.");
                    return choice;
                }
            } else {
                System.out.println("Digite um valor válido.");
                sc.next();
            }
        } while (choice != 1 && choice != 2);
        return choice;
    }

    public String ageValidator(Scanner sc) {

        while (true) {
            String age = sc.nextLine();

            if (age.isEmpty()) {
                return Pet.noInformed;
            }

            try {
                float ageFloat = Float.parseFloat(age);
                if (ageFloat <= 0 || ageFloat > 20) {
                    throw new IllegalArgumentException("O valor deve ser maior que 0 e menor que 20");
                }
                return String.valueOf(ageFloat);
            } catch (NumberFormatException e){
                System.out.println("Erro: Entrada inválida: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Entrada inválida: " + e.getMessage());
            }
        }
    }

    public String weightValidator(Scanner sc) {
        while (true) {
            String weight = sc.nextLine().trim();

            if (weight.isEmpty()) {
                return Pet.noInformed;
            }

            try {
                float weightFloat = Float.parseFloat(weight);
                if (weightFloat <= 0 || weightFloat > 60) {
                    throw new IllegalArgumentException("O valor deve ser maior que 0 e menor que 60");
                }
                return String.valueOf(weightFloat);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Entrada inválida: " + e.getMessage());
            }
        }
    }

    public String verifyPetBreed(Scanner sc) {
        try {
            String breedValid = sc.nextLine();
            Pet pet = new Pet();
            pet.setPetBreed(breedValid);
            return breedValid;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return verifyPetBreed(sc);
        }
    }
}
