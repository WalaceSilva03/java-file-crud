package model;

import services.BuscarPet;
import services.CadastrarPet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pet {
    private String name;
    private PetType petType;
    private PetGender petGender;
    private PetAddress petAddress;
    private String petAge;
    private String petWeight;
    private String petBreed;
    CadastrarPet cadastrarPet = new CadastrarPet();
    BuscarPet buscarPet = new BuscarPet();

    public static final String noInformed = "NÃO_INFORMADO";

    public Pet() {
    }

    public Pet(String name, PetType petType, PetGender petGender, PetAddress petAddress, String petAge, String petWeight, String petBreed) {
        this.name = name;
        this.petType = petType;
        this.petGender = petGender;
        this.petAddress = petAddress;
        this.petAge = petAge;
        this.petWeight = petWeight;
        this.petBreed = petBreed;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        if (name.trim().isEmpty() || name == null) {
            this.name = noInformed;
            return this.name;
        }

        if (!name.matches("([a-zA-ZÀ-ÿ]+\\ [a-zA-ZÀ-ÿ]+)*") || !name.contains(" ")) {
            throw new IllegalArgumentException("Entrada invalida.");
        } else {
            return this.name = name.trim();
        }
    }


    public PetType getPetType() {
        return petType;
    }

    public void setPetTypeChoice(int choice) {
        if (choice == 1) {
            this.petType = PetType.CACHORRO;
        }

        if (choice == 2) {
            this.petType = PetType.GATO;
        }
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public PetGender getPetGender() {
        return petGender;
    }

    public void setPetGenderChoice(int choice) {
        if (choice == 1) {
            this.petGender = PetGender.MACHO;
        }

        if (choice == 2) {
            this.petGender = PetGender.FEMEA;
        }
    }

    public void setPetGender(PetGender petGender) {
        this.petGender = petGender;
    }

    public PetAddress getPetAddress() {
        return petAddress;
    }

    public void setPetAddress(PetAddress petAddress) {
        this.petAddress = petAddress;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String age) {
        if (age.trim().isEmpty() || age == null) {
            this.petAge = noInformed;
            return;
        }

        if (age.contains(",")) {
            age = age.replace(",", ".");
        }

        float ageFloat = Float.parseFloat(age);
        if (ageFloat < 0.0 || ageFloat > 20.0) {
            throw new IllegalArgumentException("A idade deve ser menor que 20 anos.");
        }
        this.petAge = String.valueOf((int) ageFloat);
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String weight) {
        if (weight.trim().isEmpty() || weight == null) {
            this.petWeight = noInformed;
            return;
        }

        if (weight.contains(",")) {
            this.petWeight = weight.replace(",", ".");
        }

        float weightFloat = Float.parseFloat(weight);
        if (weightFloat < 0.0 || weightFloat > 60.0) {
            throw new IllegalArgumentException("O peso deve ser menor que 60kgs e superior a 0kgs.");
        }
        this.petWeight = String.valueOf((int) weightFloat);
    }

    public String getPetBreed() {
        return petBreed;
    }

    public String setPetBreed(String breed) {
        if (breed.trim().isEmpty() || breed == null) {
            this.petBreed = noInformed;
            return this.petBreed;
        }

        while (true) {
            if (!breed.matches("([a-zA-Z])+")) {
                System.out.println("A raça não deve conter números ou caracteres especiais.");
            } else {
                return this.petBreed = breed.trim();
            }
        }
    }

    public void savePet() {
        String nomePet = getName().replace(" ", "").toUpperCase();
        PetAddress petAddress = new PetAddress();
        BuscarPet buscarPet = new BuscarPet();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String formatado = localDateTime.format(formatter);
        Path pathCadastro = Paths.get("src/petsCadastrados");

        String archiveName = formatado + "-" + nomePet;
        File petsCadastradosDirectory = new File(String.valueOf(pathCadastro.toAbsolutePath()));

        if (!petsCadastradosDirectory.exists()) {
            if (petsCadastradosDirectory.mkdir()) {
                System.out.println("Diretório criado!");
            } else {
                System.out.println("Falha ao criar o diretório!");
            }
        }


        File file = new File("src/petsCadastrados/" + archiveName + ".txt");
        try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write("1 - " + getName() + "\n2 - " + getPetType() +
                    "\n3 - " + getPetGender() + "\n4 - " + getPetAddress().getCity() + ", " + getPetAddress().getStreet() + ", " + getPetAddress().getNumberHouse() +
                    "\n5 - " + getPetAge() + "\n6 - " + getPetWeight() +
                    "\n7 - " + getPetBreed());
            bw.flush();
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar seu pet: " + e.getMessage());
        }
        System.out.println("Pet cadastrado com sucesso!");
        buscarPet.buscarPet();
    }

}
