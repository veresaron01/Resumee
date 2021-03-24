package numbercompiler;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArabicToRoman {

    public String arabicToRoman(String arabStr) {
        int arab = Integer.parseInt(arabStr);
        if (arab > 5999) {
            return "Maximum 5999.";
        }
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> numbers = new LinkedHashMap<String, Integer>();
        numbers.put("M", 1000);
        numbers.put("CM", 900);
        numbers.put("D", 500);
        numbers.put("CD", 400);
        numbers.put("C", 100);
        numbers.put("XC", 90);
        numbers.put("L", 50);
        numbers.put("XL", 40);
        numbers.put("X", 10);
        numbers.put("IX", 9);
        numbers.put("V", 5);
        numbers.put("IV", 4);
        numbers.put("I", 1);

        for (Map.Entry entry : numbers.entrySet()) {
            while (arab >= (int) entry.getValue()) {
                arab = arab - (int) entry.getValue();
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

}


