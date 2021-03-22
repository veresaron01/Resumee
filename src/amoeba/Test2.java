package amoeba;

import java.io.IOException;

public class Test2 {

    public void test(int yDim, int xDim) throws IOException {



        TwoPlayerMode2 tpm = new TwoPlayerMode2(yDim, xDim);
        //tpm.fieldDimensionY = 7;
        //tpm.fieldDimensionX = 7;

        String forRegexY = String.valueOf(tpm.fieldDimensionY);
        String forRegexX = String.valueOf(tpm.fieldDimensionX);
        int fieldDimensionY = tpm.fieldDimensionY;
        int fieldDimensionX = tpm.fieldDimensionX;

        GameLogic gl = new GameLogic();
        ViewGameField vgf = new ViewGameField();

        boolean winnerFound = false;



        vgf.printField(fieldDimensionY, fieldDimensionX, tpm.getCurrentWholeField());

        while (!winnerFound) {
            //first player
            ViewConsoleTexts.printFirstPlayerChooses();

            //kivenni innen
            System.out.println(tpm.fieldDimensionY);
            System.out.println(tpm.fieldDimensionX);



            int y;
            int x;

            while(!tpm.checkValidity((y = Integer.parseInt((String.valueOf(gl.getStepXInput(forRegexY)))) - 1),(x = Integer.parseInt((String.valueOf(gl.getStepXInput(forRegexX)))) - 1))) {
                ViewConsoleTexts.printWrongCoordinates();
            }
            tpm.addExStep(y, x);

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField);

            if (tpm.matcherX()) {
                ViewConsoleTexts.printWinner1();
                winnerFound =true;
                continue;
            }


            //second player
            ViewConsoleTexts.printSecondPlayerChooses();

            int y1;
            int x1;

            while(!tpm.checkValidity((y1 = Integer.parseInt((String.valueOf(gl.getStepXInput(forRegexY)))) - 1),(x1 = Integer.parseInt((String.valueOf(gl.getStepXInput(forRegexX)))) - 1))) {
                ViewConsoleTexts.printWrongCoordinates();
            }
            tpm.addOoStep(y1, x1);

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField1);

            if (tpm.matcherO()) {
                ViewConsoleTexts.printWinner2();
                winnerFound =true;
            }

        }
    }

}
