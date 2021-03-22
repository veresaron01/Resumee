/*package amoeba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepO implements AbstractStep{

    private GameLogic gl = new GameLogic();

    private static List<StepO> oSteps = new ArrayList();
    static List<String> oStepsStr = new ArrayList();

    static List<String> oStepsAndXStepsInStr = new ArrayList();

    private int yCoordinate;
    private int xCoordinate;

    public StepO(int y, int x) throws IOException {

        yCoordinate = y;
        xCoordinate = x;

        if (checkValidity(y, x)) {
            oSteps.add(this);
            oStepsStr.add(y + "" + x);
        } else {gl.getStepOInput(); }
    }

    public static List getList () {
        return oSteps;
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

        oStepsAndXStepsInStr.addAll(oStepsStr);
        oStepsAndXStepsInStr.addAll(StepX.xStepsStr);

        String val = y + "" + x;

        if (oStepsAndXStepsInStr.contains(val)) {
            return false;
        }
        oStepsAndXStepsInStr.add(val);

        return true;
    }

}

*/
