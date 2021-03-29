package amoeba;

import java.io.IOException;

public class Model_GameLogic {

    public void startGame() throws IOException {

        Controller_UserInput cus = new Controller_UserInput();
        View_ConsoleTexts vct = new View_ConsoleTexts();

        vct.printGameIntro();
        vct.printWrongFieldDimensionSizes();

        int yDim;
        int xDim;

        yDim = Integer.parseInt(String.valueOf(cus.getInput()));
        xDim = Integer.parseInt(String.valueOf(cus.getInput()));

        Model_TwoPlayerGame twoPlayerGame = new Model_TwoPlayerGame(yDim, xDim);

        twoPlayerGame.initializeTwoPlayerGame(yDim, xDim);

    }

}
