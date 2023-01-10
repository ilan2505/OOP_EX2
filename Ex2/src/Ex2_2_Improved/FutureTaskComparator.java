package Ex2_2_Improved;

import Ex2_2.FutureTask;

import java.util.Comparator;

/**
 * This class provides
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */
public class FutureTaskComparator<T> implements Comparator<FutureTaskAdapter<T>> {

    /**
     * This function Compares the task with the task for order.
     * @param t1 - object that we compare
     * @param t2 - object that we compare to the other.
     * @return - a 0, -1 or 1  if the object is equal/less/greater to/than the specified object.
     */
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
