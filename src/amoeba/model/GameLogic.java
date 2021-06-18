package amoeba.model;

import amoeba.controller.UserInput;
import amoeba.view.ConsoleTexts;

import java.io.IOException;

public class GameLogic {

    public void startGame() throws IOException {

        UserInput userInput = new UserInput();
        ConsoleTexts consoleTexts = new ConsoleTexts();
        GameInitializer gameInitializer = null;

        consoleTexts.printGameIntro();

        consoleTexts.printChooseNumberOfPlayers();
        int numberOfPlayers = userInput.getRangeInput(1,2);

        consoleTexts.printChooseWhoStartsPlayerOrMachine();
        int whoStarts = userInput.getRangeInput(1,2);

        consoleTexts.printWrongFieldDimensionSizes();
        int yDim = userInput.getRangeInput(4,8);
        int xDim = userInput.getRangeInput(4,8);

        gameInitializer = new GameInitializer(yDim,xDim,numberOfPlayers, whoStarts);
        gameInitializer.initializeGame();



    }

}
