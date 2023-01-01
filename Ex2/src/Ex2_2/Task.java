package Ex2_2;

import java.util.Comparator;
import java.util.concurrent.Callable;

public class Task<T> implements Callable<T>, Comparable<Task<T>> {
    private Callable<T> task;
    private TaskType type;

    private Task(Callable<T> CallableObject, TaskType type) {
        this.task = CallableObject;
        this.type = type;
    }

    public static <T> Task<T> createTask(Callable<T> callableObject, TaskType type) {
        Task<T> task = new Task<T>(callableObject, type);
        return task;
    }

    public static <T> Task<T> createTask(Callable<T> callableObject) {
        return Task.createTask(callableObject, TaskType.OTHER);
    }

    public TaskType getType(){
        return type;
    }

    @Override
    public T call() throws Exception {
        return task.call();
    }

    @Override
    public int compareTo(Task<T> o) {
        return (this.getType().getPriorityValue() - o.getType().getPriorityValue());
    }
}
