package Ex2_2;

import java.util.concurrent.Callable;

class PriorityAdaptedTask<T> implements Callable<T>, Comparable<Task<T>> {
    private Task<T> task;

    public PriorityAdaptedTask(Task<T> task) {
        this.task = task;
    }

    @Override
    public int compareTo(Task<T> o) {
        return -1 * task.compareTo(o);
    }

    @Override
    public T call() throws Exception {
        return task.call();
    }
}
