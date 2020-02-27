package com.pedro.agendadesalesebarbearias.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

public class CheckLoginAsyncTask extends AsyncTask<Context, Void, Void> {

    @Override
    protected Void doInBackground(Context... contexts) {

        //Toast.makeText(contexts[0], "Checando login", Toast.LENGTH_LONG).show();

        FirebaseControl.checkLogin(contexts[0]);

        return null;
    }

}
