//package amoeba.model;
//
//import amoeba.controller.UserInput;
//import amoeba.view.ConsoleTexts;
//import amoeba.view.GameTable;
//
//import java.io.IOException;
//
//public class TwoPlayerGame {
//
//    private GameUtility gameUtility;
//    private UserInput userInput;
//    private GameTable gameTable;
//    private int yDim;
//    private int xDim;
//    private boolean isWinnerFound;
//
//    public TwoPlayerGame(int yDim, int xDim) {
//        gameUtility = new GameUtility(yDim, xDim);
//        userInput = new UserInput();
//        gameTable = new GameTable(yDim, xDim);
//        this.yDim = yDim;
//        this.xDim = xDim;
//    }
//
//    private void takePlayerStep(int XO) throws IOException {
//
//        ConsoleTexts.printWhichPlayerChooses(XO);
//
//        int y;
//        int x;
//
//        while (gameUtility.checkValidity((y = userInput.getStepInput(yDim) -1), (x = userInput.getStepInput(xDim) -1))) {
//            ConsoleTexts.printWrongCoordinates();
//        }
//        if(XO == 1) {
//            gameUtility.addExStep(y, x);
//        } else {
//            gameUtility.addOoStep(y, x);
//        }
//
//        char[][] wholeTable = gameUtility.getCurrentWholeTable();
//
//        gameTable.printTable(yDim, xDim, wholeTable);
//
//    }
//
//    public void initializeTwoPlayerGame(int yDim, int xDim) throws IOException {
//
//        gameTable.printTable(yDim, xDim, gameUtility.getCurrentWholeTable());
//
//        while (!isWinnerFound) {
//            //first player
//
//            takePlayerStep(1);
//
//            if (gameUtility.matcher(1, 4)) {
//                ConsoleTexts.printWinner(1);
//                isWinnerFound = true;
//                continue;
//            }
//            if (gameUtility.checkDrawGame()) {
//                ConsoleTexts.printDraw();
//                isWinnerFound = true;
//                continue;
//            }
//
//            //second player
//
//            takePlayerStep(2);
//
//            if (gameUtility.matcher(2, 4)) {
//                ConsoleTexts.printWinner(2);
//                isWinnerFound = true;
//                continue;
//            }
//            if (gameUtility.checkDrawGame()) {
//                ConsoleTexts.printDraw();
//                isWinnerFound = true;
//                continue;
//            }
//
//        }
//    }
//}