package amoeba;

import java.util.*;

public class Model_GameUtility {

    private int fieldDimensionY;
    private int fieldDimensionX;
    private int fieldSize;
    private int stepCounter = 0;
    private char[][] currentWholeField;

    private List<Integer> exCoordinatesY = new ArrayList();
    private List<Integer> exCoordinatesX = new ArrayList();
    private List<Integer> ooCoordinatesY = new ArrayList();
    private List<Integer> ooCoordinatesX = new ArrayList();
    private List<String> allStepsInString = new ArrayList();

    private List<List<String>> allMatchesInTheDiagons = new ArrayList();

    public Model_GameUtility(int fieldDimensionY, int fieldDimensionX) {
        this.fieldDimensionY = fieldDimensionY;
        this.fieldDimensionX = fieldDimensionX;
        this.fieldSize = fieldDimensionY * fieldDimensionX;

        this.currentWholeField = new char[fieldDimensionY][fieldDimensionX];
        for (int i = 0; i < fieldDimensionY; i++) {
            for (int j = 0; j < fieldDimensionX; j++) {
                currentWholeField[i][j] = ' ';
            }
        }

        //atlosan lehetseges talalatok bal fentrol, jobb le.
        for (int z = 1; z < fieldDimensionY - 2; z++) {
            for (int i = 1; i < fieldDimensionX - 2; i++) {

                List<String> oneMatchOnADiagon = List.of(
                        String.format("%s %s", z, i),
                        String.format("%s %s", (z + 1), (i + 1)),
                        String.format("%s %s", (z + 2), (i + 2)),
                        String.format("%s %s", (z + 3), (i + 3)));
                allMatchesInTheDiagons.add(oneMatchOnADiagon);
            }
        }

        //atlosan lehetseges talalatok, bal lentrol, jobb fel
        for (int z = 1; z < fieldDimensionY - 2; z++) {
            for (int i = fieldDimensionX; i > 3; i--) {

                List<String> oneStepInADiagon = List.of(
                        String.format("%s %s", z, i),
                        String.format("%s %s", (z + 1), (i - 1)),
                        String.format("%s %s", (z + 2), (i - 2)),
                        String.format("%s %s", (z + 3), (i - 3)));
                allMatchesInTheDiagons.add(oneStepInADiagon);
            }
        }


    }

    public int getFieldDimensionY() {
        return fieldDimensionY;
    }

    public int getFieldDimensionX() {
        return fieldDimensionX;
    }

    public boolean checkDrawGame() {
        return stepCounter == fieldSize;
    }

    public boolean checkValidity(int y, int x) {
        String val = String.format("%s%s", y, x);
        if (allStepsInString.contains(val) || y > fieldDimensionY || x > fieldDimensionX) {
            return true;
        }
        allStepsInString.add(val);
        return false;
    }

    public void addOoStep(int y, int x) {
        ooCoordinatesY.add(y);
        ooCoordinatesX.add(x);

        stepCounter++;
    }

    public void addExStep(int y, int x) {
        exCoordinatesY.add(y);
        exCoordinatesX.add(x);

        stepCounter++;
    }

    public char[][] getCurrentWholeField() {

        for (int i = 0; i < exCoordinatesY.size(); i++) {
            currentWholeField[exCoordinatesY.get(i)][exCoordinatesX.get(i)] = 'X';
        }

        for (int i = 0; i < ooCoordinatesY.size(); i++) {
            currentWholeField[ooCoordinatesY.get(i)][ooCoordinatesX.get(i)] = 'O';
        }

        return currentWholeField;
    }

    public static boolean continuity(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1] - 1) {
                return false;
            }
        }
        return true;
    }

    //talalatok megtalalasa (4 darab egy egyenes menten megszakitas nelkul, egy fajtabol (X=1 || O=2)
    public boolean matcher(int XO) {
        List<Integer> YCoordinatesForXOrOSteps = new ArrayList();
        List<Integer> XCoordinatesForXOrOSteps = new ArrayList();

        if (XO == 1) {
            YCoordinatesForXOrOSteps = exCoordinatesY;
            XCoordinatesForXOrOSteps = exCoordinatesX;
        } else {
            YCoordinatesForXOrOSteps = ooCoordinatesY;
            XCoordinatesForXOrOSteps = ooCoordinatesX;
        }

        //vizszintesen talalat
        List<Integer> list1 = new ArrayList(YCoordinatesForXOrOSteps);
        Collections.sort(list1);

        for (int i = 0; i < list1.size(); i++) {

            int continuityCounterY = 0;
            int[] forCheckConty = new int[4];

            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(i).equals(list1.get(j)) && continuityCounterY < 4) {
                    forCheckConty[continuityCounterY] = XCoordinatesForXOrOSteps.get(j);
                    continuityCounterY++;
                }

            }
            if (continuityCounterY == 4) {
                Arrays.sort(forCheckConty);
                return continuity(forCheckConty);
            }
        }

        //fuggolegesen talalat
        List<Integer> list2 = new ArrayList(XCoordinatesForXOrOSteps);
        Collections.sort(list2);

        for (int i = 0; i < list2.size(); i++) {

            int continuityCounterX = 0;
            int[] forCheckContx = new int[4];

            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(i).equals(list2.get(j)) && continuityCounterX < 4) {
                    forCheckContx[continuityCounterX] = YCoordinatesForXOrOSteps.get(j);
                    continuityCounterX++;
                }

            }
            if (continuityCounterX == 4) {
                Arrays.sort(forCheckContx);
                return continuity(forCheckContx);
            }
        }

        //atlosan talalat balfentrol, jobb le.
        List<String> exOrOoStepsStr = new ArrayList();
        for (int i = 0; i < YCoordinatesForXOrOSteps.size(); i++) {
            String exOrOoStepStr = (YCoordinatesForXOrOSteps.get(i) + 1) + " " + (XCoordinatesForXOrOSteps.get(i) + 1);
            exOrOoStepsStr.add(exOrOoStepStr);
        }

        for (List<String> matchInADiagon : allMatchesInTheDiagons) {
            if (exOrOoStepsStr.containsAll(matchInADiagon)) {
                return true;
            }
        }

        return false;
    }
}



/*
//atlosan talalat balfentrol, jobb le.
        List<String> exStepsStr = new ArrayList();
        for (int i = 0; i < YCoordinatesForXOrOSteps.size(); i++) {
            String exAllStr = (YCoordinatesForXOrOSteps.get(i) + 1) + " " + (XCoordinatesForXOrOSteps.get(i) + 1);
            exStepsStr.add(exAllStr);
        }

        for (int z = 1; z < fieldDimensionY - 2; z++) {
            for (int i = 1; i < fieldDimensionX - 2; i++) {

                List<String> allStepsInTheDiagon1 = List.of(
                        String.format("%s %s", z, i),
                        String.format("%s %s", (z + 1), (i + 1)),
                        String.format("%s %s", (z + 2), (i + 2)),
                        String.format("%s %s", (z + 3), (i + 3)));

                if (exStepsStr.containsAll(allStepsInTheDiagon1)) {
                    return true;
                }
            }
        }

        //bal lentrol, jobb fel
        for (int z = 1; z < fieldDimensionY - 2; z++) {
            for (int i = fieldDimensionX; i > 3; i--) {

                List<String> allStepsInTheDiagon2 = List.of(
                        String.format("%s %s", z, i),
                        String.format("%s %s", (z + 1), (i - 1)),
                        String.format("%s %s", (z + 2), (i - 2)),
                        String.format("%s %s", (z + 3), (i - 3)));

                if (exStepsStr.containsAll(allStepsInTheDiagon2)) {
                    return true;
                }
            }
        }
        return false;
    }
 */

