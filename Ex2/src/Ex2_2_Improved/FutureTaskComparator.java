package Ex2_2_Improved;

import Ex2_2.FutureTask;

import java.util.Comparator;

public class FutureTaskComparator<T> implements Comparator<FutureTaskAdapter<T>> {

    @Override
    public int compare(FutureTaskAdapter<T> t1, FutureTaskAdapter<T> t2) {
        if (t1.getPriority() < t2.getPriority()){
            return -1;
        }
        else if (t1.getPriority() == t2.getPriority())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

}
