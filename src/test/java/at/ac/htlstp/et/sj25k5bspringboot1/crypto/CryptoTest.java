package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import java.io.IOException;

public class CryptoTest {

    public static void main(String[] args) throws Exception {
        String text = "Hello World!";
        String md5  = Cryptographie.md5(text);
        System.out.println("md5: " + md5);
        text = "Hello_World!";
        md5  = Cryptographie.md5(text);
        System.out.println("md5: " + md5);
        String sha256 = Cryptographie.sha256(text);
        String sha512 = Cryptographie.sha512(text);
        System.out.println("sha256: " + sha256);
        System.out.println("sha512: " + sha512);

        String b64 = Cryptographie.base64Encode(text);
        String decoded = Cryptographie.base64DecodeString(b64);

        System.out.println("decoded: " + decoded);
        System.out.println("b64: " + b64);
    }
}
