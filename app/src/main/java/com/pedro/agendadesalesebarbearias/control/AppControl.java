package com.pedro.agendadesalesebarbearias.control;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.pedro.agendadesalesebarbearias.model.Professional;
import com.pedro.agendadesalesebarbearias.model.Service;

import java.util.ArrayList;

public class AppControl {

    public static ArrayList<Professional> professionals;

    public static String getExtension(Context context, Uri uri){
        ContentResolver cr = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public static int convertServiceTimeInUnit(String choose){
        int result = 0;
        String[] times = Service.TIMES;
        String teste = times[0];

        switch (choose){
            case "30 minutos":
                result = 1;
                break;

            case "1 hora":
                result = 2;
                break;

            case "1 hora e 30 minutos":
                result = 3;
                break;

            case "2 horas":
                result = 4;
                break;

            case "2 horas e 30 minutos":
                result = 5;
                break;

            case "3 horas":
                result = 6;
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }

}
