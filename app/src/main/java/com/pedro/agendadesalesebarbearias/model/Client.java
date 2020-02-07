package com.pedro.agendadesalesebarbearias.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.pedro.agendadesalesebarbearias.control.ConfigurationFirebase;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

public class Client {

    private String id;
    private String name;
    private String email;
    private String password;

    public Client() {

    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void saveClient(){
        DatabaseReference databaseReference = ConfigurationFirebase.getFirebaseReference();
        databaseReference.child(FirebaseControl.CLIENTS_DB).child( getId() ).setValue(this);
    }
}
