package amoeba.model;

import java.util.*;

public class GameUtility {

    public int tableDimensionY;
    public int tableDimensionX;
    private int fieldSize;
    private int stepCounter = 0;
    private char[][] currentWholeTable;

    protected List<Integer> exCoordinatesY = new ArrayList();
    protected List<Integer> exCoordinatesX = new ArrayList();
    protected List<Integer> ooCoordinatesY = new ArrayList();
    protected List<Integer> ooCoordinatesX = new ArrayList();
    protected List<String> allStepsInString = new ArrayList();
    protected List<String> exStepsInString = new ArrayList();
    protected List<String> ooStepsInString = new ArrayList();

    private List<List<String>> allMatchesInTheDiagons = new ArrayList();
    private List<List<String>> allMatchesHorizontally = new ArrayList<>();
    private List<List<String>> allMatchesVertically = new ArrayList<>();

    public GameUtility(int tableDimensionY, int tableDimensionX) {
        this.tableDimensionY = tableDimensionY;
        this.tableDimensionX = tableDimensionX;
        this.fieldSize = tableDimensionY * tableDimensionX;
        fillTableWithSpaces();
        getDiagonalMatches(4, allMatchesInTheDiagons);
        getHorizontalMatches(4 ,allMatchesHorizontally);
        getVerticalMatches(4, allMatchesVertically);
    }

    private void fillTableWithSpaces() {

        this.currentWholeTable = new char[tableDimensionY][tableDimensionX];
        for (int i = 0; i < tableDimensionY; i++) {
            for (int j = 0; j < tableDimensionX; j++) {
                currentWholeTable[i][j] = ' ';
            }
        }
    }
//nem jo eppen
    public void getDiagonalMatches(int length, List<List<String>> diagonalPartMatches) { //TODO

        //atlosan lehetseges talalatok bal fentrol, jobb le.
        for (int i = 1; i < tableDimensionY +2 - length; i++) {
            for (int j = 1; j < tableDimensionX +2 - length; j++) {

                List<String> oneMatchOnADiagon = new ArrayList<>();
                for (int k = 0; k < length + 0; k++){
                    oneMatchOnADiagon.add(String.format("%s %s", (i + k), (j + k)));
                }
                diagonalPartMatches.add(oneMatchOnADiagon);
            }
        }

        //atlosan lehetseges talalatok, bal lentrol, jobb fel
        for (int i = 1; i < tableDimensionY +2 - length; i++) {
            for (int j = tableDimensionX + 1; j > length; j--) {

                List<String> oneMatchOnADiagon = new ArrayList<>();
                for (int k = 1; k < length + 1; k++){
                    oneMatchOnADiagon.add(String.format("%s %s", (i + k - 1), (j - k)));
                }
                diagonalPartMatches.add(oneMatchOnADiagon);
            }
        }
        System.out.println(diagonalPartMatches);

    }

    public void getHorizontalMatches(int length, List<List<String>> partMatches){
        for (int i = 1; i < tableDimensionY + 2; i++){
            for (int j = 1; j < tableDimensionX - length + 2; j++){

                List<String> oneMatchOnAHorizontalLine = new ArrayList<>();
                for (int k = 1; k < length + 1; k++){
                    oneMatchOnAHorizontalLine.add(String.format("%s %s", (i), (j + k)));
                }
                partMatches.add(oneMatchOnAHorizontalLine);
            }
        }
    }

    public void getVerticalMatches(int length, List<List<String>> partMatches){
        for (int i = 1; i < tableDimensionX + 2; i++){
            for (int j = 1; j < tableDimensionY - length + 2; j++){

                List<String> oneMatchOnAVerticalLine = new ArrayList<>();
                for (int k = 1; k < length + 1; k++){
                    oneMatchOnAVerticalLine.add(String.format("%s %s", (j + k), (i)));
                }
                partMatches.add(oneMatchOnAVerticalLine);
            }
        }
    }

    public char[][] getCurrentWholeTable() {

        ////////////////////////////////////
        for (int i = 0; i < exCoordinatesY.size(); i++) {
            if (exCoordinatesY.get(i) < 0 || exCoordinatesX.get(i) < 0){
                System.out.println(exCoordinatesY.get(i) + " **XX " + exCoordinatesX.get(i));
            }
        }
        for (int i = 0; i < ooCoordinatesY.size(); i++) {
            if (ooCoordinatesY.get(i) < 0 || ooCoordinatesX.get(i) < 0){
                System.out.println(ooCoordinatesY.get(i) + " **OO " + ooCoordinatesX.get(i));
            }
        }
        ////////////////////////////////////


        for (int i = 0; i < exCoordinatesY.size(); i++) {
            currentWholeTable[(exCoordinatesY.get(i) -0)][(exCoordinatesX.get(i) -0)] = 'X';
        }

        for (int i = 0; i < ooCoordinatesY.size(); i++) {
            currentWholeTable[(ooCoordinatesY.get(i) -0)][(ooCoordinatesX.get(i) -0)] = 'O';
        }

        return currentWholeTable;
    }

    public boolean checkValidity(int y, int x) {
        String val = String.format("%s %s", y, x);

        if (allStepsInString.contains(val)){// || y > fieldDimensionY || x > fieldDimensionX) {
            return true;
        }
        allStepsInString.add(val);
        return false;
    }

    public void addExStep(int y, int x) {
        exCoordinatesY.add(y-1);/////////////////
        exCoordinatesX.add(x-1);/////////////////
        stepCounter++;
        exStepsInString.add((y + 0) + " " + (x + 0));
        System.out.println((y + 0) + " " + (x + 0) + " X");
    }

    public void addOoStep(int y, int x) {
        ooCoordinatesY.add(y-1);///////////////////
        ooCoordinatesX.add(x-1);///////////////////
        stepCounter++;
        ooStepsInString.add((y + 0) + " " + (x + 0));
        System.out.println((y + 0) + " " + (x + 0) + " Y");
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
            exOrOoAllStepsStr = exStepsInString;
        } else {
            exOrOoAllStepsStr = ooStepsInString;
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

    // barrior talan kiveheto
    protected int findIndexesOfMatchesWithDifferentLength(List<List<String>> allMatchesInDifferentDirectionsDifferentLongs, int searchStartBarrior_fromMinus1, List<String> exOrOoAllStepsStr) {
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