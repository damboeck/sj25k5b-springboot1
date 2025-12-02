package at.ac.htlstp.et.sj25k5bspringboot1.crypto2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackRot {

    public static final String[] dictionary = {
        "der", "die", "und", "den", "von", "das", "mit", "sich",
        "des", "auf", "für", "ist", "dem", "nicht", "ein", "eine",
        "als", "auch", "werden", "aus", "hat", "dass", "sie"
    };

    /**
     * Bewertet den Text und gibt einen Score zurück.
     * @param text Text der
     * @return     0 bei schlechtem Text, höherer Wert bei besserem Text
     */
    public static int analyzeText(String text) {
        int score = 0;
        for (String word : dictionary) {
            int count = countWordOccurrences(text, word);
            score += count;
        }
        return score;
    }

    public static int countWordOccurrences(String text, String word) {
        Pattern wordPattern = Pattern.compile(
                "(^|\n|[,;\\s:\"'\\(\\)\\.])" + Pattern.quote(word) + "($|\n|[,;\\s:\"'\\(\\)\\.])",
                Pattern.CASE_INSENSITIVE
        );
        Matcher m= wordPattern.matcher(text);
        int count=0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    public static char rotBruteForceHack(String text) {
        int  bestScore = Integer.MIN_VALUE;
        char bestShift = 'a';
        //text = text.substring(0,500);
        for (char c='a';c<='z';c++) {
            String decrypted = Rot.rotDecrypt(text, c);
            int score = analyzeText(decrypted);
            //System.out.println("Shift " + c + " Score: " + score);
            if (score > bestScore) {
                bestScore = score;
                bestShift = c;
            }
        }
        return bestShift;
    }

    public static char rotHaeufigkeitsHack(String text){
        HashMap<Character, Integer> freqMap = new HashMap<>();
        char[] chars = text.toCharArray();
        //for (int i=0;i<Integer.min(chars.length,500);i++) {
        for (int i=0;i<chars.length;i++) {
            char c = Character.toLowerCase(chars[i]);
            if (c>='a' && c<='z') {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }
        char mostFrequentChar = 'a';
        int maxFreq = 0;
        for (char c : freqMap.keySet()) {
            int freq = freqMap.get(c);
            if (freq > maxFreq) {
                maxFreq = freq;
                mostFrequentChar = c;
            }
        }
        System.out.println("mostFrequentChar="+freqMap);
        return (char)((mostFrequentChar - 'e' + 26) % 26 + 'a');
    }

    public static void main(String[] args) throws IOException {
        String encryptedFile = "data/geschichte2.chf";
        encryptedFile = "data/faust.chf";
        String encryptedText = Files.readString(Paths.get(encryptedFile));
        long start = System.currentTimeMillis();
        char bestShift = rotBruteForceHack(encryptedText);
        long end = System.currentTimeMillis();
        System.out.println("Brute-Force Hack took " + (end - start)+"ms");
        System.out.println("Best shift found: " + bestShift);
        String decryptedText = Rot.rotDecrypt(encryptedText, bestShift);
        System.out.println("Decrypted text:\n" + decryptedText.substring(0,200));

        System.out.println("\n---- Häufigkeitshack ----");
        start = System.currentTimeMillis();
        bestShift = rotHaeufigkeitsHack(encryptedText);
        end = System.currentTimeMillis();
        System.out.println("Häufigkeitshack took " + (end - start) +"ms");
        System.out.println("Best shift found: " + bestShift);
        decryptedText = Rot.rotDecrypt(encryptedText, bestShift);
        System.out.println("Decrypted text:\n" + decryptedText.substring(0,200));
    }

}
