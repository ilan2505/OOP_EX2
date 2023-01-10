package Ex2_2_Improved;


import java.util.concurrent.FutureTask;

/**
 * This class provides an adapter for task in order to enable the use of the inner priority queue of thread pool.
 * Which contains only runnable objects.
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */
public class FutureTaskAdapter<T> extends FutureTask {

    private Task<T> task;
    
    /**
     * Constructor of the FutureTaskAdapter class.
     * @param task - the task that we want to adapt.
     */
    public FutureTaskAdapter(Task<T> task)
    {
        super(task);
        this.task = task;

    }

    /**
     * This function returns the priority of the task.
     * @return - the priority of the task.
     */
    public int getPriority()
    {
        return this.task.getPriority();
    }
}
