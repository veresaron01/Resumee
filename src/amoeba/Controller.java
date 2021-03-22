package amoeba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public char getInputFromConsole(BufferedReader reader) throws IOException {
        char c;
        String s;
        while (!(s = reader.readLine()).matches(".")) {}
        c = s.charAt(0);
        return c;
    }

}
