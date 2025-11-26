package services;

import Utils.PetValidator;
import model.Pet;
import repository.FileDelete;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AlterarPet {
    static Path pathCadastrados = Paths.get("src\\petsCadastrados");
    public void alterarPet(){
        BuscarPet buscarPet = new BuscarPet();
        ArrayList<Pet> listaFiltrada = buscarPet.buscarPet();

        if(listaFiltrada.isEmpty()){
            System.out.println("Nenhum pet encontrado para editar.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        PetValidator validator = new PetValidator();
        FileDelete fileDelete = new FileDelete();

        buscarPet.printarListaPets();
        System.out.print("Digite o número do pet que deseja editar: ");
        int escolherPetEditar = validator.lerNValido(sc);

        if(escolherPetEditar < 1 || escolherPetEditar > listaFiltrada.size()){
            System.out.println("Número inválido! Tente novamente.\n");
            alterarPet();
        }

        Pet petSelecionado = listaFiltrada.get(escolherPetEditar - 1);
        String nomeAntigoPet = petSelecionado.getName();

        System.out.println("Editando o pet: " + petSelecionado.getName());

        System.out.println("Novo nome (ou pressione Enter para manter): ");
        String novoNome = sc.nextLine();

        if(novoNome.isBlank()){
            System.out.println("Nome mantido: " + petSelecionado.getName());
        }else{
            petSelecionado.setName(novoNome);
            System.out.println("Nome atualizado para: " + novoNome);
        }

        System.out.print("Novo endereço (Rua, Número, Cidade) ou pressione o Enter para manter o endereço: ");
        String novoEndereco = sc.nextLine();
        if(!novoEndereco.isBlank()){
            String[] partes = novoEndereco.split(", ");
            petSelecionado.getPetAddress().setCity(partes[0].trim());
            petSelecionado.getPetAddress().setStreet(partes[1].trim());
            petSelecionado.getPetAddress().setNumberHouse(partes[2].trim());
        }

        System.out.print("Nova Idade (Anos) ou pressione enter para manter: ");
        String novaIdade = validator.ageValidator(sc);

        if(novaIdade.isBlank()){
            System.out.println("Idade mantida: " + petSelecionado.getPetAge() + " anos");
        }else {
            petSelecionado.setPetAge(novaIdade);
            System.out.println("Idade atualizada para: " + novaIdade);
        }


        System.out.print("Novo peso (kgs) ou pressione enter para manter: ");
        String novoPeso = validator.weightValidator(sc);

        if(novoPeso.isBlank()){
            System.out.println("Peso mantido: " + petSelecionado.getPetWeight() + " kgs");
        }else {
            petSelecionado.setPetWeight(novoPeso);
            System.out.println("Peso atualizado para: " + novoPeso);
        }

        System.out.print("Nova raça ou pressione enter para manter: ");
        String novaRaca = validator.verifyPetBreed(sc);

        if(novaRaca.isBlank()){
            System.out.println("Raça mantido: " + petSelecionado.getPetBreed());
        }else {
            petSelecionado.setPetBreed(novaRaca);
            System.out.println("Raça atualizada para: " + novaRaca);
        }

        System.out.println("Pet Atualizado!");

        fileDelete.deletarArquivoAntigo(nomeAntigoPet);
        System.out.println(nomeAntigoPet);

        String nomeFormatado = petSelecionado.getName().replace(" ", "").toUpperCase();

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataHoraFormatada = agora.format(formatter);

        String nomeArquivo = dataHoraFormatada + "-" + nomeFormatado + ".txt";

        File diretorio = new File(String.valueOf(pathCadastrados.toAbsolutePath()));
        File arquivo = new File(diretorio, nomeArquivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("1 - " + petSelecionado.getName());
            bw.newLine();
            bw.write("2 - " + petSelecionado.getPetType());
            bw.newLine();
            bw.write("3 - " + petSelecionado.getPetGender());
            bw.newLine();
            bw.write("4 - " + petSelecionado.getPetAddress().getStreet() + ", " + petSelecionado.getPetAddress().getNumberHouse() + ", " + petSelecionado.getPetAddress().getCity());
            bw.newLine();
            bw.write("5 - " + petSelecionado.getPetAge() + " anos");
            bw.newLine();
            bw.write("6 - " + petSelecionado.getPetWeight() + "kg");
            bw.newLine();
            bw.write("7 - " + petSelecionado.getPetBreed());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro substituir o arquivo: " + e.getMessage());
        }


    }
}
