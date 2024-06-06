package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptacion {
    public static String SHA256="SHA-256";
    private static String convertirAHexadecimal(byte[] digest){
        String hash="";
        // foreach
        for(byte aux:digest){
            int b=aux&0xff;
            if(Integer.toHexString(b).length()==1){
                hash+="0";
            }
            hash+=Integer.toHexString(b);
        } // foreach

        return hash;
    } // convertirAHexadecimal

    public static String obtenerContraseniaCifrada(String mensaje) throws NoSuchAlgorithmException{
        String algoritmo=SHA256;
        byte[] digest=null;

        byte[] buffer=mensaje.getBytes();
        MessageDigest messageDigest=MessageDigest.getInstance(algoritmo);
        messageDigest.reset();
        messageDigest.update(buffer);
        digest=messageDigest.digest();
        //System.out.println(digest);
        return convertirAHexadecimal(digest);

    } // obtenerContraseniaSifrada
}
