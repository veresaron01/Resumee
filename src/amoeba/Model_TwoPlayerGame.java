package amoeba;

import java.io.IOException;

public class Model_TwoPlayerGame {

    Model_GameUtility tpm;
    Controller_UserInput cl;
    View_GameField vgf;
    int yDim;
    int xDim;
    boolean winnerFound;

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
        tpm.addExStep(y, x);

        char[][] wholeField = tpm.getCurrentWholeField();

        vgf.printField(yDim, xDim, wholeField);

        if (tpm.matcher(XO)) {
            View_ConsoleTexts.printWinner(XO);
            winnerFound = true;
            continue;
        }
        if (tpm.checkDrawGame()) {
            View_ConsoleTexts.printDraw();
            winnerFound = true;
            continue;
        }

    }

    public void initializeTwoPlayerGame(int yDim, int xDim) throws IOException {

        vgf.printField(yDim, xDim, tpm.getCurrentWholeField());

        while (!winnerFound) {
            //first player

            takePlayerStep(1);

            if (tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

            //second player

            int y1;
            int x1;

            takePlayerStep(2);

            if (tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

        }
    }

    /*public void initializeTwoPlayerGame(int yDim, int xDim) throws IOException {

        Model_GameUtility tpm = new Model_GameUtility(yDim, xDim);
        Controller_UserInput cl = new Controller_UserInput();
        View_GameField vgf = new View_GameField();

        boolean winnerFound = false;

        vgf.printField(yDim, xDim, tpm.getCurrentWholeField());

        while (!winnerFound) {
            //first player
            View_ConsoleTexts.printWhichPlayerChooses(1);

            int y;
            int x;

            while (tpm.checkValidity((y = Integer.parseInt((String.valueOf(cl.getStepInput(yDim)))) - 1), (x = Integer.parseInt((String.valueOf(cl.getStepInput(xDim)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addExStep(y, x);

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(yDim, xDim, wholeField);

            if (tpm.matcher(1)) {
                View_ConsoleTexts.printWinner1();
                winnerFound = true;
                continue;
            }
            if (tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

            //second player
            View_ConsoleTexts.printWhichPlayerChooses(2);

            int y1;
            int x1;

            while (tpm.checkValidity((y1 = Integer.parseInt((String.valueOf(cl.getStepInput(yDim)))) - 1), (x1 = Integer.parseInt((String.valueOf(cl.getStepInput(xDim)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addOoStep(y1, x1);

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(yDim, xDim, wholeField1);

            if (tpm.matcher(2)) {
                View_ConsoleTexts.printWinner2();
                winnerFound = true;
                continue;
            }
            if (tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

        }
    }*/

}