package com.pedro.agendadesalesebarbearias.model;

public class Address {

    public static final String[] states = {"AC", "AL", "AP", "AM", "BA", "CE", "DF",
                                     "ES", "GO", "MA", "MT", "MS", "MG", "PA",
                                     "PB", "PR", "PE", "PI", "RJ", "RN", "RS",
                                     "RO", "SC", "SP", "SE", "TO"};

    private String state;
    private String city;
    private String district;
    private String street;
    private String houseNumber;

    public Address() {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
