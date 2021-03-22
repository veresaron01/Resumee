/*package amoeba;

import java.io.IOException;

public class Test {

    TwoPlayerMode tpm = new TwoPlayerMode(8,8);

    private GameLogic gl = new GameLogic();
    private ViewGameField vgf = new ViewGameField();

    public void test () throws IOException {
        while (true) {
            //first
            ViewConsoleTexts.printFirstPlayerChooses();

            char c = gl.getStepXInput();
            char d = gl.getStepXInput();

            int y = Integer.parseInt((String.valueOf(c)));
            int x = Integer.parseInt((String.valueOf(d)));

            StepX sx = new StepX(y,x);
            tpm.addYCoordinate(y);
            tpm.addXCoordinate(x);
            tpm.addStep('X');

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(8,8,wholeField);


            //second
            ViewConsoleTexts.printSecondPlayerChooses();

            char c1 = gl.getStepXInput();
            char d1 = gl.getStepXInput();

            int y1 = Integer.parseInt((String.valueOf(c1)));
            int x1 = Integer.parseInt((String.valueOf(d1)));

            StepX sx1 = new StepX(y1,x1);
            tpm.addYCoordinate(y1);
            tpm.addXCoordinate(x1);
            tpm.addStep('O');

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(8,8,wholeField1);

        }
    }

}
*/