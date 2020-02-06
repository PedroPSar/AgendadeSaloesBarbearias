package com.pedro.agendadesalesebarbearias.control;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.model.Client;

public class FirebaseControl {

    private static FirebaseAuth auth;
    private static DatabaseReference databaseReference;
    private static String userId;

    public static final String CLIENTS_DB = "clients";
    public static final String COMMERCE_DB = "commerces";

    private static ValueEventListener valueEventListenerClient;

    public static boolean Login(String email, String password){

        final String fEmail = email;

        auth = ConfigurationFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                        userId = EncoderBase64.encoderBase64(fEmail);


                        }else{



                        }

                    }
                });

        return true;
    }

    public static boolean signUpClient(Context context, Client client){

        final Context fContext = context;
        final String userId = EncoderBase64.encoderBase64(client.getEmail());

        final Client fClient = client;
        fClient.setId(userId);

        auth = ConfigurationFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(userId, client.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(fContext, fContext.getString(R.string.sign_up_is_successful)
                                    , Toast.LENGTH_SHORT).show();

                            fClient.salveClient();

                        }else{

                            String exceptionError = "";

                            try{
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                exceptionError = fContext.getString(R.string.enter_a_stronger_password);
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                exceptionError = fContext.getString(R.string.invalid_email);
                            } catch (FirebaseAuthUserCollisionException e) {
                                exceptionError = fContext.getString(R.string.this_email_is_already_in_use);
                            } catch (Exception e) {
                                exceptionError = fContext.getString(R.string.error_when_registering);
                            }

                            Toast.makeText(fContext, exceptionError, Toast.LENGTH_SHORT).show();

                        }

                    }
                });

        return true;
    }
}
