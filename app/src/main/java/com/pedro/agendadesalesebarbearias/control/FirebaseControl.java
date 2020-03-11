package com.pedro.agendadesalesebarbearias.control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.activity.ClientUserMainActivity;
import com.pedro.agendadesalesebarbearias.activity.CommerceUserMainActivity;
import com.pedro.agendadesalesebarbearias.activity.MainActivity;
import com.pedro.agendadesalesebarbearias.adapter.RvEmployeesAdapter;
import com.pedro.agendadesalesebarbearias.adapter.RvServicesAdapter;
import com.pedro.agendadesalesebarbearias.model.Client;
import com.pedro.agendadesalesebarbearias.model.Professional;
import com.pedro.agendadesalesebarbearias.model.SalaoBarbearia;
import com.pedro.agendadesalesebarbearias.model.Service;

public class FirebaseControl {

    private static FirebaseAuth auth;
    private static DatabaseReference databaseReferenceClient;
    private static DatabaseReference databaseReferenceCommerce;
    private static StorageReference storageReference;

    public static final String CLIENTS_DB = "clients";
    public static final String COMMERCE_DB = "commerces";
    public static final String SERVICES_DB = "services";
    public static final String EMPLOYEES_DB = "employees";
    public static final String AVATAR_IMG_NAME = "avatar";
    public static final String BG_IMG_NAME = "bg";

    private static ValueEventListener valueEventListenerClient;

    public static boolean Login(String email, String password){

        final String fEmail = email;

        auth = ConfigurationFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                        String userId = EncoderBase64.encoderBase64(fEmail);


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
        auth.createUserWithEmailAndPassword(fClient.getEmail(), client.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(fContext, fContext.getString(R.string.sign_up_is_successful)
                                    , Toast.LENGTH_SHORT).show();

                            fClient.saveClient();

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

    public static boolean signUpCommerce(Context context, SalaoBarbearia commerce){

        final Context fContext = context;
        final String userId = EncoderBase64.encoderBase64(commerce.getEmail());

        final SalaoBarbearia fCommerce = commerce;
        fCommerce.setId(userId);

        auth = ConfigurationFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(fCommerce.getEmail(), commerce.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(fContext, fContext.getString(R.string.sign_up_is_successful)
                                    , Toast.LENGTH_SHORT).show();

                            fCommerce.saveCommerce();

                            openCommerceUserMainActivity(fContext);
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

    public static void checkLogin(final Context context){

        auth = ConfigurationFirebase.getFirebaseAuth();

        if (auth.getCurrentUser() != null) {
            String userId = EncoderBase64.encoderBase64(auth.getCurrentUser().getEmail());
            databaseReferenceClient = ConfigurationFirebase.getFirebaseReference().child(CLIENTS_DB).child(userId);
            databaseReferenceCommerce = ConfigurationFirebase.getFirebaseReference().child(COMMERCE_DB).child(userId);

            final ValueEventListener valueEventListenerCommerce = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        openCommerceUserMainActivity( context );
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            ValueEventListener valueEventListenerClient = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        openClientUserMainActivity(context);
                    }else{
                        databaseReferenceCommerce.addListenerForSingleValueEvent(valueEventListenerCommerce);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            databaseReferenceClient.addListenerForSingleValueEvent(valueEventListenerClient);

        }else {
            openLoginMainActivity( context );
        }

    }

    public static void signOut(){
        auth = ConfigurationFirebase.getFirebaseAuth();
        if ( auth.getCurrentUser() != null ) {
            auth.signOut();
        }
    }

    public static void validateLogin(final Context context, String email, String password){
        auth = ConfigurationFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            checkLogin(context);
                        }else {
                            String errorExcep = "";

                            try{
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                errorExcep = context.getString(R.string.error_email_not_correct);
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                errorExcep = context.getString(R.string.error_passwaord_not_correct);;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            Toast.makeText(context, errorExcep, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public static void uploadImgInStorage(final Context context, String accountType, String imgTypeName, Uri uri){
        StorageReference imgStorageRef = ConfigurationFirebase.getStorageReference();
        auth = ConfigurationFirebase.getFirebaseAuth();

        if(auth != null){

            String userId = EncoderBase64.encoderBase64(auth.getCurrentUser().getEmail());

            imgStorageRef.child(accountType).child(userId).child(imgTypeName);

            imgStorageRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(context, context.getString(R.string.toast_image_uploaded_sucessfully), Toast.LENGTH_SHORT).show();
                    }else{
                        String message = task.getException().toString();
                        Toast.makeText(context, context.getString(R.string.toast_error_image_uploaded), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    public static void loadImgFromStorageIntoImageView(final Context context, String accountType, String imgTypeName, final AppCompatImageView imgView){
        storageReference = ConfigurationFirebase.getStorageReference();
        auth = ConfigurationFirebase.getFirebaseAuth();
        if(auth != null){
            String userId = EncoderBase64.encoderBase64(auth.getCurrentUser().getEmail());

            try{
                storageReference.child(accountType).child(userId).child(imgTypeName).getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(context)
                                        .load(uri)
                                        .into(imgView);
                            }
                        });
            }catch (NullPointerException e){
                Glide.with(context)
                        .load(R.drawable.img_default)
                        .into(imgView);
            }

        }

    }

    public static void setCommerceInfo(final Context context,
                                       final AppCompatTextView txtRating, final AppCompatTextView txtCommerceName,
                                       final RecyclerView rvServices, final RecyclerView rvEmployees){

        auth = ConfigurationFirebase.getFirebaseAuth();
        String userId = EncoderBase64.encoderBase64(auth.getCurrentUser().getEmail());

        databaseReferenceCommerce = ConfigurationFirebase.getFirebaseReference().child(COMMERCE_DB).child(userId);
        databaseReferenceCommerce.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    SalaoBarbearia commerce = dataSnapshot.getValue(SalaoBarbearia.class);

                    // Set info
                    // Load rating and commerce name
                    txtRating.setText(String.valueOf(commerce.getRating()));
                    txtCommerceName.setText(commerce.getName());

                    // Load services rv
                    RvServicesAdapter servicesAdapter = new RvServicesAdapter(commerce.getServices(), context);
                    RecyclerView.LayoutManager servicesLM = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    rvServices.setLayoutManager(servicesLM);
                    rvServices.setAdapter(servicesAdapter);

                    // Load employess
                    RvEmployeesAdapter employeesAdapter = new RvEmployeesAdapter(commerce.getProfessional(), context);
                    RecyclerView.LayoutManager  employeesLM = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    rvEmployees.setLayoutManager(employeesLM);
                    rvEmployees.setAdapter(employeesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, context.getString(R.string.toast_error_loading_info), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public static void setServiceInCurrentCommerce(final Context context, Service service){
        String currentUserId = EncoderBase64.encoderBase64( auth.getCurrentUser().getEmail() );
        databaseReferenceCommerce = ConfigurationFirebase.getFirebaseReference();

        databaseReferenceCommerce.child(COMMERCE_DB)
                .child(currentUserId).child(SERVICES_DB).setValue(service)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, context.getString(R.string.toast_successfully_add_service),
                        Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, context.getString(R.string.toast_error_add_service),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void setEmployeeInCurrentCommerce(final Context context, Professional employee){
        String currentUserId = EncoderBase64.encoderBase64( auth.getCurrentUser().getEmail() );
        databaseReferenceCommerce = ConfigurationFirebase.getFirebaseReference();
        databaseReferenceCommerce.child(COMMERCE_DB)
                .child(currentUserId).child(EMPLOYEES_DB).setValue(employee)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, context.getString(R.string.toast_successfully_add_employee),
                        Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, context.getString(R.string.toast_error_add_employee),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private static void openClientUserMainActivity(Context context){
        Intent intent = new Intent(context, ClientUserMainActivity.class);
        context.startActivity(intent);
    }

    private static void openCommerceUserMainActivity(Context context){
        Intent intent = new Intent(context, CommerceUserMainActivity.class);
        context.startActivity(intent);
    }

    private static void openLoginMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


}
