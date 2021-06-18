package amoeba.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {

    BufferedReader reader;

    public UserInput() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public char getInputFromConsole() throws IOException {
        char c;
        String s;
        while (!(s = reader.readLine()).matches(".")) {
        }
        c = s.charAt(0);
        return c;
    }

    public int getStepInput(int reg) throws IOException {
        String regex = "[1-" + reg + "]";
        char inputChar;
        System.out.println(regex);

        while (!String.valueOf((inputChar = getInputFromConsole())).matches(regex)) {
            System.out.println("Number from 1 - " + reg);
        }
        int result = Integer.parseInt(String.valueOf(inputChar));

        return result;
    }

    public int getRangeInput(int min, int max) throws IOException {
        int result = 0;

        boolean wrongNumber = true;
        while (wrongNumber) {
            result = Integer.parseInt(String.valueOf(getInputFromConsole()));
            if (result <= max && result >= min){
                wrongNumber = false;
                break;
            }
            System.out.println("Number from 4 - 8");
        }
        return result;
    }

}
