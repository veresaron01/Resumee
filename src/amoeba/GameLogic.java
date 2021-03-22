package amoeba;

import java.io.IOException;

public class GameLogic {

    public void startGame () throws IOException {

        Controller controller = new Controller();
        View_ConsoleTexts vct = new View_ConsoleTexts();
        Model_TwoPlayerGame t = new Model_TwoPlayerGame();

        vct.printGameIntro();
        vct.printWrongFieldDimensionSizes();

        int yDim;
        int xDim;
        while ((yDim = Integer.parseInt(String.valueOf(controller.getInput()))) < 4 || (xDim = Integer.parseInt(String.valueOf(controller.getInput()))) < 4) {
            vct.printWrongFieldDimensionSizes();
        }

        t.test(yDim,xDim);

    }

}
