package numbercompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CompleteARRA {

    // Type in a Roman number between I and MMMMMCMXCIX inclusive. Get the arabic number version of it. OR
    // Type in a number between 0 and 5999 inclusive. Get the roman version of it. OR
    // Type in "exit" to end the process.

    public static void main(String[] args) throws IOException {

        ArabicToRoman ar = new ArabicToRoman();
        RomanToArabic ra = new RomanToArabic();

        List<String> regexes = ra.generateRegexes();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (!(input = reader.readLine()).equals("exit")) {
            if (input.matches("[^[\\w&&[^MDCLXVI]]]+")) {
                boolean b = false;
                label:
                for (String regex : regexes) {
                    if (input.matches(regex)) {
                        System.out.println(ra.romanToArabic(input));
                        b = true;
                        break label;
                    }
                }
                if (!b) {
                    System.out.println("False roman syntax.");
                }
            } else if (input.matches("[\\d]+")) {
                System.out.println(ar.arabicToRoman(input));//ar.atr
            }
        }
    }
}




