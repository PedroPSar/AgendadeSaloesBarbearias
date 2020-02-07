package com.pedro.agendadesalesebarbearias.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.activity.MainActivity;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.model.Client;


public class SignUpClientFragment extends Fragment {

    private AppCompatEditText edTxtUserName;
    private AppCompatEditText edTxtEmail;
    private AppCompatEditText edTxtPassword;

    private LinearLayoutCompat llSignIn;
    private Button btnSignUp;

    public SignUpClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up_client, container, false);
        return view;
    }

     @Override
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);

        edTxtUserName = view.findViewById(R.id.editTxtUserName);
        edTxtEmail = view.findViewById(R.id.editTxtEmail);
        edTxtPassword = view.findViewById(R.id.editTextPassword);
        llSignIn = view.findViewById(R.id.ll_signIn);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        llSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Client client = new Client();
                client.setName(edTxtUserName.getText().toString());
                client.setEmail(edTxtEmail.getText().toString());
                client.setPassword(edTxtPassword.getText().toString());

                FirebaseControl.signUpClient(getActivity(), client);
            }
        });

     }

 }
