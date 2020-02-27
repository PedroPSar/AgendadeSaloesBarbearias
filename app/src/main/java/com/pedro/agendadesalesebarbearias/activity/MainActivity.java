package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.asynctasks.CheckLoginAsyncTask;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogar);
        signUpText = findViewById(R.id.tv_signUp);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        CheckLoginAsyncTask task = new CheckLoginAsyncTask();
        task.execute(this);
    }
}
