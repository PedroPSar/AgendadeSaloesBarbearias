package com.pedro.agendadesalesebarbearias.control;

import android.util.Base64;

public class EncoderBase64 {

    public static String encoderBase64(String email){
        return Base64.encodeToString(email.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }
    public static String decoderBase64(String code){
        return new String(Base64.decode(code, Base64.DEFAULT));
    }
}
