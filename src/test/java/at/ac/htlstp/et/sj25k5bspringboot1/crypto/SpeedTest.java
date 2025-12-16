package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpeedTest {

    public static final String FILE_PATH = "data/faust.txt";

    public static void main(String[] args) throws Exception {
        String faust = Files.readString(Path.of(FILE_PATH));
        long startTime = System.currentTimeMillis();
        Cryptographie.base64Encode(faust);
        long endTime = System.currentTimeMillis();
        System.out.println("Encoding Time: " + (endTime - startTime) + " ms");


    }
}
