package com.pedro.agendadesalesebarbearias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CheckLoginActivity extends AppCompatActivity {

    ImageView loadingGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);

        loadingGif = findViewById(R.id.loadingGif);

        Glide.with(this)
                .load(R.drawable.loading)
                .into(loadingGif);
    }
}
