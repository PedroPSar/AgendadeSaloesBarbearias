package com.pedro.agendadesalesebarbearias.control;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigurationFirebase {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth auth;

    public static DatabaseReference getFirebaseReference(){

        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return databaseReference;
    }

    public static FirebaseAuth getFirebaseAuth(){

        if(auth == null){

            auth = FirebaseAuth.getInstance();

        }

        return auth;
    }

}
