package Ex2_2_Improved;


import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * This class provides
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */

public class CustomExecutor<T> extends ThreadPoolExecutor {
    private volatile int CurrentMax;
    final Logger logger = LoggerFactory.getLogger(CustomExecutor.class);
    
    /**
     * This function creates with number of threads a new custom executor.
     */
    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>(5, new FutureTaskComparator()));
        this.CurrentMax = 0;
    }

    /**
     * This function gives a future task to the executor service.
     * @param task
     * @return futureTaskAdapter -
     */
    public Future<T> submit(Task<T> task)
    {
        FutureTaskAdapter futureTaskAdapter = new FutureTaskAdapter<T>(task);


//        PriorityBlockingQueue<Runnable> f = new PriorityBlockingQueue<Runnable>(5,new FutureTaskComparator());
//        f.add(futureTaskAdapter);
//        task = Task.createTask(task, TaskType.IO);
//        futureTaskAdapter = new FutureTaskAdapter<T>(task);
//        f.add(futureTaskAdapter);

        this.execute(futureTaskAdapter);
        return futureTaskAdapter;
    }

    /**
     * This function submits a task with a task type, and gives this task to the executor service.
     * @param callable -
     * @param type -
     * @return task -
     */
    public Future<T> submit(Callable<T> callable, TaskType type) {
        // create a new future task to save the task and the future result
        Task<T> task = Task.createTask(callable, type);
        return this.submit(task);
    }

    /**
     * This function
     * @param callable -
     * @return task -
     */
    public Future<T> submit(Callable callable)
    {
        return this.submit(callable, TaskType.OTHER);
    }

    /**
     * This function
     * @param t -
     * @param r -
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r){
        this.CurrentMax= ((FutureTaskAdapter)r).getPriority();
        super.beforeExecute(t, r);
    }

    /**
     * This function
     * @return -
     */
    public int getCurrentMax()
    {
        return this.CurrentMax;
    }

    /**
     * This function end off the executor service.
     */
    public void gracefullyTerminate()
    {
        this.shutdown();
    }
}
