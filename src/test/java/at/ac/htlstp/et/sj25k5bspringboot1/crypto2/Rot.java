package at.ac.htlstp.et.sj25k5bspringboot1.crypto2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Rot {

    /**
     * Applies a ROT cipher to the input string using the provided key character.
     * @param input the string to be encrypted
     * @param key the character key for the ROT cipher
     * @return  the encrypted string
     */
    public static String rot(String input, char key) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++)
            chars[i] = rot(chars[i], Character.toLowerCase(key));
        String result = String.valueOf(chars);
        return result;
    }

    private static char rot(char c, char key) {
        if (c >= 'a' && c <= 'z') {
            return (char)(((c - 'a' + (key - 'a')) % 26) + 'a');
        } else if (c >= 'A' && c <= 'Z') {
            return (char)(((c - 'A' + (key - 'a')) % 26) + 'A');
        } else {
            return c;
        }
    }

    public static String rotDecrypt(String input, char key) {
        return rot(input, (char)(26 - (key - 'a') + 'a'));
    }

    public static void main(String[] args) throws IOException {
        String text = "Hello,World!";
        char key = 'f';
        String encrypted = rot(text, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + rotDecrypt(encrypted, key));
        String s = Files.readString((new File("data/geschichte2.txt")).toPath());
        String encryptedFile = rot(s, 'R');
        Files.writeString((new File("data/geschichte2.chf")).toPath(), encryptedFile);
    }
}
