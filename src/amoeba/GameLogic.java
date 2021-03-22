package amoeba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameLogic {

    Controller controller = new Controller();

    //Controllerbe atvinni
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void initializeGame () throws IOException {
        new ViewConsoleTexts().printGameIntro();

        while (controller.getInputFromConsole(reader) != '9') {
            new ViewConsoleTexts().printGameIntro();
        }

    }
    public void initializeMenu () {
        new ViewConsoleTexts().printMenu();

    }

    public char getStepXInput () throws IOException {
        char result = ' ';

        while (!String.valueOf((result = controller.getInputFromConsole(reader))).matches("[1-8]")) {
            System.out.println("Number from 1 - 8");
        }

        return result;
    }

    public char getStepOInput () throws IOException {
        char result = ' ';

        while (!String.valueOf((result = controller.getInputFromConsole(reader))).matches("[1-8]")) {
            System.out.println("Number from 1 - 8");
        }

        return result;
    }



    public void clearEverythingForNewGame () {}

    //public void saveGame () {}

    //public void loadGame () {}

}
