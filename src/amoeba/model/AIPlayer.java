package amoeba.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer {

    private GameUtility gameUtility;
    private int whoStarts;

    public List<List<String>> allPartMatchesInTheDiagons3 = new ArrayList();
    public List<List<String>> allPartMatchesInTheDiagons2 = new ArrayList();
    public List<List<String>> allPartMatchesInTheHorizontals3 = new ArrayList();
    public List<List<String>> allPartMatchesInTheHorizontals2 = new ArrayList();
    public List<List<String>> allPartMatchesInTheVerticals3 = new ArrayList();
    public List<List<String>> allPartMatchesInTheVerticals2 = new ArrayList();

    public int aiStepY;
    public int aiStepX;


    public AIPlayer(GameUtility gameUtility, int whoStarts) {
        this.gameUtility = gameUtility;
        this.whoStarts = whoStarts;
        gameUtility.getDiagonalMatches(3, allPartMatchesInTheDiagons3);
        gameUtility.getDiagonalMatches(2, allPartMatchesInTheDiagons2);
        gameUtility.getHorizontalMatches(3, allPartMatchesInTheHorizontals3);
        gameUtility.getHorizontalMatches(2, allPartMatchesInTheHorizontals2);
        gameUtility.getHorizontalMatches(3, allPartMatchesInTheVerticals3);
        gameUtility.getHorizontalMatches(2, allPartMatchesInTheVerticals2);
    }

    // XO12 arra vonatkozik ki kezd, 1-es: AI = O    ||    2-es: AI = X
    public void aiMainAlgorithm (int XO12, List<String> exSteps, List<String> ooSteps) {  //***************

        List<String> exOrOoAllStepsStr;
        if (XO12 == 1) {
            exOrOoAllStepsStr = exSteps;//gameUtility.exStepsInString; ****************
        } else {
            exOrOoAllStepsStr = ooSteps;//gameUtility.ooStepsInString; ******************
        }

        List<String> afterEndDDDoublePoints = new ArrayList<>();
        afterEndDDDoublePoints.addAll(getAfterEndPointsList(allPartMatchesInTheDiagons3, exOrOoAllStepsStr));
        System.out.println(getAfterEndPointsList(allPartMatchesInTheDiagons3, exOrOoAllStepsStr) + "  endpointlist");////////////////////////////***********
        afterEndDDDoublePoints.addAll(getAfterEndPointsList(allPartMatchesInTheHorizontals3, exOrOoAllStepsStr));
        afterEndDDDoublePoints.addAll(getAfterEndPointsList(allPartMatchesInTheVerticals3, exOrOoAllStepsStr));

        System.out.println(afterEndDDDoublePoints.size() + "  afterenpointsList size");////////////////**********




        List<String> afterEndSSSinglePoints = make1PointsListFrom2PointList(afterEndDDDoublePoints);
        List<String> afterEndSSSinglePointsCorrect = new ArrayList<>();
        for (int i = 0; i < afterEndSSSinglePoints.size(); i++) {
            if (isPointEmpty(afterEndSSSinglePoints.get(i)) && isPointOnTable(afterEndSSSinglePoints.get(i), gameUtility.tableDimensionY, gameUtility.tableDimensionX)){
                afterEndSSSinglePointsCorrect.add(afterEndSSSinglePoints.get(i));
            }
        }
        //megkeresi a legközelebb eső találatot a pontjaim gravitációs központjához
        int[] centerOfGravity = centerOfAIPointsGravity();
        int indexOfClosestPointToTheCenterOfGravity = 0;
        double minDistance = 20;
        for (int i = 0; i < afterEndSSSinglePointsCorrect.size(); i++) {
            int[] point = pointParser(afterEndSSSinglePointsCorrect.get(i));
            double distanceY = Math.abs(point[0] - centerOfGravity[0]);
            double distanceX = Math.abs(point[1] - centerOfGravity[1]);
            double distance = Math.sqrt((distanceY * distanceY) + (distanceX * distanceX));

            if (distance < minDistance){
                minDistance = distance;
                indexOfClosestPointToTheCenterOfGravity = i;
            }
        }

        if (minDistance < 20 && ){ //és üres legalább az egyik endpoint
            int[] aiStep = pointParser(afterEndSSSinglePointsCorrect.get(indexOfClosestPointToTheCenterOfGravity));
            aiStepY = aiStep[0] -1;
            aiStepX = aiStep[1] -1;
            return;
        }else { //////dummy steps
            Random random = new Random();
            aiStepY = random.nextInt(5) + 0;
            aiStepX = random.nextInt(5) + 0;
        }






//        List<String> AfterEndDoublePoints = new ArrayList<>();
//        for (int i = 0; i < afterEndDDDoublePoints.size(); i++) {
//            if (isPointEmpty(afterEndDDDoublePoints.get(i)) && isPointOnTable(afterEndDDDoublePoints.get(i), gameUtility.tableDimensionY, gameUtility.tableDimensionX)){
//                AfterEndDoublePoints.add(afterEndDDDoublePoints.get(i));
//            }
//        }
//
//        List<String> correctAfterEndSinglePoints = make1PointsListFrom2PointList(AfterEndDoublePoints);
//
//
//
//        //megkeresi a legközelebb eső találatot a pontjaim gravitációs központjához
//        int[] centerOfGravity = centerOfAIPointsGravity();
//        int indexOfClosestPointToTheCenterOfGravity = 0;
//        double minDistance = 20;
//        for (int i = 0; i < correctAfterEndSinglePoints.size(); i++) {
//            int[] point = pointParser(correctAfterEndSinglePoints.get(i));
//            double distanceY = Math.abs(point[0] - centerOfGravity[0]);
//            double distanceX = Math.abs(point[1] - centerOfGravity[1]);
//            double distance = Math.sqrt((distanceY * distanceY) + (distanceX * distanceX));
//
//            if (distance < minDistance){
//                minDistance = distance;
//                indexOfClosestPointToTheCenterOfGravity = i;
//            }
//        }
//
//        if (minDistance < 20 && isPointOnTable(correctAfterEndSinglePoints.get(indexOfClosestPointToTheCenterOfGravity), gameUtility.tableDimensionY, gameUtility.tableDimensionX) && isPointEmpty(correctAfterEndSinglePoints.get(indexOfClosestPointToTheCenterOfGravity))){
//            int[] aiStep = pointParser(correctAfterEndSinglePoints.get(indexOfClosestPointToTheCenterOfGravity));
//            aiStepY = aiStep[0];
//            aiStepX = aiStep[1];
//            return;
//        } else if (){
//
//        }else { //////dummy steps
//            Random random = new Random();
//            aiStepY = random.nextInt(5) + 0;
//            aiStepX = random.nextInt(5) + 0;
//        }


    }

//    public void aiTakeAStep(){
//        aiMainAlgorithm(whoStarts);
//    }

    public List<String> make1PointsListFrom2PointList(List<String> twoPointsList){
        List<String> onePointsList = new ArrayList<>();

        for (String twoPoints : twoPointsList){
            String[] splittedStr = twoPoints.split(" ");
            int[] twoPointCoords = {
                    Integer.parseInt(splittedStr[0]),
                    Integer.parseInt(splittedStr[1]),
                    Integer.parseInt(splittedStr[2]),
                    Integer.parseInt(splittedStr[3])
            };
            String firstPoint = twoPointCoords[0] + " " + twoPointCoords[1];
            String secondPoint = twoPointCoords[2] + " " + twoPointCoords[3];

            onePointsList.add(firstPoint);
            onePointsList.add(secondPoint);
        }

        return onePointsList;
    }

    public int[] centerOfTable(){
        int[] center = new int[2];
        center[0] = (int) Math.ceil((double) gameUtility.tableDimensionY / 2);
        center[1] = (int) Math.ceil((double) gameUtility.tableDimensionY / 2);
        return center;
    }

    public int[] pointParser(String point){
        String[] pointCoords = point.split(" ");

        int[] pointCoordinates = new int[2];
        pointCoordinates[0] = Integer.parseInt(pointCoords[0]);
        pointCoordinates[1] = Integer.parseInt(pointCoords[1]);

        return  pointCoordinates;
    }

    public int[] centerOfAIPointsGravity(){ // tesztelni h nincs e baj h double-t ad es atkasztolja int-re
        int[] center = new int[2];
        List<Integer> aIStepsY;
        List<Integer> aIStepsX;
        if(whoStarts == 1){
            aIStepsY = gameUtility.ooCoordinatesY;
            aIStepsX = gameUtility.ooCoordinatesX;
        } else{
            aIStepsY = gameUtility.exCoordinatesY;
            aIStepsX = gameUtility.exCoordinatesX;
        }
        int avgY = 0;
        int avgX = 0;
        for (Integer i : aIStepsY){
            avgY = avgY + i;
        }
        for (Integer i : aIStepsX){
            avgX = avgX + i;
        }

        center[0] = (int) Math.ceil((double) avgY / aIStepsY.size());
        center[1] = (int) Math.ceil((double) avgX / aIStepsY.size());

        return center;
    }

    public List<String> getAfterEndPointsList(List<List<String>> diagonsPossible, List<String> exOrOoAllStepsStr){
        List<String> endPoints = findTheEndPointsOfContinuousMatches(diagonsPossible, exOrOoAllStepsStr);
        List<String> afterEndPoints = new ArrayList<>();
        for (String endPoint : endPoints){
            String afterEndPoint = getAfterEndsPoints(endPoint);
            afterEndPoints.add(afterEndPoint);
        }

        //System.out.println(afterEndPoints);//******************************
        return afterEndPoints;
    }

    public boolean isPointEmpty(String point){
        boolean isEmpty = gameUtility.allStepsInString.contains(point);

        return !isEmpty;
    }

    public boolean isPointOnTable(String point, int tableDimensionY, int tableDimensionX){
        String[] y_x = point.split(" ");
        int y = Integer.parseInt(y_x[0]);
        int x = Integer.parseInt(y_x[1]);
        if (y < 0 || y > tableDimensionY || x < 0 || x > tableDimensionX){
            return false;
        }
        return true;
    }

    public List<String> findTheEndPointsOfContinuousMatches(List<List<String>> diagonsPossible, List<String> exOrOoAllStepsStr) {
        List<String> foundDiaMatchesInString = new ArrayList<>();
        for (int i = 0; i < diagonsPossible.size(); i++){
            if (exOrOoAllStepsStr.containsAll(diagonsPossible.get(i))){
                List<String> oneMatch = diagonsPossible.get(i);
                String ends = oneMatch.get(0) + " " + oneMatch.get(oneMatch.size()-1);
                foundDiaMatchesInString.add(ends);
            }
        }
        return foundDiaMatchesInString;
    }

    private static String getAfterEndsPoints(String twoPoints){
        String[] splittedStr = twoPoints.split(" ");
        int[] point = {
                Integer.parseInt(splittedStr[0]),
                Integer.parseInt(splittedStr[1]),
                Integer.parseInt(splittedStr[2]),
                Integer.parseInt(splittedStr[3])
        };
        String startPointsInLineNeigbour = "";
        String endPointsInLineNeigbour = "";

        if (point[0] < point[2]) { //az start pont lentebb van
            if (point[1] < point[3]){ // az start pont balra'bb van
                startPointsInLineNeigbour = (point[0] - 1) + " " + (point[1] - 1);
                endPointsInLineNeigbour = (point[2] + 1) + " " + (point[3] + 1);
            } else if(point[1] == point[3]){ // fuggoleges vonal
                startPointsInLineNeigbour = (point[0] - 1) + " " + (point[1] - 0);
                endPointsInLineNeigbour = (point[2] + 1) + " " + (point[3] + 0);
            } else { // az start pont jobbra'bb van
                startPointsInLineNeigbour = (point[0] - 1) + " " + (point[1] + 1);
                endPointsInLineNeigbour = (point[2] + 1) + " " + (point[3] - 1);
            }
        } else if(point[0] == point[2]){ // vizszintes vonal
            if (point[1] < point[3]){ // az start pont balra'bb van
                startPointsInLineNeigbour = (point[0] - 0) + " " + (point[1] - 1);
                endPointsInLineNeigbour = (point[2] + 0) + " " + (point[3] + 1);
//            } else if(point[1] == point[3]){
//                startPointsInLineNeigbour = (point[0] - 1) + " " + (point[1] - 0);
//                endPointsInLineNeigbour = (point[2] + 1) + " " + (point[3] + 0);
            } else {
                startPointsInLineNeigbour = (point[0] - 0) + " " + (point[1] + 1);
                endPointsInLineNeigbour = (point[2] + 0) + " " + (point[3] - 1);
            }
        } else { // a end pont lentebb van
            if (point[1] < point[3]){ // az start pont balra'bb van
                startPointsInLineNeigbour = (point[0] + 1) + " " + (point[1] - 1);
                endPointsInLineNeigbour = (point[2] - 1) + " " + (point[3] + 1);
            } else if(point[1] == point[3]){ // fuggoleges vonal
                startPointsInLineNeigbour = (point[0] + 1) + " " + (point[1] - 0);
                endPointsInLineNeigbour = (point[2] - 1) + " " + (point[3] + 0);
            } else {
                startPointsInLineNeigbour = (point[0] + 1) + " " + (point[1] + 1);
                endPointsInLineNeigbour = (point[2] - 1) + " " + (point[3] - 1);
            }
        }

        return startPointsInLineNeigbour + " " + endPointsInLineNeigbour;
    }

    public static void main(String[] args) {
//        String points = "4 5 4 0";
        //GameUtility gameUtility2 = new GameUtility(5,5);
        //AIPlayer ai = new AIPlayer(gameUtility2, 1);
//
//        List<List<String>> possible = new ArrayList<>();
//        List<String> p1 = List.of("1 1", "2 2");
//        List<String> p2 = List.of("2 2", "3 3");
//        List<String> p3 = List.of("3 3", "4 4");
//        List<String> p4 = List.of("4 4", "5 5");
//        possible.add(p1);
//        possible.add(p2);
//        possible.add(p3);
//        possible.add(p4);
//
//        List<String> steps = List.of("1 1", "2 2", "3 3",  "4 4", "5 5");

//        System.out.println(ai.findTheEndPointsOfDiagonalContinuousMatches(possible, steps));
//
//        System.out.println();
//
//        System.out.println(gameUtility2.findIndexesOfMatchesWithDifferentLength(possible, 0, steps));
//
//        System.out.println();
//
//        System.out.println(ai.checkAfterEndPointsEmptiness(possible, steps));
//
//        System.out.println();

//        System.out.println(ai.isPointOnTable("1 5",5,5));



//        List<String> ex = List.of("1 1", "2 2", "3 3", "5 5");
//        List<String> ex = List.of("1 1");
//        List<String> oo = List.of( "1 1", "3 3","4 4", "4 5", "2 3", "3 4");
//
//
//        ai.aiMainAlgorithm(2, ex, oo);
//
//        System.out.println(ai.aiStepY + " " + ai.aiStepX);

    }

}