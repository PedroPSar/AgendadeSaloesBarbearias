package com.pedro.agendadesalesebarbearias.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.asynctasks.CheckLoginAsyncTask;

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

        CheckLoginAsyncTask task = new CheckLoginAsyncTask();
        task.execute(this);
    }
}
