package amoeba.model;

import amoeba.controller.UserInput;
import amoeba.view.ConsoleTexts;
import amoeba.view.GameTable;

import java.io.IOException;

public class GameInitializer {

    private final GameUtility gameUtility;
    private final UserInput userInput;
    private final GameTable gameTable;
    private final int yDim;
    private final int xDim;
    private boolean isGameEnded;
    private final int numberOfPlayers;
    private final int whoStarts;
    private final AIPlayer AIPlayer;

    public GameInitializer(int yDim, int xDim, int numberOfPlayers, int whoStarts) {
        gameUtility = new GameUtility(yDim, xDim);
        userInput = new UserInput();
        gameTable = new GameTable(yDim, xDim);
        this.yDim = yDim;
        this.xDim = xDim;
        this.numberOfPlayers = numberOfPlayers;
        this.whoStarts = whoStarts;
        this.AIPlayer = new AIPlayer(gameUtility, whoStarts);
    }

    private void takePlayerStep(int XO) throws IOException {

        ConsoleTexts.printWhichPlayerChooses(XO);

        int y;
        int x;

//        if (numberOfPlayers == 1) { //átírni, a gép teszi a másik pontot (getAIStepY(), getAIStepX() )
//            AIPlayer.aiMainAlgorithm(whoStarts);
//            while (gameUtility.checkValidity((y = AIPlayer.aiStepY - 1), (x = AIPlayer.aiStepX - 1))) {
//                ConsoleTexts.printWrongCoordinates();
//                AIPlayer.aiMainAlgorithm(whoStarts);
//            }
//        } else {
            while (gameUtility.checkValidity((y = userInput.getStepInput(yDim) - 0), (x = userInput.getStepInput(xDim) - 0))) {     // mi szükség van az       ŰŰyŰŰ = userInput.getStepInput(yDim) -1)
                ConsoleTexts.printWrongCoordinates();
            }
//        }

        if (XO == 1) {
            gameUtility.addExStep(y, x);
        } else {
            gameUtility.addOoStep(y, x);
        }

        char[][] wholeTable = gameUtility.getCurrentWholeTable();

        gameTable.printTable(wholeTable);

    }

    private void takeAIPlayerStep(int XO) throws IOException {

        ConsoleTexts.printWhichPlayerChooses(XO);

        int y;
        int x;

        if (gameUtility.allStepsInString.isEmpty()){
            AIPlayer.aiMainAlgorithm(whoStarts, gameUtility.exStepsInString, gameUtility.ooStepsInString);
        }

        while (gameUtility.checkValidity((y = AIPlayer.aiStepY ), (x = AIPlayer.aiStepX ))) { // -1 -1
            ConsoleTexts.printWrongCoordinates();
            AIPlayer.aiMainAlgorithm(whoStarts, gameUtility.ooStepsInString, gameUtility.exStepsInString);
        }

//        while (gameUtility.checkValidity((y = userInput.getStepInput(yDim) - 1), (x = userInput.getStepInput(xDim) - 1))) {     // mi szükség van az       ŰŰyŰŰ = userInput.getStepInput(yDim) -1)
//            ConsoleTexts.printWrongCoordinates();
//
//        }

        if (XO == 1) {
            gameUtility.addExStep(y, x);
        } else {
            gameUtility.addOoStep(y, x);
        }

        char[][] wholeTable = gameUtility.getCurrentWholeTable();

        gameTable.printTable(wholeTable);

    }

    public void initializeGame() throws IOException {

        gameTable.printTable(gameUtility.getCurrentWholeTable());

        while (!isGameEnded) {

//            System.out.println(AIPlayer.findTheEndPointsOfDiagonalContinuousMatches(AIPlayer.allPartMatchesInTheDiagons2, gameUtility.allStepsInString)); /////////////////////
//            System.out.println(AIPlayer.allPartMatchesInTheDiagons2);
            //System.out.println(AIPlayer.isPointEmpty("2 2") + "2 2 is empty");
            System.out.println(gameUtility.allStepsInString + "  all steps in string");

            //first player
            if (numberOfPlayers == 1) {
                takeAIPlayerStep(1);
            } else {
                takePlayerStep(1);
            }


            if (gameUtility.matcher(1)) {
                ConsoleTexts.printWinner(1);
                isGameEnded = true;
                continue;
            }
            if (gameUtility.checkDrawGame()) {
                ConsoleTexts.printDraw();
                isGameEnded = true;
                continue;
            }

            //second player
            takePlayerStep(2);

            if (gameUtility.matcher(2)) {
                ConsoleTexts.printWinner(2);
                isGameEnded = true;
                continue;
            }
            if (gameUtility.checkDrawGame()) {
                ConsoleTexts.printDraw();
                isGameEnded = true;
                continue;
            }

        }
    }

}
