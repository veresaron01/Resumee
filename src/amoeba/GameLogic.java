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

        while (controller.getInputFromConsole() != '9') {
            new ViewConsoleTexts().printGameIntro();
        }

    }
    public void initializeMenu () {
        new ViewConsoleTexts().printMenu();

    }

    public char getStepXInput (String reg) throws IOException {
        String regex = "[1-" + reg + "]";
        char result = ' ';
        System.out.println(regex);

        //while (!String.valueOf((result = controller.getInputFromConsole(reader))).matches("[1-8]")) {
        //    System.out.println("Number from 1 - 8");
        //}
        while (!String.valueOf((result = controller.getInputFromConsole())).matches(regex)) {
            System.out.println("Number from 1 - " + reg);
        }

        return result;
    }

    public char getStepOInput (String reg) throws IOException {
        String regex = "[1-" + reg + "]";
        System.out.println(regex);
        char result = ' ';

        //while (!String.valueOf((result = controller.getInputFromConsole(reader))).matches("[1-8]")) {
        //    System.out.println("Number from 1 - 8");
        //}
        while (!String.valueOf((result = controller.getInputFromConsole())).matches(regex)) {
            System.out.println("Number from 1 - " + reg);
        }

        return result;
    }



    public void clearEverythingForNewGame () {}

    //public void saveGame () {}

    //public void loadGame () {}

}
