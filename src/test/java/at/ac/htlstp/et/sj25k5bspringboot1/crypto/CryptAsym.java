package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static at.ac.htlstp.et.sj25k5bspringboot1.crypto.Cryptographie.base64Decode;
import static at.ac.htlstp.et.sj25k5bspringboot1.crypto.Cryptographie.base64Encode;

public class CryptAsym {

    /**
     * Erzeugt eine RSA Schlüsselpaar mit einer Schlüssellänge von 1024 bit
     * @return Schlüsselpaar
     * @throws NoSuchAlgorithmException Fehler wenn RSA nicht funktioniert
     */
    public static KeyPair generateRSAkeypair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        return pair;
    }

    public static String privateKeyBase64FromRSAkeypair(KeyPair keyPair) {
        if (keyPair==null) return null;
        return base64Encode(keyPair.getPrivate().getEncoded());
    }

    public static PrivateKey privateKeyFromBase64(String privateKeyBase64) {
        try {
            byte[] keyBytes = base64Decode(privateKeyBase64);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (Exception e) { }
        return null;
    }

    public static String publicKeyBase64FromRSAkeypair(KeyPair keyPair) {
        if (keyPair==null) return null;
        return base64Encode(keyPair.getPublic().getEncoded());
    }

    public static PublicKey publicKeyFromBase64(String publicKeyBase64) {
        try {
            byte[] keyBytes = base64Decode(publicKeyBase64);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        } catch (Exception e) { }
        return null;
    }

    public static String encryptTextRSAprivate(String text, String privateKeyBase64){
        if (text==null || privateKeyBase64==null) return null;
        PrivateKey privateKey = privateKeyFromBase64(privateKeyBase64);
        if (privateKey==null) return null;
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            String encrypted = base64Encode(cipher.doFinal(text.getBytes("UTF-8")));
            return encrypted;
        } catch (Exception e) {	}
        return null;
    }

    public static String decryptTextRSApublic(String encryptedText, String publicKeyBase64){
        if (encryptedText==null || publicKeyBase64==null) return null;
        PublicKey publicKey = publicKeyFromBase64(publicKeyBase64);
        if (publicKey==null) return null;
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            String text = new String(cipher.doFinal(base64Decode(encryptedText)),"UTF-8");
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptTextRSApublic(String text, String publicKeyBase64){
        if (text==null || publicKeyBase64==null) return null;
        PublicKey publicKey = publicKeyFromBase64(publicKeyBase64);
        if (publicKey==null) return null;
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            String encrypted = base64Encode(cipher.doFinal(text.getBytes("UTF-8")));
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptTextRSAprivate(String encryptedText, String privateKeyBase64){
        if (encryptedText==null || privateKeyBase64==null) return null;
        PrivateKey privateKey = privateKeyFromBase64(privateKeyBase64);
        if (privateKey==null) return null;
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            String text = new String(cipher.doFinal(base64Decode(encryptedText)),"UTF-8");
            return text;
        } catch (Exception e) {	}
        return null;
    }
}
