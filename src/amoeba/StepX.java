/*package amoeba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepX implements AbstractStep{

    private GameLogic gl = new GameLogic();

    private static List<StepX> xSteps = new ArrayList();
    static List<String> xStepsStr = new ArrayList();

    static List<String> xStepsAndOStepsInStr = new ArrayList();

    private int yCoordinate;
    private int xCoordinate;

    public StepX(int y, int x) throws IOException {

        yCoordinate = y;
        xCoordinate = x;

        if (checkValidity(y, x)) {
            xSteps.add(this);
            xStepsStr.add(y + "" + x);
        } else {gl.getStepXInput(); }
    }

    public static List getList () {
        return xSteps;
    }

    @Override
    public int getXCoordinate() {
        return xCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return yCoordinate;
    }

    private boolean checkValidity (int y, int x) {

        xStepsAndOStepsInStr.addAll(xStepsStr);
        xStepsAndOStepsInStr.addAll(StepO.oStepsStr);

        String val = y + "" + x;

        if (xStepsAndOStepsInStr.contains(val)) {
            return false;
        }
        xStepsAndOStepsInStr.add(val);

        return true;
    }

}
*/