package com.pedro.agendadesalesebarbearias.control;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfigurationFirebase {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth auth;
    private static StorageReference storageReference;

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

    public static StorageReference getStorageReference(){

        if(storageReference != null){
            storageReference = FirebaseStorage.getInstance().getReference();
        }

        return storageReference;
    }

}
