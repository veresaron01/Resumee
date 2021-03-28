package amoeba;

import java.io.IOException;

public class Model_TwoPlayerGame {

    public void test(int yDim, int xDim) throws IOException {

        Model_GameUtility tpm = new Model_GameUtility(yDim, xDim);

        String forRegexY = String.valueOf(tpm.fieldDimensionY);
        String forRegexX = String.valueOf(tpm.fieldDimensionX);
        int fieldDimensionY = tpm.fieldDimensionY;
        int fieldDimensionX = tpm.fieldDimensionX;

        Controller_UserInput cl = new Controller_UserInput();
        View_GameField vgf = new View_GameField();

        boolean winnerFound = false;

        vgf.printField(fieldDimensionY, fieldDimensionX, tpm.getCurrentWholeField());

        while (!winnerFound) {
            //first player
            View_ConsoleTexts.printFirstPlayerChooses();

            int y;
            int x;

            while (!tpm.checkValidity((y = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexY)))) - 1), (x = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexX)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addExStep(y, x);

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField);

            if (tpm.matcher(1)) {
                View_ConsoleTexts.printWinner1();
                winnerFound = true;
                continue;
            }
            if (!tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

            //second player
            View_ConsoleTexts.printSecondPlayerChooses();

            int y1;
            int x1;

            while (!tpm.checkValidity((y1 = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexY)))) - 1), (x1 = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexX)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addOoStep(y1, x1);

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField1);

            if (tpm.matcher(2)) {
                View_ConsoleTexts.printWinner2();
                winnerFound = true;
                continue;
            }
            if (!tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

        }
    }

}



/*package amoeba;

import java.io.IOException;

public class Model_TwoPlayerGame {

    public void test(int yDim, int xDim) throws IOException {

        Model_GameUtility tpm = new Model_GameUtility(yDim, xDim);

        String forRegexY = String.valueOf(tpm.fieldDimensionY);
        String forRegexX = String.valueOf(tpm.fieldDimensionX);
        int fieldDimensionY = tpm.fieldDimensionY;
        int fieldDimensionX = tpm.fieldDimensionX;

        Controller_UserInput cl = new Controller_UserInput();
        View_GameField vgf = new View_GameField();

        boolean winnerFound = false;

        vgf.printField(fieldDimensionY, fieldDimensionX, tpm.getCurrentWholeField());

        while (!winnerFound) {
            //first player
            View_ConsoleTexts.printFirstPlayerChooses();

            int y;
            int x;

            while (!tpm.checkValidity((y = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexY)))) - 1), (x = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexX)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addExStep(y, x);

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField);

            //if (tpm.matcher(1)) {
                if (tpm.matcherX()) {
                View_ConsoleTexts.printWinner1();
                winnerFound = true;
                continue;
            }
            if (!tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

            //second player
            View_ConsoleTexts.printSecondPlayerChooses();

            int y1;
            int x1;

            while (!tpm.checkValidity((y1 = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexY)))) - 1), (x1 = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexX)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addOoStep(y1, x1);

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField1);

            //if (tpm.matcher(2)) {
                if (tpm.matcherO()) {
                View_ConsoleTexts.printWinner2();
                winnerFound = true;
                continue;
            }
            if (!tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

        }
    }

}
*/