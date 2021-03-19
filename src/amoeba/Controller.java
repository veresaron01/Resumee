package amoeba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public char getInputFromConsole(BufferedReader reader) throws IOException {
        char c = reader.readLine().charAt(0);

        return c;
    }

}
