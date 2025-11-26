package services;

import Utils.PetValidator;
import model.Pet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ListarPets {
    static Path pathCadastrados = Paths.get("src\\petsCadastrados");
    public void listarPets(){
        BuscarPet buscarPet = new BuscarPet();
        ArrayList<Pet> listaFiltrada = buscarPet.buscarPet();

        if(listaFiltrada.isEmpty()){
            System.out.println("Não há nenhum pet cadastrado.");
            return;
        }

        System.out.println();
        System.out.println("Lista de Pets abaixo: ");
        buscarPet.printarListaPets();
    }
}
