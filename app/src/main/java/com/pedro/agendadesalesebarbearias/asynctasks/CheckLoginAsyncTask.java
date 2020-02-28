package com.pedro.agendadesalesebarbearias.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.pedro.agendadesalesebarbearias.control.FirebaseControl;

public class CheckLoginAsyncTask extends AsyncTask<Context, Void, Void> {
    
    @Override
    protected Void doInBackground(Context... contexts) {

        FirebaseControl.checkLogin(contexts[0]);

        return null;
    }

}
