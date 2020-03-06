package com.pedro.agendadesalesebarbearias.model;

import java.util.ArrayList;

public class Service {

    private String name;
    private String price;
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
}
