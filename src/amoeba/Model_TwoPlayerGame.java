package amoeba;

import java.io.IOException;

public class Model_TwoPlayerGame {

    private Model_GameUtility tpm;
    private Controller_UserInput cl;
    private View_GameField vgf;
    private int yDim;
    private int xDim;
    private boolean winnerFound;

    public Model_TwoPlayerGame (int yDim, int xDim) {
        tpm = new Model_GameUtility(yDim, xDim);
        cl = new Controller_UserInput();
        vgf = new View_GameField();
        this.yDim = yDim;
        this.xDim = xDim;
    }

    private void takePlayerStep(int XO) throws IOException {

        View_ConsoleTexts.printWhichPlayerChooses(XO);

        int y;
        int x;

        while (tpm.checkValidity((y = Integer.parseInt((String.valueOf(cl.getStepInput(yDim)))) - 1), (x = Integer.parseInt((String.valueOf(cl.getStepInput(xDim)))) - 1))) {
            View_ConsoleTexts.printWrongCoordinates();
        }
        if(XO == 1) {
            tpm.addExStep(y, x);
        } else {
            tpm.addOoStep(y, x);
        }

        char[][] wholeField = tpm.getCurrentWholeField();

        vgf.printField(yDim, xDim, wholeField);

    }

    public void initializeTwoPlayerGame(int yDim, int xDim) throws IOException {

        vgf.printField(yDim, xDim, tpm.getCurrentWholeField());

        while (!winnerFound) {
            //first player

            takePlayerStep(1);

            if (tpm.matcher(1)) {
                View_ConsoleTexts.printWinner(1);
                winnerFound = true;
                continue;
            }
            if (tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

            //second player

            takePlayerStep(2);

            if (tpm.matcher(2)) {
                View_ConsoleTexts.printWinner(2);
                winnerFound = true;
                continue;
            }
            if (tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

        }
    }
}