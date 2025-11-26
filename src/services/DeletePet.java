package services;

import Utils.PetValidator;
import model.Pet;
import repository.FileDelete;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class DeletePet {
    static Path pathCadastrados = Paths.get("src\\petsCadastrados");

    public void deletePet() {
        BuscarPet buscarPet = new BuscarPet();
        ArrayList<Pet> listaFiltrada = buscarPet.buscarPet();


        if (listaFiltrada.isEmpty()) {
            System.out.println("Nenhum pet encontrado para deletar.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        PetValidator validator = new PetValidator();

        buscarPet.printarListaPets();
        System.out.println("Escolha o pet que deseja apagar.");
        int escolherPetDeletar = validator.lerNValido(sc);

        if (escolherPetDeletar < 1 || escolherPetDeletar > listaFiltrada.size()) {
            System.out.println("Número inválido. Tente novamente!");
            deletePet();
        }

        Pet petSelecionado = listaFiltrada.get(escolherPetDeletar - 1);
        String nomePetArquivoDeletado = petSelecionado.getName().replaceAll("\\s", "").toUpperCase();

        System.out.println("Você tem certeza que deseja deletar o arquivo (SIM/NAO): ");
        String confirmacao = sc.nextLine().trim().toUpperCase();

        if (!confirmacao.equals("SIM")) {
            System.out.println("Exclusão interrompida.");
            return;
        }

        File pasta = new File(String.valueOf("src/petsCadastrados"));
        File[] arquivos = pasta.listFiles();

        if(arquivos != null){
            for(File arquivo : arquivos){
                String nomeArquivo = arquivo.getName().toUpperCase();
                if(nomeArquivo.contains("-" + nomePetArquivoDeletado + ".TXT")){
                    if(arquivo.delete()){
                        System.out.println("Pet " + nomePetArquivoDeletado + " deletado com sucesso!");
                    }else {
                        System.out.println("Erro ao deletar o pet " + nomePetArquivoDeletado);
                    }
                    return;
                }
            }
            System.out.println("Arquivo do Pet não encontrado!");
        }
    }
}