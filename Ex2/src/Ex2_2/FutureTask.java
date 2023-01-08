package Ex2_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTask<T> implements Future<T>, Comparable<FutureTask<T>> {
    private PriorityAdaptedTask task;
    private T result;

    public FutureTask(Task task) {
        this.task = new PriorityAdaptedTask(task);
    }

    public void setResult(T result) {
        this.result = result;
    }

    public PriorityAdaptedTask getTask() {
        return task;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        while (result == null) {
            continue;
        }
        return result;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        Thread.sleep(unit.toMillis(timeout));
        if (result == null) {
            throw new TimeoutException("Task is not done yet, timeout has been reached");
        }
        return this.get();
    }

    @Override
    public int compareTo(FutureTask o) {
        return this.getTask().compareTo(o.getTask());
    }
}
