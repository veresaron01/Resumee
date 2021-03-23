package amoeba;

import java.util.*;

public class Model_GameUtility {

    int fieldDimensionY = 0;
    int fieldDimensionX = 0;
    int fieldSize = 0;

    int[] yCoordinates;// = new int[fieldSize];

    static int yCounter = 0;

    int[] xCoordinates;// = new int[fieldSize];

    static int xCounter = 0;

    char[] steps;// = new char[fieldSize];
    static int stepCounter = 0;

    List<Integer> exCoordinatesY = new ArrayList();
    List<Integer> exCoordinatesX = new ArrayList();
    List<Integer> ooCoordinatesY = new ArrayList();
    List<Integer> ooCoordinatesX = new ArrayList();
    List<Integer> allCoordinatesY = new ArrayList();
    List<Integer> allCoordinatesX = new ArrayList();
    List<String> allStepsInString = new ArrayList();

    public Model_GameUtility(int fieldDimensionY, int fieldDimensionX) {
        this.fieldDimensionY = fieldDimensionY;
        this.fieldDimensionX = fieldDimensionX;
        this.fieldSize = fieldDimensionY * fieldDimensionX;
        yCoordinates = new int[fieldDimensionY * fieldDimensionX];
        xCoordinates = new int[fieldDimensionY * fieldDimensionX];
        steps = new char[fieldDimensionY * fieldDimensionX];
    }

    public boolean checkDrawGame() {
        if (steps.length == fieldSize) {
            return true;
        }
        return false;
    }

    public boolean checkValidity(int y, int x) {
        String val = y + "" + x;
        if (allStepsInString.contains(val) || y > fieldDimensionY || x > fieldDimensionX) {
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

    public static boolean continuity(List<Integer> a) {
        boolean result = true;
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) != a.get(i + 1) - 1) {
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

        //atlosan talalat balfentrol, jobb le.
        List<String> exStepsStr = new ArrayList();
        for (int i = 0; i < exCoordinatesY.size(); i++) {
            String exAllStr = (exCoordinatesY.get(i) + 1) + " " + (exCoordinatesX.get(i) + 1);
            exStepsStr.add(exAllStr);
        }

        for (int z = 1; z < fieldDimensionX - 2; z++) {
            for (int i = 1; i < fieldDimensionY - 2; i++) {

                List<String> allStepsInTheDiagon = new ArrayList();

                String element1 = (z) + " " + (i);
                String element2 = (z + 1) + " " + (i + 1);
                String element3 = (z + 2) + " " + (i + 2);
                String element4 = (z + 3) + " " + (i + 3);

                allStepsInTheDiagon.add(element1);
                allStepsInTheDiagon.add(element2);
                allStepsInTheDiagon.add(element3);
                allStepsInTheDiagon.add(element4);

                if (exStepsStr.containsAll(allStepsInTheDiagon)) {
                    return true;
                }
            }
        }

        //bal lentrol, jobb fel
        for (int z = 1; z < fieldDimensionX - 2; z++) {
            for (int i = fieldDimensionY; i > 3; i--) {

                List<String> allStepsInTheDiagon = new ArrayList();

                String element1 = (z) + " " + (i);
                String element2 = (z + 1) + " " + (i - 1);
                String element3 = (z + 2) + " " + (i - 2);
                String element4 = (z + 3) + " " + (i - 3);

                allStepsInTheDiagon.add(element1);
                allStepsInTheDiagon.add(element2);
                allStepsInTheDiagon.add(element3);
                allStepsInTheDiagon.add(element4);

                if (exStepsStr.containsAll(allStepsInTheDiagon)) {
                    return true;
                }
            }
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

        //atlosan talalat balfentrol, jobb le.
        List<String> exStepsStr = new ArrayList();
        for (int i = 0; i < ooCoordinatesY.size(); i++) {
            String exAllStr = (ooCoordinatesY.get(i) + 1) + " " + (ooCoordinatesX.get(i) + 1);
            exStepsStr.add(exAllStr);
        }

        for (int z = 1; z < fieldDimensionX - 2; z++) {
            for (int i = 1; i < fieldDimensionY - 2; i++) {

                List<String> allStepsInTheDiagon = new ArrayList();

                String element1 = (z) + " " + (i);
                String element2 = (z + 1) + " " + (i + 1);
                String element3 = (z + 2) + " " + (i + 2);
                String element4 = (z + 3) + " " + (i + 3);

                allStepsInTheDiagon.add(element1);
                allStepsInTheDiagon.add(element2);
                allStepsInTheDiagon.add(element3);
                allStepsInTheDiagon.add(element4);

                if (exStepsStr.containsAll(allStepsInTheDiagon)) {
                    return true;
                }
            }
        }

        //bal lentrol, jobb fel
        for (int z = 1; z < fieldDimensionX - 2; z++) {
            for (int i = fieldDimensionY; i > 3; i--) {

                List<String> allStepsInTheDiagon = new ArrayList();

                String element1 = (z) + " " + (i);
                String element2 = (z + 1) + " " + (i - 1);
                String element3 = (z + 2) + " " + (i - 2);
                String element4 = (z + 3) + " " + (i - 3);

                allStepsInTheDiagon.add(element1);
                allStepsInTheDiagon.add(element2);
                allStepsInTheDiagon.add(element3);
                allStepsInTheDiagon.add(element4);

                if (exStepsStr.containsAll(allStepsInTheDiagon)) {
                    return true;
                }
            }
        }
        return result;
    }
}

