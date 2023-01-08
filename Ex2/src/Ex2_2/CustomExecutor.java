package Ex2_2;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
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
        // create a new future task to save the task and the future result
        FutureTask futureTask = new FutureTask(task);
        queue.addTask(futureTask);
        return futureTask;
    }

    public Future<T> submit(Callable<T> callable, TaskType type) {
        // create a new future task to save the task and the future result
        Task<T> task = Task.createTask(callable, type);
        return submit(task);
    }

    public Future<T> submit(Callable<T> callable) {
        // create a new future task to save the task and the future result
        Task<T> task = Task.createTask(callable);
        return submit(task);
    }

    public int getCurrentMax(){
        // please note this function does not return the current max priority directly from the queue
        return queue.getCurrentMax();
    }

    public void gracefullyTerminate() {
        // Block the queue for further tasks additions
        queue.blockQueue();

        // Waiting for all tasks in Queue to finish
        try {
            queue.start();
            queue.join();
        } catch (InterruptedException e) {
            System.out.println("Error in shutdown, queue.join()");
            e.printStackTrace();
        }

        // Waiting for all threads to finish
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].stopThread();
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Error in shutdown, threads.join()");
                e.printStackTrace();
            }
        }
        for (int i = 0; i < idleThreads.length; i++) {
            try {
                threads[i].stopThread();
                idleThreads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Error in shutdown, threads.join()");
                e.printStackTrace();
            }
        }
    }
}
