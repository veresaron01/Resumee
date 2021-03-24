package numbercompiler;

import java.util.*;

public class RomanToArabic {

    public int romanToArabic(String rom) {

        Map<String, Integer> numbers = new LinkedHashMap<String, Integer>();
        numbers.put("CM", 900);
        numbers.put("M", 1000);
        numbers.put("CD", 400);
        numbers.put("D", 500);
        numbers.put("XC", 90);
        numbers.put("C", 100);
        numbers.put("XL", 40);
        numbers.put("L", 50);
        numbers.put("IX", 9);
        numbers.put("X", 10);
        numbers.put("IV", 9);
        numbers.put("V", 5);
        numbers.put("I", 1);

        int arab = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(rom);

        for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
            while (sb.toString().contains(entry.getKey())) {
                arab = arab + entry.getValue();
                if (entry.getKey().length() == 1) {
                    int shiftIndex = sb.indexOf(entry.getKey());
                    sb.replace(shiftIndex, shiftIndex + 1, " ");
                } else if (entry.getKey().length() == 2) {
                    int shiftIndex = sb.indexOf(entry.getKey());
                    sb.replace(shiftIndex, shiftIndex + 2, " ");
                }
            }
        }
        return arab;
    }

    public List<String> generateRegexes() {
        String[] s1 = new String[]{"(M){0,5}", "(CM)?", "(CD)?", "(D)?"};
        String[] s2 = new String[]{"(C){0,3}"};
        String[] s3 = new String[]{"(XC)?", "(XL)?", "(L)?"};
        String[] s4 = new String[]{"(X){0,3}"};
        String[] s5 = new String[]{"(IX)?", "(IV)?", "(V)?"};
        String[] s6 = new String[]{"(I){0,3}"};

        List<String> regexes = new ArrayList();
        List<String> regexesFinish = new ArrayList();
        List<String> partRegexes1 = new ArrayList();
        List<String> partRegexes2 = new ArrayList();
        List<String> partRegexes3 = new ArrayList();
        List<String> partRegexes4 = new ArrayList();
        List<String> partRegexes5 = new ArrayList();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= 3; i++) {
            sb.append(s1[0]);
            sb.append(s1[i]);
            partRegexes1.add(sb.toString());
            sb.delete(0, sb.length());
        }

        for (String part1 : partRegexes1) {
            if (!(part1.contains(s1[1]) || (part1.contains(s1[2])))) {
                sb.append(part1).append(s2[0]);
                partRegexes2.add(sb.toString());
                sb.delete(0, sb.length());
            } else {
                sb.append(part1);
                partRegexes2.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }

        for (String part2 : partRegexes2) {
            for (int i = 0; i <= 2; i++) {
                sb.append(part2).append(s3[i]);
                partRegexes3.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }

        for (String part3 : partRegexes3) {
            if (!(part3.contains(s3[0]) || (part3.contains(s3[1])))) {
                sb.append(part3).append(s4[0]);
                partRegexes4.add(sb.toString());
                sb.delete(0, sb.length());
            } else {
                sb.append(part3);
                partRegexes4.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }

        for (String part4 : partRegexes4) {
            for (int i = 0; i <= 2; i++) {
                sb.append(part4).append(s5[i]);
                partRegexes5.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }

        for (String part5 : partRegexes5) {
            if (!(part5.contains(s5[0]) || (part5.contains(s5[1])))) {
                sb.append(part5).append(s6[0]);
                regexes.add(sb.toString());
                sb.delete(0, sb.length());
            } else {
                sb.append(part5);
                regexes.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }

        for (String reg : regexes) {
            if (reg.contains("(M){0,5}(M){0,5}")) {
                String newReg = reg.replace("(M){0,5}(M){0,5}", "(M){0,5}");
                regexesFinish.add(newReg);
            } else {
                regexesFinish.add(reg);
            }
        }
        return regexesFinish;
    }
}
