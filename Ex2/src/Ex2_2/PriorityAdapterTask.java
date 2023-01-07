package Ex2_2;

import java.util.concurrent.Callable;

class PriorityAdaptedTask<T> implements Callable<T>, Comparable<PriorityAdaptedTask<T>> {
    private Task<T> task;

    public PriorityAdaptedTask(Task<T> task) {
        this.task = task;
    }

    @Override
    public int compareTo(PriorityAdaptedTask<T> o) {
        return -1 *this.getTask().compareTo(o.getTask());
    }

    @Override
    public T call() throws Exception {
        return task.call();
    }

    public int getPriority() {
        return task.getPriority();
    }

    public Task<T> getTask() {
        return task;
    }
}
