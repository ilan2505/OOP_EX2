package Ex2_2;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CustomExecutor <T>{
    private TaskQueue queue;
    private TaskThread[] threads;
    private TaskThread[] idleThreads;

    HashMap<Task, T> taskResults;
    int count = 0;
    private int maxThreads;
    private int minThreads;
    private int currentThreads;

    public CustomExecutor() {
        int availableCores = Runtime.getRuntime().availableProcessors();
        queue = new TaskQueue();
        this.maxThreads = availableCores - 1;
        this.minThreads = availableCores / 2;
        this.currentThreads = minThreads;
        threads = new TaskThread[minThreads];
        idleThreads = new TaskThread[maxThreads - minThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new TaskThread(queue);
            threads[i].start();
        }
        for (int i = 0; i < idleThreads.length; i++) {
            idleThreads[i] = new TaskThread(queue);
            idleThreads[i].setIdle();
            idleThreads[i].start();
        }
    }

    public Future<T> submit(Task<T> task) {
        FutureTask futureTask = new FutureTask(task);
        queue.addTask(futureTask);
        return futureTask;
    }
}
