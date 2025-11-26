package main;

import model.Pet;
import repository.FileRepository;
import services.*;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        File file = new File("src/data/formulario.txt");
        FileRepository fr = new FileRepository();
        PrintMenu printMenu = new PrintMenu();
        BuscarPet buscarPet = new BuscarPet();
        CadastrarPet cadastrarPet = new CadastrarPet();
        AlterarPet alterarPet = new AlterarPet();
        DeletePet deletePet = new DeletePet();
        ListarPets listarPets = new ListarPets();

        if(!file.exists()){
            fr.createForm();
            System.out.println("Formulario criado!");
        }

        fr.readForm(file);

        do {
            switch (printMenu.printarMenu()) {
                case 1:
                    cadastrarPet.createPet();
                    break;

                case 2:
                    buscarPet.rodarMenuBusca();
                    break;

                case 3:
                    deletePet.deletePet();
                    break;

                case 4:
                    listarPets.listarPets();
                    break;

                case 5:
                    alterarPet.alterarPet();
                    break;

            }
        }while (printMenu.printarMenu() != 6);
        System.out.println("Encerrando....");
    }
}