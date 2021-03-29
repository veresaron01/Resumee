package amoeba;

public class View_GameField {

    public void printField(int height, int length, char[][] fieldValues) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print("_____   ");
            }
            System.out.println("");
            for (int j = 0; j < length; j++) {
                System.out.print("| " + fieldValues[i][j] + " |   ");
            }
            System.out.println("");
            for (int j = 0; j < length; j++) {
                System.out.print("|___|   ");
            }
            System.out.println("");
        }

    }

}
