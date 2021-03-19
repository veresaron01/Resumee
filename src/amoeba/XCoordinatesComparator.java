package amoeba;

import java.util.Comparator;

public class XCoordinatesComparator implements Comparator<AbstractStep> {
    @Override
    public int compare(AbstractStep abstractStep, AbstractStep t1) {
        return abstractStep.getXCoordinate() - t1.getXCoordinate();
    }
}
