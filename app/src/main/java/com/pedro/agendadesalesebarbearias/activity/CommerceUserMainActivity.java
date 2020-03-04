package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

public class CommerceUserMainActivity extends AppCompatActivity {

    private Button btnDeslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce_user_main);

        //btnDeslogar = findViewById( R.id.btnDeslogar );

        /*btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseControl.signOut();
                Intent intent = new Intent( CommerceUserMainActivity.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        });*/
    }
}
