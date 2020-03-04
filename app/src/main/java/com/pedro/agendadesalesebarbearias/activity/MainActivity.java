package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView signUpText;
    private AppCompatEditText etEmail;
    private AppCompatEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogar);
        signUpText = findViewById(R.id.tv_signUp);
        etEmail = findViewById(R.id.editTxtEmail);
        etPassword = findViewById(R.id.editTxtPassword);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserClientDetailsActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                FirebaseControl.validateLogin(MainActivity.this, email, password);
            }
        });

    }
}
