package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

public class Rot {


    public static char rotChar(char c, int offset) {
        if (c>='a' && c<='z') {
            return (char) ('a' + (c - 'a' + offset + 26) % 26);
        } else if (c>='A' && c<='Z') {
            return (char) ('A' + (c - 'A' + offset + 26) % 26);
        } else {
            return c;
        }
    }

    public static String rotEncrypt(String input, char shiftChar) {
        return rot(input, shiftChar,true);
    }
    public static String rotDecrypt(String input, char shiftChar) {
        return rot(input, shiftChar,false);
    }
    public static String rot(String input, char shiftChar,boolean encrypt) {
        int offset = Character.toLowerCase(shiftChar) - 'a';
        char[] chars = input.toCharArray();
        for (int i=0; i<chars.length; i++) {
            chars[i] = rotChar(chars[i], encrypt?offset:-offset);
        }
        String output = String.copyValueOf(chars);
        return output;
    }

    public static void main(String[] args) {
        String s   = "Hello World";
        char   key = 'e';
        String encrypted = rotEncrypt(s, key);
        String decrypted = rotDecrypt(encrypted, key);
        System.out.printf("Original:  %s\n", s);
        System.out.printf("Encrypted: %s\n", encrypted);
        System.out.printf("Decrypted: %s\n", decrypted);
    }
}
