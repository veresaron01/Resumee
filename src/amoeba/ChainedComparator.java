package amoeba;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ChainedComparator implements Comparator<AbstractStep> {

    private List<Comparator<AbstractStep>> comparators;

    public ChainedComparator(Comparator<AbstractStep>... comparators) {
        this.comparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(AbstractStep as, AbstractStep t1) {
        for(Comparator<AbstractStep> comp : comparators){
            int result = comp.compare(as, t1);
            if(result != 0){
                return result;
            }
        }
        return 0;
    }

}
