package amoeba.model;

import java.util.*;

public class GameUtility {

    private int fieldDimensionY;
    private int fieldDimensionX;
    private int fieldSize;
    private int stepCounter = 0;
    private char[][] currentWholeTable;

    private List<Integer> exCoordinatesY = new ArrayList();
    private List<Integer> exCoordinatesX = new ArrayList();
    private List<Integer> ooCoordinatesY = new ArrayList();
    private List<Integer> ooCoordinatesX = new ArrayList();
    protected List<String> allStepsInString = new ArrayList();
    protected List<String> exAllStepsStr = new ArrayList();
    protected List<String> ooAllStepsStr = new ArrayList();

    private List<List<String>> allMatchesInTheDiagons = new ArrayList();
    private List<List<String>> allMatchesHorizontally = new ArrayList<>();
    private List<List<String>> allMatchesVertically = new ArrayList<>();

    public GameUtility(int fieldDimensionY, int fieldDimensionX) {
        this.fieldDimensionY = fieldDimensionY;
        this.fieldDimensionX = fieldDimensionX;
        this.fieldSize = fieldDimensionY * fieldDimensionX;
        fillTableWithSpaces();
        getDiagonalMatches(4, allMatchesInTheDiagons);
        getHorizontalMatches(4 ,allMatchesHorizontally);
        getVerticalMatches(4, allMatchesVertically);
    }

    private void fillTableWithSpaces() {

        this.currentWholeTable = new char[fieldDimensionY][fieldDimensionX];
        for (int i = 0; i < fieldDimensionY; i++) {
            for (int j = 0; j < fieldDimensionX; j++) {
                currentWholeTable[i][j] = ' ';
            }
        }
    }

    public void getDiagonalMatches(int length, List<List<String>> diagonalPartMatches) {

        //atlosan lehetseges talalatok bal fentrol, jobb le.
        for (int i = 0; i < fieldDimensionY - 3 + 4 - length; i++) {
            for (int j = 0; j < fieldDimensionX - 3 + 4 - length; j++) {

                List<String> oneMatchOnADiagon = new ArrayList<>();
                for (int k = 1; k < length + 1; k++){
                    oneMatchOnADiagon.add(String.format("%s %s", (i + k), (j + k)));
                }
                diagonalPartMatches.add(oneMatchOnADiagon);
            }
        }

        //atlosan lehetseges talalatok, bal lentrol, jobb fel
        for (int i = 0; i < fieldDimensionY - 3 + 4 - length; i++) {
            for (int j = fieldDimensionX + 1; j > length; j--) {

                List<String> oneMatchOnADiagon = new ArrayList<>();
                for (int k = 1; k < length + 1; k++){
                    oneMatchOnADiagon.add(String.format("%s %s", (i + k), (j - k)));
                }
                diagonalPartMatches.add(oneMatchOnADiagon);
            }
        }

    }

    public void getHorizontalMatches(int length, List<List<String>> partMatches){
        for (int i = 1; i < fieldDimensionY + 1; i++){
            for (int j = 0; j < fieldDimensionX - length + 1; j++){

                List<String> oneMatchOnAHorizontalLine = new ArrayList<>();
                for (int k = 1; k < length + 1; k++){
                    oneMatchOnAHorizontalLine.add(String.format("%s %s", (i), (j + k)));
                }
                partMatches.add(oneMatchOnAHorizontalLine);
            }
        }
    }

    public void getVerticalMatches(int length, List<List<String>> partMatches){
        for (int i = 1; i < fieldDimensionX + 1; i++){
            for (int j = 0; j < fieldDimensionY - length + 1; j++){

                List<String> oneMatchOnAVerticalLine = new ArrayList<>();
                for (int k = 1; k < length + 1; k++){
                    oneMatchOnAVerticalLine.add(String.format("%s %s", (j + k), (i)));
                }
                partMatches.add(oneMatchOnAVerticalLine);
            }
        }
    }

    public char[][] getCurrentWholeTable() {

        for (int i = 0; i < exCoordinatesY.size(); i++) {
            currentWholeTable[exCoordinatesY.get(i)][exCoordinatesX.get(i)] = 'X';
        }

        for (int i = 0; i < ooCoordinatesY.size(); i++) {
            currentWholeTable[ooCoordinatesY.get(i)][ooCoordinatesX.get(i)] = 'O';
        }

        return currentWholeTable;
    }

    public boolean checkValidity(int y, int x) {
        String val = String.format("%s%s", y, x);

        if (allStepsInString.contains(val)){// || y > fieldDimensionY || x > fieldDimensionX) {
            return true;
        }
        allStepsInString.add(val);
        return false;
    }

    public void addExStep(int y, int x) {
        exCoordinatesY.add(y);
        exCoordinatesX.add(x);
        stepCounter++;
        exAllStepsStr.add((y + 1) + " " + (x + 1));
    }

    public void addOoStep(int y, int x) {
        ooCoordinatesY.add(y);
        ooCoordinatesX.add(x);
        stepCounter++;
        ooAllStepsStr.add((y + 1) + " " + (x + 1));
    }

    public boolean checkDrawGame() {
        return stepCounter == fieldSize;
    }

    public static boolean checkContinuity(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1] - 1) {
                return false;
            }
        }
        return true;
    }

    //talalatok megtalalasa (megadott darab egy egyenes menten megszakitas nelkul, egy fajtabol (X=1 || O=2)
    public boolean matcher(int XO) {
        List<String> exOrOoAllStepsStr;
        if (XO == 1) {
            exOrOoAllStepsStr =exAllStepsStr;
        } else {
            exOrOoAllStepsStr = ooAllStepsStr;
        }
        //vizszintes talalat
        int a = findIndexesOfMatchesWithDifferentLength(allMatchesHorizontally, 0, exOrOoAllStepsStr);
        if (a != -1){
            System.out.println(a);
            return true;
        }
        //fuggoleges talalat
        if (findIndexesOfMatchesWithDifferentLength(allMatchesVertically, 0, exOrOoAllStepsStr) != -1){
            return true;
        }
        //atlosan talalat
        if (findIndexesOfMatchesWithDifferentLength(allMatchesInTheDiagons, 0, exOrOoAllStepsStr) != -1){
            return true;
        }
        return false;
    }

    protected int findIndexesOfMatchesWithDifferentLength(List<List<String>> allMatchesInDifferentDirectionsDifferentLongs, int searchStartBarrior_fromMinus1, List<String> exOrOoAllStepsStr) { // barrior talan kiveheto
        int indexOfParticularLineSegment = -1;

        for (int i = searchStartBarrior_fromMinus1; i < allMatchesInDifferentDirectionsDifferentLongs.size(); i++){
            if (exOrOoAllStepsStr.containsAll(allMatchesInDifferentDirectionsDifferentLongs.get(i))){
                indexOfParticularLineSegment = i;
                return indexOfParticularLineSegment;
            }
        }
        return  indexOfParticularLineSegment;
    }
}