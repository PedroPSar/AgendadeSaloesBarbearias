package com.pedro.agendadesalesebarbearias.control;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.webkit.MimeTypeMap;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pedro.agendadesalesebarbearias.model.Professional;
import com.pedro.agendadesalesebarbearias.model.Service;

import java.util.ArrayList;
import java.util.List;

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

    public static boolean permissionsValidator(int requestCode, Activity activity, String[] permissions){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> permissionsList = new ArrayList<>();

            for( String permission: permissions){
                boolean permissionValid = ContextCompat.checkSelfPermission(activity, permission)
                        == PackageManager.PERMISSION_GRANTED;
                if(!permissionValid) permissionsList.add(permission);
            }

            //Caso a lista esteja vazia não há necessidade de solicitar a permissão
            if(permissionsList.isEmpty()) return  true;

            String[] arrayPermissions = new String[permissionsList.size()];
            permissionsList.toArray(arrayPermissions);

            //Solicita permissão
            ActivityCompat.requestPermissions(activity, arrayPermissions, requestCode);
        }
        return true;
    }

}
