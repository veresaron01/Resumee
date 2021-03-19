package amoeba;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewConsoleTexts {

    public static void printGameIntro () {
        System.out.println("Welcome to the Amoeba Game!\n");
        System.out.println("You can play with a human partner, or with my AI.\n");
        System.out.println("Press '9' plus enter for menu at any time in the game.\n");
        System.out.println("You have to make a line of 4 with your character (x/o).\n");
        System.out.println("The line can be horizontal, vertical, or diagonal.\n");
    }

    public static void printMenu (){
        System.out.println("Menu\n\n");
        System.out.println("Choose dimension sizes (4-8)!\n First: a number for height + enter \n Second: a number for width + enter");
    }

    public static void printModeChooser(){
        System.out.println("Choose mode: press \"1\" for One-Player mode | OR | press \"2\" for Multi-Player mode!");
    }

    public static void printFirstPlayerChooses () {
        System.out.println("Player #1`s turn. First choose a row with a number and enter, than the column with a number and enter.");
    }

    public static void printSecondPlayerChooses () {
        System.out.println("Player #2`s turn. First choose a row with a number and enter, than the column with a number and enter.");
    }
}
