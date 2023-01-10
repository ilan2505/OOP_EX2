package Ex2_2_Improved;


import java.util.concurrent.FutureTask;

/**
 * This class provides
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */
public class FutureTaskAdapter<T> extends FutureTask {

    private Task<T> task;
    
    /**
     * Constructor of the FutureTaskAdapter class.
     * @param task
     */
    public FutureTaskAdapter(Task<T> task)
    {
        super(task);
        this.task = task;

    }

    /**
     * This function
     * @return task -
     */
    public int getPriority()
    {
        return this.task.getPriority();
    }
}
