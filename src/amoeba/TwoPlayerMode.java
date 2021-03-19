package amoeba;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwoPlayerMode {

    private static int fieldDimensionY;
    private static int fieldDimensionX;
    private static final int fieldSize = fieldDimensionY * fieldDimensionX;

    static int[] yCoordinates = new int[fieldDimensionY];
    private static int yCounter = 0;
    static int[] xCoordinates = new int[fieldDimensionX];
    private static int xCounter = 0;

    static char[] steps = new char[fieldSize];
    private static int stepCounter = 0;

    YCoordinatesComparator YComp = new YCoordinatesComparator();
    XCoordinatesComparator XComp = new XCoordinatesComparator();
    ChainedComparator cc1 = new ChainedComparator(YComp, XComp);

    public TwoPlayerMode(int fieldDimensionY, int fieldDimensionX) {
        this.fieldDimensionY = fieldDimensionY;
        this.fieldDimensionX = fieldDimensionX;
    }

    public void addYCoordinate (int y) {
        yCoordinates[yCounter] = y;
        yCounter++;
    }

    public void addXCoordinate (int x) {
        xCoordinates[xCounter] = x;
        xCounter++;
    }

    public void addStep (char c) {
        steps[stepCounter] = c;
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

    //talalatok megtalalasa (4 darab egy egyenes menten megsyakitas nelkul, egy fajtabol)
    public boolean matcher (List stepList) { //return value legyen majd Player
        List<AbstractStep> list = stepList;
        boolean result = false;

        //fuggolegesen talalat
        Collections.sort(list, YComp);

        for (int i = 0; i < list.size(); i++) {

            int continuityCounterY = 0;
            int[] forCheckCont = new int[4];

            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).getYCoordinate() == list.get(j).getYCoordinate() && continuityCounterY < 4) {
                    forCheckCont[continuityCounterY] = list.get(j).getXCoordinate();
                    continuityCounterY++;
                }

            }
            if (continuityCounterY == 4) {
                Arrays.sort(forCheckCont);
                return continuity(forCheckCont);
            }
        }

        //vizszintesen talalat
        Collections.sort(list, XComp);

        for (int i = 0; i < list.size(); i++) {

            int continuityCounterX = 0;
            int[] forCheckCont = new int[4];

            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).getXCoordinate() == list.get(j).getXCoordinate() && continuityCounterX < 4) {
                    forCheckCont[continuityCounterX] = list.get(j).getYCoordinate();
                    continuityCounterX++;
                }

            }
            if (continuityCounterX == 4) {
                Arrays.sort(forCheckCont);
                return continuity(forCheckCont);

            }
        }

        //atlos talalat bal felso sarokbol jobb alulra

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

