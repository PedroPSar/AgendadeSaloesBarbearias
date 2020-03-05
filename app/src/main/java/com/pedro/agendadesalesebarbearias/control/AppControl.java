package com.pedro.agendadesalesebarbearias.control;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class AppControl {

    public static String getExtension(Context context, Uri uri){
        ContentResolver cr = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
}
