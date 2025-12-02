package at.ac.htlstp.et.sj25k5bspringboot1.crypto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Pattern;

public class HackRot {


    public static final String FILE="faust";
    public static final String SRC_FILE = "data/"+FILE+".txt";
    public static final String CHIFFRE_FILE = "data/"+FILE+".chf";
    public static final String[] dict = {"ist","der","und","die","den","von","das","mit","sich","des",
            "auf","für","dem","nicht","ein","Die","eine","als","auch","werden","aus",
            "hat","dass","sie","nach","wird","bei","einer"};

    public static int countWords(String text, String word){
        int count = 0;
        Pattern WORD = Pattern.compile("(^|[\s.,;:!\\?()\"\n])"+word+"($|[\s.,;:!\\?()\"\n])", Pattern.CASE_INSENSITIVE);
        var matcher = WORD.matcher(text);
        while (matcher.find()) count++;
        return count;
    }

    /**
     * Analysiere den Text und gib einen Score zurück, wie "deutsch" der Text ist.
     * @param text Text
     * @return     Score (je höher, desto besser)
     */
    public static double analyzeText(String text){
        double result =0;
        for (String w : dict) {
            result += countWords(text, w);
        }
        return result;
    }

    public static char hackRotBruteForce(String chiffre) {
        // Brute Force Angriff
        double bestScore = -1;
        char bestChar = ' ';
        for (char c = 'a'; c <= 'z'; c++) {
            String entschl = Rot.rotDecrypt(chiffre, c);
            double score = analyzeText(entschl);
            //System.out.println("code: " + c + " => Score = " + score);
            if (score > bestScore) {
                bestScore = score;
                bestChar = c;
            }
        }
        return bestChar;
    }

    public static char hackRotWithFrequencyAnalysis(String chiffre) {
        char[] c = chiffre.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : c) {
            ch = Character.toLowerCase(ch);
            if (ch>='a' && ch<='z') {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }
        int max = 0;
        char freqChar = ' ';
        for (char ch='a'; ch<='z'; ch++) {
            int count = map.getOrDefault(ch, 0);
            if (count>max) {
                max = count;
                freqChar = ch;
            }
        }
        char key = (char)('a'+(freqChar-'e'+26)%26);
        return key;
    }

    public static void main(String[] args) throws IOException {
        // Erzeuge mal wieder eine Chiffre-Datei
        String s = Files.readString(Paths.get(SRC_FILE));
        String chiffre = Rot.rotEncrypt(s,'t');
        Files.writeString(Paths.get(CHIFFRE_FILE), chiffre);
        System.out.println("Analysiere Originaltext: Score = " + analyzeText(s));
        long startTime = System.currentTimeMillis();
        // Brute Force Angriff
        char key = hackRotBruteForce(chiffre);
        long endTime = System.currentTimeMillis();
        System.out.println("Der Schlüssel ist vermutlich: " + key+" (Dauer: "+(endTime-startTime)+" ms)");
        startTime = System.currentTimeMillis();
        // Häufigkeitsanalyse
        char key2 = hackRotWithFrequencyAnalysis(chiffre);
        endTime = System.currentTimeMillis();
        System.out.println("Der Schlüssel ist vermutlich: " + key2+" (Dauer: "+(endTime-startTime)+" ms)");
    }

}
