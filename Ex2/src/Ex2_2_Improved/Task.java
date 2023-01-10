package Ex2_2_Improved;

import java.util.concurrent.Callable;

/**
 * This class represents a task that can be executed by a custom thread pool.
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */
public class Task<T> implements Callable<T> {
    private Callable<T> task;
    private TaskType type;

    /**
     * This function creates a new task with the given task and the task type.
     * @param CallableObject - the operation that the task will execute.
     * @param type- The type of the task.
     */
    private Task(Callable<T> CallableObject, TaskType type) {
        this.task = CallableObject;
        this.type = type;
    }

    /**
     * This function creates a task with the given task and the task type.
     * @param callableObject
     * @param type
     * @return task - The task object
     */
    public static <T> Task<T> createTask(Callable<T> callableObject, TaskType type) {
        Task<T> task = new Task<T>(callableObject, type);
        return task;
    }

    /**
     * This function creates a task with the given task.
     * @param callableObject
     * @return task - the new task object
     */
    public static <T> Task<T> createTask(Callable<T> callableObject) {
        return Task.createTask(callableObject, TaskType.OTHER);
    }

    /**
     * This function get the priority value of the type.
     * @return the priority value of the type.
     */
    public int getPriority(){
        return type.getPriorityValue();
    }

    /**
     * This function calls the method for the task.
     * @return task - the result of the task.
     */
    @Override
    public T call() throws Exception {
        return task.call();
    }

}
