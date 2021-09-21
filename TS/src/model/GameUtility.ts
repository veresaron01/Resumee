export class GameUtility {
    tableDimensionY: number;
    tableDimensionX: number;
    fieldSize: number;
    stepCounter: number = 0;
    currentWholeTable: string[][];

    exCoordinatesY: Array<number>;
    exCoordinatesX: Array<number>;
    ooCoordinatesY: Array<number>;
    ooCoordinatesX: Array<number>;
    allStepsInString: Array<string>;
    exStepsInString: Array<string>;
    ooStepsInString: Array<string>;

    allMatchesInTheDiagons: Array<Array<string>>;
    allMatchesHorizontally: Array<Array<string>>;
    allMatchesVertically: Array<Array<string>>;


    constructor(tableDimensionY: number, tableDimensionX: number) {
        this.tableDimensionY = tableDimensionY;
        this.tableDimensionX = tableDimensionX;
        this.fieldSize = tableDimensionY * tableDimensionX;
        this.fillTableWithSpaces();
        this.getDiagonalMatches(4, this.allMatchesInTheDiagons);
        this.getHorizontalMatches(4, this.allMatchesHorizontally);
        this.getVerticalMatches(4, this.allMatchesVertically);
    }

    private fillTableWithSpaces() {

        for (let i = 0; i < this.tableDimensionY; i++) {
            for (let j = 0; j < this.tableDimensionX; j++) {
                this.currentWholeTable[i][j] = ' ';
            }
        }
    }
}

    function getDiagonalMatches(length: number, diagonalPartMatches: Array<Array<string>>): void {

    }























