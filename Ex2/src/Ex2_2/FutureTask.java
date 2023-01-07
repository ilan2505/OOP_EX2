package Ex2_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTask implements Future, Comparable<FutureTask> {
    private PriorityAdaptedTask task;
    private Object result;

    public FutureTask(Task task) {
        this.task = new PriorityAdaptedTask(task);
    }

    public void setResult(Object result) {
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
    public Object get() throws InterruptedException, ExecutionException {
        return result;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        Thread.sleep(timeout);
        return result;
    }

    @Override
    public int compareTo(FutureTask o) {
        return this.getTask().compareTo(o.getTask());
    }
}
