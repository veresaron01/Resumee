package amoeba;

import java.io.IOException;

public class MainClass {

    public static void main(String[] args) throws IOException {

        //Test2 t = new Test2();
        //t.test();

        TwoPlayerMode2 tpm = new TwoPlayerMode2(8,8);
        int a = tpm.fieldDimensionY;
        int b = tpm.fieldDimensionX;

        System.out.println(a + " " + b);

/*
        TwoPlayerMode2 tpm2 = new TwoPlayerMode2(8,8);

        GameLogic gl = new GameLogic();


        tpm2.yCoordinates[0] = 2;
        tpm2.xCoordinates[0] = 3;
        tpm2.steps[0] = 'O';

        char[][] c = tpm2.getCurrentWholeField();
        //for (char[] d : c) {
        //    for (char e : d) {
        //        System.out.println(e);
        //    }
        //}

        new ViewGameField().printField(8,8,c);
*/








        //new GameLogic().initializeGame();


        /*int height = 4;
        int length = 8;

        int[][] f = new int[height][length];
        int k = 0;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                f[i][j] = 0;
            }
        }

        ViewGameField gf = new ViewGameField();
        gf.printField(height,length, f);
*/
    }

}
