package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Cyptographie {

    /**
     * erzeugt eine MD5-Prüfsumme
     *
     * @param s String
     * @return MD5 Prüfsumme
     */
    public static String md5(String s) {
        String hash = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(s.getBytes());
            for (int i = 0; i < digest.length; i++) {
                String p = (Integer.toHexString(digest[i] & 0xff));
                if (p.length()<2) p = "0"+p;
                hash += p;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public static String sha512(File file) {
        String hash = "";
        try {
            byte[] digest = MessageDigest
                    .getInstance("SHA-512")
                    .digest(Files.readAllBytes(file.toPath()));
            for (int i = 0; i < digest.length; i++) {
                String p = (Integer.toHexString(digest[i] & 0xff));
                if (p.length()<2) p = "0"+p;
                hash += p;
            }
        } catch (Exception ex) {}
        return hash;
    }

    public static String sha512(String s) {
        String hash = "";
        try {
            byte[] digest = MessageDigest
                    .getInstance("SHA-512")
                    .digest(s.getBytes());
            for (int i = 0; i < digest.length; i++) {
                String p = (Integer.toHexString(digest[i] & 0xff));
                if (p.length()<2) p = "0"+p;
                hash += p;
            }
        } catch (Exception ex) {}
        return hash;
    }

    public static String sha256(String s) {
        String hash = "";
        try {
            byte[] digest = MessageDigest
                    .getInstance("SHA-256")
                    .digest(s.getBytes());
            for (int i = 0; i < digest.length; i++) {
                String p = (Integer.toHexString(digest[i] & 0xff));
                if (p.length()<2) p = "0"+p;
                hash += p;
            }
        } catch (Exception ex) {}
        return hash;
    }

    /**
     * Erzeugt aus einem Base64-codierten String den ursprünglichen binären Dateiinhalt
     * @param base64encodedString Base64-codierter String
     * @return                    Dateiinhalt
     * @throws IOException        Fehler wenn etwas nicht funktioniert hat
     */
    public static byte[] base64Decode(String base64encodedString) throws IOException {
        byte[] base64decodedBytes = Base64.getMimeDecoder().decode(base64encodedString);
        return base64decodedBytes;
    }

}
