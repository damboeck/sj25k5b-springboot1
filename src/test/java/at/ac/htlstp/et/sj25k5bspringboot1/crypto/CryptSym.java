package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static at.ac.htlstp.et.sj25k5bspringboot1.crypto.Cryptographie.base64Decode;
import static at.ac.htlstp.et.sj25k5bspringboot1.crypto.Cryptographie.base64Encode;

public class CryptSym {

    private static final byte[] SALT = {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03
    };


    public static String encrypt(String property, String schluessel) throws GeneralSecurityException, UnsupportedEncodingException {
        return encrypt(property,schluessel.toCharArray(),SALT);
    }

	/*public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
		return encrypt(property, PASSWORD);
	}*/

    public static String encrypt(String property, char[] schluessel, byte[] salt) throws GeneralSecurityException, UnsupportedEncodingException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(schluessel));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(salt, 20));
        byte b[] = property.getBytes("UTF-8");
        byte c[] = pbeCipher.doFinal(b);
        return base64Encode(c);
    }


	/*public static String decrypt(String property) throws GeneralSecurityException, IOException {
		return decrypt(property, PASSWORD);
	}*/

    public static String decrypt(String property, String schluessel) throws GeneralSecurityException, IOException {
        return decrypt(property,schluessel.toCharArray(),SALT);
    }

    public static String decrypt(String property, String schluessel, String salt) throws GeneralSecurityException, IOException {
        byte[] mysalt = salt.getBytes();
        return decrypt(property,schluessel.toCharArray(),mysalt);
    }

    /**
     * Entschlüsseln eines Strings über einen bekannten Key
     *
     * @param property   Text, der zu entschlüsseln ist
     * @param schluessel Key, mit dem der Text entschlüsselt werden kann
     * @throws GeneralSecurityException  Fehlermeldung
     * @throws IOException Fehlermeldung
     * @return entschlüsselter Text
     */
    public static String decrypt(String property, char[] schluessel, byte[] salt) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(schluessel));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(salt, 20));
        byte b64[] = base64Decode(property);
        byte c[] = pbeCipher.doFinal(b64);
        return new String(c, "UTF-8");
    }



}
