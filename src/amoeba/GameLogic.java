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

        return controller.getInputFromConsole(reader);
    }

    public char getStepOInput () throws IOException {

        return controller.getInputFromConsole(reader);
    }



    public void clearEverythingForNewGame () {}

    //public void saveGame () {}

    //public void loadGame () {}

}
