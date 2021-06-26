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
        gameUtility.getVerticalMatches(3, allPartMatchesInTheVerticals3);
        gameUtility.getVerticalMatches(2, allPartMatchesInTheVerticals2);
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
        afterEndDDDoublePoints.addAll(getAfterEndPointsList(allPartMatchesInTheHorizontals3, exOrOoAllStepsStr));
        afterEndDDDoublePoints.addAll(getAfterEndPointsList(allPartMatchesInTheVerticals3, exOrOoAllStepsStr));

        //logs
        System.out.println(allPartMatchesInTheHorizontals3 + "allPartMatchesInTheHorizontals3");
        System.out.println(afterEndDDDoublePoints + "allAfterEndPointsDoublePoints");
        System.out.println(afterEndDDDoublePoints.size() + "  afterenpointsList size");


        List<String> afterEndSSSinglePoints = make1PointsListFrom2PointsList(afterEndDDDoublePoints);
        List<String> afterEndSSSinglePointsCorrect = filterOutInvalidPoints(afterEndSSSinglePoints);


        int indexOfClosestPointToTheCenterOfGravity = findClosestCoordinateToACenterPoint(pointParser(getCenterOfGravity(exOrOoAllStepsStr))[0], pointParser(getCenterOfGravity(exOrOoAllStepsStr))[1], afterEndSSSinglePointsCorrect);//**************

        List<Integer> bestPointsToTake = findClosestCoordinateSSSToACenterPoint(pointParser(getCenterOfGravity(exOrOoAllStepsStr))[0], pointParser(getCenterOfGravity(exOrOoAllStepsStr))[1], afterEndSSSinglePointsCorrect);



        System.out.println(afterEndSSSinglePointsCorrect);


        if (afterEndDDDoublePoints.isEmpty()){
            Random random = new Random(); //dummy steps
            aiStepY = random.nextInt(5) + 0;
            aiStepX = random.nextInt(5) + 0;
            System.out.println("C");
        } else {
            for (int i = 0; i < bestPointsToTake.size(); i++) {
                String bestStep = afterEndSSSinglePointsCorrect.get(bestPointsToTake.get(i));

                //if ()

                aiStepY = pointParser(bestStep)[0];
                aiStepX = pointParser(bestStep)[1];


            }
        }



        System.out.println(aiStepY + " " + aiStepX + " asdasd");












////mess
//        if (indexOfClosestPointToTheCenterOfGravity > -1 && !afterEndSSSinglePointsCorrect.isEmpty()){
//            int[] aiStep = pointParser(afterEndSSSinglePointsCorrect.get(indexOfClosestPointToTheCenterOfGravity));
//
//            String point = aiStep[0] + " " + aiStep[1];
//            System.out.println("D");
////            if (!isPointFull(point) && !isPointOnTable(point, gameUtility.tableDimensionY, gameUtility.tableDimensionX)){
//            if (aiStep[0] >= 0 && aiStep[1] >= 0 && !isPointFull(point)){
//                aiStepY = aiStep[0] -1;
//                aiStepX = aiStep[1] -1;
//                System.out.println("A");
//            }else {
//
//            }
//
//        }else {
//            System.out.println(gameUtility.allStepsInString + "allStepsInString");//*******************
//            if (gameUtility.allStepsInString.size() < 1){
//                //AIPlayer.aiMainAlgorithm(whoStarts, gameUtility.exStepsInString, gameUtility.ooStepsInString);
//                int[] i = centerOfTable();
//                System.out.println(i[0] + " " + i[1] + " az 'üres' pont");
//                aiStepY = i[0] -0;
//                aiStepX = i[1] -0;
//                System.out.println("B");
//            } else { //////dummy steps
//                Random random = new Random();
//                aiStepY = random.nextInt(5) + 1;
//                aiStepX = random.nextInt(5) + 1;
//                System.out.println("C");
//            }
//        }

    }

    //PIPA
    public int findClosestCoordinateToACenterPoint(int centerY, int centerX, List<String> points){
        int indexOfClosestPointToTheCenterOfGravity = -1;
        double minDistance = 20;
        for (int i = 0; i < points.size(); i++) {
            int[] point = pointParser(points.get(i));
            double distanceY = Math.abs(point[0] - centerY);
            double distanceX = Math.abs(point[1] - centerX);
            double distance = Math.sqrt((distanceY * distanceY) + (distanceX * distanceX));

            if (distance < minDistance){
                minDistance = distance;
                indexOfClosestPointToTheCenterOfGravity = i;
            }
        }

        return indexOfClosestPointToTheCenterOfGravity;
    }

    //PIPA
    public List<Integer> findClosestCoordinateSSSToACenterPoint(int centerY, int centerX, List<String> points){
        List<Integer> indicesInRelevantOrder = new ArrayList<>();

        for (int j = 0; j < points.size(); j++) {

            int indexOfClosestPointToTheCenterOfGravity = -1;
            double minDistance = 20;
            for (int i = 0; i < points.size(); i++) {
                if (!indicesInRelevantOrder.contains(i)){
                    int[] point = pointParser(points.get(i));
                    double distanceY = Math.abs(point[0] - centerY);
                    double distanceX = Math.abs(point[1] - centerX);
                    double distance = Math.sqrt((distanceY * distanceY) + (distanceX * distanceX));

                    if (distance < minDistance){
                        minDistance = distance;
                        indexOfClosestPointToTheCenterOfGravity = i;
                    }
                }
            }
            if (indexOfClosestPointToTheCenterOfGravity >= 0) {
                indicesInRelevantOrder.add(indexOfClosestPointToTheCenterOfGravity);
            }
        }
        return indicesInRelevantOrder;
    }

    //PIPA
    public String getCenterOfGravity(List<String> exORooAllPoints){
        int Ysum = 0;
        int Xsum = 0;
        for (String point : exORooAllPoints){
            Ysum += pointParser(point)[0];
            Xsum += pointParser(point)[1];
        }
        String center = ((int) Math.round((double) Ysum / exORooAllPoints.size())) + " " + ((int) Math.round((double) Xsum / exORooAllPoints.size()));
        return center;
    }


    //PIPA
    public List<String> make1PointsListFrom2PointsList(List<String> twoPointsList){
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

    //PIPA
    public List<String> filterOutInvalidPoints(List<String> pointsToValidate) {
        List<String> correctPoints = new ArrayList<>();
        for (int i = 0; i < pointsToValidate.size(); i++) {
            if (!isPointFull(pointsToValidate.get(i)) && isPointOnTable(pointsToValidate.get(i), gameUtility.tableDimensionY, gameUtility.tableDimensionX)){
                correctPoints.add(pointsToValidate.get(i));
            }
        }
        return correctPoints;
    }

    //PIPA
    public int[] centerOfTable(){
        int[] center = new int[2];
        center[0] = (int) Math.ceil((double) gameUtility.tableDimensionY / 2);
        center[1] = (int) Math.ceil((double) gameUtility.tableDimensionX / 2);
        return center;
    }

    //PIPA
    public int[] pointParser(String point){
        String[] pointCoords = point.split(" ");

        int[] pointCoordinates = new int[2];
        pointCoordinates[0] = Integer.parseInt(pointCoords[0]);
        pointCoordinates[1] = Integer.parseInt(pointCoords[1]);

        return  pointCoordinates;
    }

    //PIPA
    public String intarrParser(int... a){
        return a[0] + " " + a[1];
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

    public boolean isPointFull(String point){
        boolean isEmpty = gameUtility.allStepsInString.contains(point);

        return isEmpty;
    }

    //PIPA**************
    public boolean isPointOnTable(String point, int tableDimensionY, int tableDimensionX){
        String[] y_x = point.split(" ");
        int y = Integer.parseInt(y_x[0]);
        int x = Integer.parseInt(y_x[1]);
        if (y < 0 || y > tableDimensionY || x < 0 || x > tableDimensionX){ //*********talan >= kell a vegere is
            return false;
        }
        return true;
    }

    //PIPA
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
        GameUtility gameUtility2 = new GameUtility(5,5);
        AIPlayer ai = new AIPlayer(gameUtility2, 1);





//        String points = "4 5 4 0";

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

//        System.out.println(gameUtility2.findIndexesOfMatchesWithDifferentLength(possible, 0, steps));

//        System.out.println(ai.checkAfterEndPointsEmptiness(possible, steps));

//        System.out.println(ai.isPointOnTable("1 5",5,5));



//        List<String> ex = List.of("1 1", "2 2", "4 4", "5 5");
//        List<String> ex = List.of("1 1");
//        List<String> oo = List.of( "1 1", "3 3", "2 2", "4 5", "2 3", "3 4");

//        List<String> ex = List.of("1 1");
//        List<String> oo = List.of( "1 1", "1 2","1 3");
//
//        ai.aiMainAlgorithm(2, ex, oo);
//        System.out.println(ai.aiStepY + " " + ai.aiStepX);



//        List<Integer> integersY = List.of(1,2,4,20);
//        List<Integer> integersX = List.of(1,2,4,10);
//        int[] i = ai.centerOfAIPointsGravity(integersY,integersX);
//        System.out.println(i[0] + " " + i[1]);

//        List<String> oo = List.of( "3 4", "3 3", "3 3", "3 4", "5 1", "5 5");
//        System.out.println(ai.findClosestCoordinateSSSToTheCenterOfGravity(3, 3, oo));




    }

}
