package com.pedro.agendadesalesebarbearias.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class SalaoBarbearia {

    private String id;
    private String email;
    private String password;
    private String name;
    private String type;
    private ArrayList<Professional> professional;
    private ArrayList<Service> services;
    private Address address;
    private float rating;

    public SalaoBarbearia() {

    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
