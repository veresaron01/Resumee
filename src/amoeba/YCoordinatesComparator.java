package amoeba;

import java.util.Comparator;

public class YCoordinatesComparator implements Comparator<AbstractStep> {
    @Override
    public int compare(AbstractStep abstractStep, AbstractStep t1) {
        return abstractStep.getYCoordinate() - t1.getYCoordinate();
    }
}
