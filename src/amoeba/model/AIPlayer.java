package amoeba.model;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer {

    private GameUtility gameUtility;
    private int whoStarts;

    public List<List<String>> allPartMatchesInTheDiagons3 = new ArrayList();
    public List<List<String>> allPartMatchesInTheDiagons2 = new ArrayList();


    public AIPlayer(GameUtility gameUtility, int whoStarts) {
        this.gameUtility = gameUtility;
        this.whoStarts = whoStarts;
        gameUtility.getDiagonalMatches(3, allPartMatchesInTheDiagons3);
        gameUtility.getDiagonalMatches(2, allPartMatchesInTheDiagons2);
    }

    public void aiTakeAStep (int XO12) { // XO12 arra vonatkozik ki kezd, 1-es: AI = O
        //gameUtility.exAllStepsStr
    }

    public List<String> findTheEndPointsOfDiagonalContinuousMatches(List<List<String>> diagonsPossible, List<String> exOrOoAllStepsStr) { //egzsyerusites egz for ciklusra
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
        String points = "4 5 4 0";
        GameUtility gameUtility2 = new GameUtility(5,5);
        AIPlayer ai = new AIPlayer(gameUtility2, 1);

        List<List<String>> possible = new ArrayList<>();
        List<String> p1 = List.of("1 1", "2 2");
        List<String> p2 = List.of("2 2", "3 3");
        List<String> p3 = List.of("3 3", "4 4");
        List<String> p4 = List.of("4 4", "5 5");
        possible.add(p1);
        possible.add(p2);
        possible.add(p3);
        possible.add(p4);

        List<String> steps = List.of("1 1", "2 2", "3 3",  "4 4", "5 5");

        System.out.println(ai.findTheEndPointsOfDiagonalContinuousMatches(possible, steps));

        System.out.println();

        System.out.println(gameUtility2.findIndexesOfMatchesWithDifferentLength(possible, 0, steps));

    }

}
