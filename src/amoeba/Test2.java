package amoeba;

import java.io.IOException;

public class Test2 {



    public void test () throws IOException {

        TwoPlayerMode2 tpm = new TwoPlayerMode2(8,8);

        GameLogic gl = new GameLogic();
        ViewGameField vgf = new ViewGameField();

        while (true) {
            //first
            ViewConsoleTexts.printFirstPlayerChooses();

            System.out.println(tpm.fieldDimensionY);
            System.out.println(tpm.fieldDimensionX);

            //tpm.fieldDimensionY = 8;
            //tpm.fieldDimensionX = 8;

            char c = gl.getStepXInput();
            char d = gl.getStepXInput();

            int y = Integer.parseInt((String.valueOf(c))) - 1;
            int x = Integer.parseInt((String.valueOf(d))) - 1;

            tpm.addExStep(y,x);

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(8,8,wholeField);

            //if (tpm.matcherX()) {System.out.println("The Winner is Player #1!!! (\"X\")");}


            //second
            ViewConsoleTexts.printSecondPlayerChooses();

            char c1 = gl.getStepOInput();
            char d1 = gl.getStepOInput();

            int y1 = Integer.parseInt((String.valueOf(c1))) - 1;
            int x1 = Integer.parseInt((String.valueOf(d1))) - 1;

            tpm.addOoStep(y1,x1);

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(8,8,wholeField1);

            //if (tpm.matcher()) {System.out.println("The Winner is Player #2!!! (\"O\")");}

        }
    }

}
