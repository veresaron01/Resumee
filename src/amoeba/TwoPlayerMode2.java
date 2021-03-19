package amoeba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwoPlayerMode2 {

    int fieldDimensionY = 8;
    int fieldDimensionX = 8;
    int fieldSize = fieldDimensionY * fieldDimensionX;

    int[] yCoordinates = new int[fieldDimensionY];

    static int yCounter = 0;

    int[] xCoordinates = new int[fieldDimensionX];

    static int xCounter = 0;

    char[] steps = new char[fieldSize];
    static int stepCounter = 0;

    YCoordinatesComparator yComp = new YCoordinatesComparator();
    XCoordinatesComparator xComp = new XCoordinatesComparator();
    ChainedComparator cc1 = new ChainedComparator(yComp, xComp);



    List<Integer> exCoordinatesY = new ArrayList();
    List<Integer> exCoordinatesX = new ArrayList();
    List<Integer> ooCoordinatesY = new ArrayList();
    List<Integer> ooCoordinatesX = new ArrayList();
    List<Integer> allCoordinatesY = new ArrayList();
    List<Integer> allCoordinatesX = new ArrayList();


    public TwoPlayerMode2(int fieldDimensionY, int fieldDimensionX) {
        this.fieldDimensionY = fieldDimensionY;
        this.fieldDimensionX = fieldDimensionX;
    }

    public void addOoStep (int y, int x) {
        ooCoordinatesY.add(y);
        ooCoordinatesX.add(x);

        yCoordinates[yCounter] = y;
        yCounter++;

        xCoordinates[xCounter] = x;
        xCounter++;

        allCoordinatesY.add(y);
        allCoordinatesX.add(x);

        steps[stepCounter] = 'O';
        stepCounter++;
    }

    public void addExStep (int y, int x) {
        exCoordinatesY.add(y);
        exCoordinatesX.add(x);

        yCoordinates[yCounter] = y;
        yCounter++;

        xCoordinates[xCounter] = x;
        xCounter++;

        allCoordinatesY.add(y);
        allCoordinatesX.add(x);

        steps[stepCounter] = 'X';
        stepCounter++;
    }

    public char[][] getCurrentWholeField () {
        char[][] currentWholeField = new char[fieldDimensionY][fieldDimensionX];
        int temp = 0;

        for (int i = 0; i < fieldDimensionY; i++) {
            for (int j = 0; j < fieldDimensionX; j++) {
                if (i == yCoordinates[temp] && j == xCoordinates[temp]) {
                    currentWholeField[i][j] = steps[temp];
                    temp++;
                } else {
                    currentWholeField[i][j] = ' ';
                }
            }
        }

        return currentWholeField;
    }

    public static boolean continuity (int[] a) {
        boolean result = true;
        for (int i = 0; i < a.length -1; i++) {
            if (a[i] != a[i+1]-1) {
                result = false;
            }
        }

        return result;
    }

    //talalatok megtalalasa (4 darab egy egyenes menten megszakitas nelkul, egy fajtabol)
    public boolean matcherX () { //return value legyen majd Player
        boolean result = false;


        //fuggolegesen talalat
        List<Integer> list1 = exCoordinatesY;
        Collections.sort(list1);

        for (int i = 0; i < list1.size(); i++) {

            int continuityCounterY = 0;
            int[] forCheckCont = new int[4];

            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(i) == list1.get(j) && continuityCounterY < 4){
                    forCheckCont[continuityCounterY] = exCoordinatesX.get(j);
                    continuityCounterY++;
                }

            }
            if (continuityCounterY == 4) {
                Arrays.sort(forCheckCont);
                return continuity(forCheckCont);
            }
        }

        //vizszintesen talalat
        List<Integer> list2 = exCoordinatesX;
        Collections.sort(list2);

        for (int i = 0; i < list2.size(); i++) {

            int continuityCounterX = 0;
            int[] forCheckCont = new int[4];

            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(i) == list2.get(j) && continuityCounterX < 4){
                    forCheckCont[continuityCounterX] = exCoordinatesY.get(j);
                    continuityCounterX++;
                }

            }
            if (continuityCounterX == 4) {
                Arrays.sort(forCheckCont);
                return continuity(forCheckCont);
            }
        }

        //atlos talalat bal felso sarokbol jobb alulra
/*
        Collections.sort(list, cc1);

        for (int i = 0; i < list.size(); i++) {

            int continuityCounterX = 1;
            int continuityCounterY = 1;
            int[] forCheckContX = new int[4];
            forCheckContX[0] = list.get(i).getYCoordinate();
            int[] forCheckContY = new int[4];
            forCheckContY[0] = list.get(i).getXCoordinate();

            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).getXCoordinate() == list.get(j).getXCoordinate() - 1 && list.get(i).getYCoordinate() == list.get(j).getYCoordinate() - 1 && continuityCounterX < 4 && continuityCounterY < 4) {
                    forCheckContX[continuityCounterX +1] = list.get(j).getYCoordinate();
                    continuityCounterX++;
                    forCheckContY[continuityCounterY +1] = list.get(j).getXCoordinate();
                    continuityCounterY++;
                }

            }
            if (continuityCounterX == 4 && continuityCounterY == 4) {
                Arrays.sort(forCheckContX);
                Arrays.sort(forCheckContY);
                return continuity(forCheckContX) && continuity(forCheckContY);

            }
        }

        //atlos talalat jobb felso sarokbol bal alulra??

        for (int i = 0; i < list.size(); i++) {

            int continuityCounterX = 1;
            int continuityCounterY = 1;
            int[] forCheckContX = new int[4];
            forCheckContX[0] = list.get(i).getYCoordinate();
            int[] forCheckContY = new int[4];
            forCheckContY[0] = list.get(i).getXCoordinate();

            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).getXCoordinate() == list.get(j).getXCoordinate() - 1 && list.get(i).getYCoordinate() == list.get(j).getYCoordinate() + 1 && continuityCounterX < 4 && continuityCounterY < 4) {
                    forCheckContX[continuityCounterX +1] = list.get(j).getYCoordinate();
                    continuityCounterX++;
                    forCheckContY[continuityCounterY +1] = list.get(j).getXCoordinate();
                    continuityCounterY++;
                }

            }
            if (continuityCounterX == 4 && continuityCounterY == 4) {
                Arrays.sort(forCheckContX);
                Arrays.sort(forCheckContY);
                return continuity(forCheckContX) && continuity(forCheckContY);

            }
        }
*/
        return result;
    }


    //eltavolitani
/*public Player matcherProba () {
    Player winner = null;

    int playerOnesSteps = 0; //+2 in y- and x- Coordinates
    int playerTwosSteps = 1; //+2

    //check for pl1 winning?
    for (int k = 0; k < stepCounter; k++){
        //yCoordinates[k] ==
    }

    for (int i = 0; i < fieldDimensionY; i++) {
        for (int j = 0; j < fieldDimensionY; j++) {

        }
    }

    return winner;
}*/

}

