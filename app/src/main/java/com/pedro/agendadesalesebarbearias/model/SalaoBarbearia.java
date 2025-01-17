package com.pedro.agendadesalesebarbearias.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.pedro.agendadesalesebarbearias.control.ConfigurationFirebase;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

import java.util.ArrayList;

public class SalaoBarbearia {

    private String id;
    private String email;
    private String password;
    private String name;
    private String tel;
    private String type;
    private ArrayList<Professional> professional;
    private ArrayList<Service> services;
    private Address address;
    private String openingTime;
    private String closingTime;
    private float rating;

    public static final String BEAUTY_PARLOR_TYPE = "1";
    public static final String BARBERSHOP_TYPE = "2";

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Exclude
    public ArrayList<Professional> getProfessional() {
        return professional;
    }

    public void setProfessional(ArrayList<Professional> professional) {
        this.professional = professional;
    }

    @Exclude
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

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public void saveCommerce(){
        DatabaseReference databaseReference = ConfigurationFirebase.getFirebaseReference();
        databaseReference.child(FirebaseControl.COMMERCE_DB).child( getId() ).setValue(this);
    }
}
