package com.pedro.agendadesalesebarbearias.model;

import java.util.ArrayList;

public class SalaoBarbearia {

    private String name;
    private String type;
    private ArrayList<Professional> professional;
    private ArrayList<Service> services;
    private Address address;
    private float rating;

    public SalaoBarbearia() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Professional> getProfessional() {
        return professional;
    }

    public void setProfessional(ArrayList<Professional> professional) {
        this.professional = professional;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
