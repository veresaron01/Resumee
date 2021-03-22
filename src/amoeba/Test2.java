package amoeba;

import java.io.IOException;

public class Test2 {

    public void test() throws IOException {

        TwoPlayerMode2 tpm = new TwoPlayerMode2(8, 8);

        GameLogic gl = new GameLogic();
        ViewGameField vgf = new ViewGameField();

        while (true) {
            //first
            ViewConsoleTexts.printFirstPlayerChooses();

            //System.out.println(tpm.fieldDimensionY);
            //System.out.println(tpm.fieldDimensionX);

            //tpm.fieldDimensionY = 8;
            //tpm.fieldDimensionX = 8;

            int y;
            int x;

            while(!tpm.checkValidity((y = Integer.parseInt((String.valueOf(gl.getStepXInput()))) - 1),(x = Integer.parseInt((String.valueOf(gl.getStepXInput()))) - 1))) {
                System.out.println("Choose empty coordinates.");
            }
            tpm.addExStep(y, x);

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(8, 8, wholeField);

            if (tpm.matcherX()) {
                System.out.println("The Winner is Player #1!!! (\"X\")");
            }


            //second
            ViewConsoleTexts.printSecondPlayerChooses();



            int y1;
            int x1;

            while(!tpm.checkValidity((y1 = Integer.parseInt((String.valueOf(gl.getStepXInput()))) - 1),(x1 = Integer.parseInt((String.valueOf(gl.getStepXInput()))) - 1))) {
                System.out.println("Choose empty coordinates.");
            }
            tpm.addOoStep(y1, x1);

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(8, 8, wholeField1);

            if (tpm.matcherO()) {
                System.out.println("The Winner is Player #2!!! (\"O\")");
            }

        }
    }

}
