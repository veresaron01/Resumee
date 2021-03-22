package amoeba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwoPlayerMode2 {

    int fieldDimensionY = 8;
    int fieldDimensionX = 8;
    int fieldSize = fieldDimensionY * fieldDimensionX;

    int[] yCoordinates = new int[fieldSize];

    static int yCounter = 0;

    int[] xCoordinates = new int[fieldSize];

    static int xCounter = 0;

    char[] steps = new char[fieldSize];
    static int stepCounter = 0;

    //YCoordinatesComparator yComp = new YCoordinatesComparator();
    //XCoordinatesComparator xComp = new XCoordinatesComparator();
    //ChainedComparator cc1 = new ChainedComparator(yComp, xComp);


    List<Integer> exCoordinatesY = new ArrayList();
    List<Integer> exCoordinatesX = new ArrayList();
    List<Integer> ooCoordinatesY = new ArrayList();
    List<Integer> ooCoordinatesX = new ArrayList();
    List<Integer> allCoordinatesY = new ArrayList();
    List<Integer> allCoordinatesX = new ArrayList();
    List<String> allStepsInString = new ArrayList();

    public TwoPlayerMode2(int fieldDimensionY, int fieldDimensionX) {
        this.fieldDimensionY = fieldDimensionY;
        this.fieldDimensionX = fieldDimensionX;
    }

    public boolean checkValidity (int y, int x) {
        String val = y + "" + x;
        if (allStepsInString.contains(val)) {
            return false;
        }
        allStepsInString.add(val);
        return true;
    }

    public void addOoStep(int y, int x) {
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

    public void addExStep(int y, int x) {
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

    public char[][] getCurrentWholeField() {

        char[][] currentWholeField = new char[fieldDimensionY][fieldDimensionX];

        for (int i = 0; i < fieldDimensionY; i++) {
            for (int j = 0; j < fieldDimensionX; j++) {
                currentWholeField[i][j] = ' ';
            }
        }

        for (int i = 0; i < exCoordinatesY.size(); i++) {
            currentWholeField[exCoordinatesY.get(i)][exCoordinatesX.get(i)] = 'X';
        }

        for (int i = 0; i < ooCoordinatesY.size(); i++) {
            currentWholeField[ooCoordinatesY.get(i)][ooCoordinatesX.get(i)] = 'O';
        }

        return currentWholeField;
    }

    public static boolean continuity(int[] a) {
        boolean result = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1] - 1) {
                result = false;
            }
        }

        return result;
    }

    //talalatok megtalalasa (4 darab egy egyenes menten megszakitas nelkul, egy fajtabol(X))
    public boolean matcherX() {
        boolean result = false;

        //vizszintesen talalat
        List<Integer> list1 = new ArrayList();
        list1.addAll(exCoordinatesY);
        Collections.sort(list1);

        for (int i = 0; i < list1.size(); i++) {

            int continuityCounterY = 0;
            int[] forCheckConty = new int[4];

            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(i) == list1.get(j) && continuityCounterY < 4) {
                    forCheckConty[continuityCounterY] = exCoordinatesX.get(j);
                    continuityCounterY++;
                }

            }
            if (continuityCounterY == 4) {
                Arrays.sort(forCheckConty);
                return continuity(forCheckConty);
            }
        }

        //fuggolegesen talalat
        List<Integer> list2 = new ArrayList();
        list2.addAll(exCoordinatesX);
        Collections.sort(list2);

        for (int i = 0; i < list2.size(); i++) {

            int continuityCounterX = 0;
            int[] forCheckContx = new int[4];

            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(i) == list2.get(j) && continuityCounterX < 4) {
                    forCheckContx[continuityCounterX] = exCoordinatesY.get(j);
                    continuityCounterX++;
                }

            }
            if (continuityCounterX == 4) {
                Arrays.sort(forCheckContx);
                return continuity(forCheckContx);
            }
        }

        //atlosan talalat
        List<Integer> list3a = new ArrayList();
        list3a.addAll(exCoordinatesX);
        Collections.sort(list3a);

        List<Integer> list3b = new ArrayList();
        list3b.addAll(exCoordinatesY);
        Collections.sort(list3b);

        int continuityCounterX = 0;

        int[] forCheckContx = new int[3];

        for (int i = 0; i < list3a.size() - 1; i++) {
            forCheckContx[0] = list3a.get(i);
            if (list3a.get(i) == list3a.get(i + 1) - 1 && continuityCounterX < 3) {
                forCheckContx[continuityCounterX] = exCoordinatesX.get(i + 1);
                continuityCounterX++;
            }
        }

        int continuityCounterY1 = 0;
        int continuityCounterY2 = 0;
        int[] forCheckConty1 = new int[3];
        int[] forCheckConty2 = new int[3];

        for (int i = 0; i < list3b.size() - 1; i++) {
            forCheckConty1[0] = list3b.get(i);
            if (list3b.get(i) == list3b.get(i + 1) - 1 && continuityCounterY1 < 3) {
                forCheckConty1[continuityCounterY1] = exCoordinatesY.get(i + 1);
                continuityCounterY1++;
            }
            if (list3b.get(i) == list3b.get(i + 1) + 1 && continuityCounterY2 < 3) {
                forCheckConty2[continuityCounterY2] = exCoordinatesY.get(i + 1);
                continuityCounterY2++;
            }
        }

        if ((continuityCounterX == 3 && continuityCounterY1 == 3) || (continuityCounterX == 3 && continuityCounterY2 == 3)) {
            result = true;
        }

        return result;

    }

    //talalatok megtalalasa (4 darab egy egyenes menten megszakitas nelkul, egy fajtabol(O))
    public boolean matcherO() {
        boolean result = false;

        //vizszintesen talalat
        List<Integer> list1 = new ArrayList();
        list1.addAll(ooCoordinatesY);
        Collections.sort(list1);

        for (int i = 0; i < list1.size(); i++) {

            int continuityCounterY = 0;
            int[] forCheckConty = new int[4];

            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(i) == list1.get(j) && continuityCounterY < 4) {
                    forCheckConty[continuityCounterY] = ooCoordinatesX.get(j);
                    continuityCounterY++;
                }

            }
            if (continuityCounterY == 4) {
                Arrays.sort(forCheckConty);
                return continuity(forCheckConty);
            }
        }

        //fuggolegesen talalat
        List<Integer> list2 = new ArrayList();
        list2.addAll(ooCoordinatesX);
        Collections.sort(list2);

        for (int i = 0; i < list2.size(); i++) {

            int continuityCounterX = 0;
            int[] forCheckContx = new int[4];

            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(i) == list2.get(j) && continuityCounterX < 4) {
                    forCheckContx[continuityCounterX] = ooCoordinatesY.get(j);
                    continuityCounterX++;
                }

            }
            if (continuityCounterX == 4) {
                Arrays.sort(forCheckContx);
                return continuity(forCheckContx);
            }
        }

        //atlosan talalat
        int continuityCounterX = 0;

        int[] forCheckContx = new int[3];

        for (int i = 0; i < list2.size() - 1; i++) {
            forCheckContx[0] = list2.get(i);
            if (list2.get(i) == list2.get(i + 1) - 1 && continuityCounterX < 3) {
                forCheckContx[continuityCounterX] = ooCoordinatesX.get(i + 1);
                continuityCounterX++;
            }
        }

        int continuityCounterY1 = 0;
        int continuityCounterY2 = 0;
        int[] forCheckConty1 = new int[3];
        int[] forCheckConty2 = new int[3];

        for (int i = 0; i < list1.size() - 1; i++) {
            forCheckConty1[0] = list1.get(i);
            if (list1.get(i) == list1.get(i + 1) - 1 && continuityCounterY1 < 3) {
                forCheckConty1[continuityCounterY1] = ooCoordinatesY.get(i + 1);
                continuityCounterY1++;
            }
            if (list1.get(i) == list1.get(i + 1) + 1 && continuityCounterY2 < 3) {
                forCheckConty2[continuityCounterY2] = ooCoordinatesY.get(i + 1);
                continuityCounterY2++;
            }
        }

        if ((continuityCounterX == 3 && continuityCounterY1 == 3) || (continuityCounterX == 3 && continuityCounterY2 == 3)) {
            result = true;
        }

        return result;

    }

}

