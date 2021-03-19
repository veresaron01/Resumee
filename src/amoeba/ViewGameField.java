package amoeba;

public class ViewGameField {

    public void printField(int height, int length, char[][] fieldValues){
        char[][] field = new char[length][height];
        field = fieldValues;

        for(int i = 0; i < height; i++) {
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
