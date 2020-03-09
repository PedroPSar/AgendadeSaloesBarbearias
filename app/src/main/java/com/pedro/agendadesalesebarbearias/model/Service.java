package com.pedro.agendadesalesebarbearias.model;

import java.util.ArrayList;

public class Service {

    private String name;
    private String price;

    // Service time in 30 minute units
    private int serviceTime;
    private ArrayList<Professional> professionals;

    public Service() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public ArrayList<Professional> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(ArrayList<Professional> professionals) {
        this.professionals = professionals;
    }

}
