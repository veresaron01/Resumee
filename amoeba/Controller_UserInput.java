package amoeba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller_UserInput {

    BufferedReader reader;

    public Controller_UserInput() {
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

    public char getStepInput(int reg) throws IOException {
        String regex = "[1-" + reg + "]";
        char result;
        System.out.println(regex);

        while (!String.valueOf((result = getInputFromConsole())).matches(regex)) {
            System.out.println("Number from 1 - " + reg);
        }

        return result;
    }

    public char getInput() throws IOException {
        char result;

        while (!String.valueOf((result = getInputFromConsole())).matches("[4-8]")) {
            System.out.println("Number from 4 - 8");
        }
        return result;
    }

}
