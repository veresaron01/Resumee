package amoeba;

public class View_ConsoleTexts {

    public void printGameIntro() {
        System.out.println("\n\nWelcome to the Amoeba Game!\n");
        System.out.println("You can play with a human partner, or with my AI.\n");
        //System.out.println("Press '9' plus enter for menu at any time in the game.\n");
        System.out.println("You have to make a line of 4 with your character (x/o).\n");
        System.out.println("The line can be horizontal, vertical, or diagonal.\n");
    }

    public void printWrongFieldDimensionSizes() {
        System.out.println("Choose dimension sizes (4-8)!\n First: a number for height + enter \n Second: a number for width + enter");
    }

    public static void printFirstPlayerChooses() {
        System.out.println("Player #1`s turn. First choose a row with a number and enter, than the column with a number and enter.");
    }

    public static void printSecondPlayerChooses() {
        System.out.println("Player #2`s turn. First choose a row with a number and enter, than the column with a number and enter.");
    }

    public static void printWrongCoordinates() {
        System.out.println("Choose empty coordinates.");
    }

    public static void printWinner1() {
        System.out.println("\n\nThe Winner is Player #1!!! (\"X\") Congratulations!");
    }

    public static void printWinner2() {
        System.out.println("\n\nThe Winner is Player #2!!! (\"O\") Congratulations!");
    }

    public static void printDraw() {
        System.out.println("No Player won, draw-match.");
    }
}
