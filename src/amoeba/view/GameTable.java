package amoeba.view;

public class GameTable {

    private int height;
    private int length;

    public GameTable(int yDim, int xDim) {
        this.height = yDim;
        this.length = xDim;
    }

    public void printTable(char[][] fieldValues) {
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
