package amoeba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    BufferedReader reader;

    public Controller () {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public char getInputFromConsole() throws IOException {
        char c;
        String s;
        while (!(s = reader.readLine()).matches(".")) {}
        c = s.charAt(0);
        return c;
    }

}
