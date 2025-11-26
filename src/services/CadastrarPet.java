package services;

import Utils.PetValidator;
import model.Pet;
import model.PetAddress;
import repository.FileRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CadastrarPet {
    public void createPet(){
        Scanner sc = new Scanner(System.in);
        Pet pet = new Pet();
        PetAddress petAddress = new PetAddress();
        FileRepository fr = new FileRepository();
        PetValidator pv = new PetValidator();
        LocalDateTime localDateTime = LocalDateTime.now();


        //Cadastrar nome
        fr.readLineForm(1, new File("src//data//formulario.txt"));
        String name = pet.setName(pv.verifyPetName(sc));

        fr.readLineForm(2, new File ("src//data//formulario.txt"));
        System.out.println("1 - Cachorro\n2 - Gato");
        pet.setPetTypeChoice(pv.oneOrTwo(sc));

        fr.readLineForm(3, new File ("src//data//formulario.txt"));
        System.out.println("1 - Macho\n2 - Femea");
        pet.setPetGenderChoice(pv.oneOrTwo(sc));

        fr.readLineForm(4, new File ("src//data//formulario.txt"));
        System.out.print("Cidade: ");
        sc.nextLine();
        petAddress.setCity(sc.nextLine());

        System.out.print("Rua: ");
        petAddress.setStreet(sc.nextLine());

        petAddress.setNumberHouse(pv.NumberHouseValidator(sc));

        fr.readLineForm(5, new File ("src//data//formulario.txt"));
        pet.setPetAge(pv.ageValidator(sc));

        fr.readLineForm(6, new File ("src//data//formulario.txt"));
        pet.setPetWeight(pv.weightValidator(sc));

        fr.readLineForm(7, new File ("src//data//formulario.txt"));
        pet.setPetBreed(pv.verifyPetBreed(sc));

        pet.setPetAddress(petAddress);
        pet.savePet();
    }
}