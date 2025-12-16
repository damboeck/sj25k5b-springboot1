package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CryptoTest {

    public static void main(String[] args) throws Exception {
        String text = "Hello World!";
        String md5  = Cryptographie.md5(text);
        System.out.println("md5: " + md5);
        text = "Hello_World!";
        md5  = Cryptographie.md5(text);
        System.out.println("md5: " + md5);
        text = Files.readString((new File("data/faust.txt")).toPath());
        System.out.println("Calculating md5 for Faust...-Size:"+text.length());
        long startTime = System.currentTimeMillis();
        for (int i=0;i<10;i++)
            md5  = Cryptographie.md5(text);
        long endTime = System.currentTimeMillis();
        System.out.println("md5: " + md5);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        String sha256 = Cryptographie.sha256(text);
        String sha512 = Cryptographie.sha512(text);
        String sha512b64 = Cryptographie.sha512Base64(text);
        System.out.println("sha256: " + sha256);
        System.out.println("sha512: " + sha512);
        System.out.println("sha512b64: " + sha512b64);

        startTime = System.currentTimeMillis();
        for (int i=0;i<10;i++)
            md5  = Cryptographie.sha512(text);
        endTime = System.currentTimeMillis();
        System.out.println("sha512: " + md5);
        System.out.println("Time taken(sha512): " + (endTime - startTime) + " ms");

        String faustb64 = Cryptographie.base64Encode(text);

        text = "Hello Welt!";
        String b64 = Cryptographie.base64Encode(text);
        String decoded = Cryptographie.base64DecodeString(b64);

        System.out.println("decoded: " + decoded);
        System.out.println("b64: " + b64);

        text = "Hallo Welt!";
        b64 = Cryptographie.base64Encode(text);
        decoded = Cryptographie.base64DecodeString(b64);

        System.out.println("decoded: " + decoded);
        System.out.println("b64: " + b64);
    }
}
