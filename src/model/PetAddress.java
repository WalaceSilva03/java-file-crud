package model;

public class PetAddress {
    private String numberHouse;
    private String city;
    private String street;

    public PetAddress() {
    }

    public PetAddress(String city, String street, String numberHouse) {
        this.numberHouse = numberHouse;
        this.city = city;
        this.street = street;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public PetAddress receiverString(String input){
        String[] inputs = input.split(", ");
        String city = inputs[0];
        String street = inputs[1];
        String numberHouse = inputs[2];
        return new PetAddress(city, street, numberHouse);
    }
}
