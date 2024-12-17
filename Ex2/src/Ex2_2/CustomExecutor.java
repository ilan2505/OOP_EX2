package Ex2_2;


import java.util.concurrent.*;

/**
 * This class represents a custom executor service.
 * Implements by extending ThreadPoolExecutor.
 * This class provides a thread pool that can schedule tasks to be executed in the future according to the tasks natural order..
 * @author Jonatan Boritsky,  Ilan Meyer Souffir
 */

public class CustomExecutor<T> extends ThreadPoolExecutor {
    private volatile int CurrentMax;
    
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
     * The task is about to add the Priority queue to be executed sometime in the future,
     * this by calling the execute method of the thread pool.
     * @param task - the task that we want to execute.
     * @return futureTask - the future task that we want to execute and where we want to get the result.
     */
    public Future<T> submit(Task<T> task)
    {
        FutureTaskAdapter futureTaskAdapter = new FutureTaskAdapter<T>(task);
        this.execute(futureTaskAdapter);
        return futureTaskAdapter;
    }

    /**
     * This function submits a task with a task type, and gives this task to the executor service.
     * the function creates a new task and call the submit function.
     * @param callable - the operation that we want to execute.
     * @param type - the type of the task.
     * @return FutureTaskAdapter - the future task that we want to execute and where we want to get the result.
     */
    public Future<T> submit(Callable<T> callable, TaskType type) {
        // create a new future task to save the task and the future result
        Task<T> task = Task.createTask(callable, type);
        return this.submit(task);
    }

    /**
     * This function call the submit function with the default task type OTHER.
     * @param callable - the operation that we want to execute.
     * @return task - the future task that we want to execute and where we want to get the result.
     */
    public Future<T> submit(Callable callable)
    {
        return this.submit(callable, TaskType.OTHER);
    }

    /**
     * This function is called when the thread pool is about to execute a task.
     * the function cast the task to a future task adapter and get th priority of the task and assign it to the CurrentMax.
     * @param t - the Thread that is going to execute the task.
     * @param r - the Runnable task that is going to be executed from the Priority queue.
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r){
        this.CurrentMax= ((FutureTaskAdapter)r).getPriority();
        super.beforeExecute(t, r);
    }

    /**
     * This function returns the current max priority of the executed task in the queue.
     * @return - the current max priority of the executed task in the queue.
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
