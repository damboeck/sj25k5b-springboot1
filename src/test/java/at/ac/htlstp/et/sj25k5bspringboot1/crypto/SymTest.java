package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

public class SymTest {

    public static void main(String[] args) throws Exception {
        String password ="mysecretpassword";
        String originalString = "Hello, World!";
        String enc = CryptSym.encrypt(originalString,password);
        System.out.println("originalString: " + originalString);
        System.out.println("encryptedString: " + enc);
        String dec = CryptSym.decrypt(enc,password);
        System.out.println("decryptedString: " + dec);

        String decOtherSalt = CryptSym.decrypt(enc,password,"12345678");
        System.out.println("decryptedString with other salt: " + decOtherSalt);
    }

}
