package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class AsymTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair = CryptAsym.generateRSAkeypair();
        String privateKey = CryptAsym.privateKeyBase64FromRSAkeypair(keyPair);
        String publicKey  = CryptAsym.publicKeyBase64FromRSAkeypair(keyPair);
        System.out.println("privateKey: " + privateKey);
        System.out.println("publicKey: " + publicKey);

        /*KeyPair keyPair1 = CryptAsym.generateRSAkeypair();
        String privateKey1 = CryptAsym.privateKeyBase64FromRSAkeypair(keyPair1);
        System.out.println("privateKey: " + privateKey1);*/

        String text = "Das ist ein geheimer Text!";
        String encryptedText = CryptAsym.encryptTextRSAprivate(text, privateKey);
        System.out.println("encryptedText: " + encryptedText);
        String decryptedText = CryptAsym.decryptTextRSApublic(encryptedText, publicKey);
        System.out.println("decryptedText: " + decryptedText);



    }
}
