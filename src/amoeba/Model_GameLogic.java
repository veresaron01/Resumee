package amoeba;

import java.io.IOException;

public class Model_GameLogic {

    public void startGame() throws IOException {

        Controller_UserInput controller = new Controller_UserInput();
        View_ConsoleTexts vct = new View_ConsoleTexts();

        vct.printGameIntro();
        vct.printWrongFieldDimensionSizes();

        int yDim;
        int xDim;

        while ((yDim = Integer.parseInt(String.valueOf(controller.getInput()))) < 4 || (xDim = Integer.parseInt(String.valueOf(controller.getInput()))) < 4) {
            vct.printWrongFieldDimensionSizes();
        }

        Model_TwoPlayerGame t = new Model_TwoPlayerGame(yDim, xDim);

        t.initializeTwoPlayerGame(yDim, xDim);

    }

}
