package Ex2_2;

import java.util.PriorityQueue;
import java.util.concurrent.Callable;

public class TaskQueue extends Thread {
    private PriorityQueue<FutureTask> queue;
    private int currentMax;

    public TaskQueue() {
        super();
        queue = new PriorityQueue<>();
        currentMax = 0;
    }

    public void run() {
        while (!queue.isEmpty());
    }

    public void addTask(FutureTask futureTask) {
        synchronized (queue) {
            queue.add(futureTask);
            if (futureTask.getTask().getPriority() > currentMax) {
                currentMax = futureTask.getTask().getPriority();
            }
            notifyAll();
        }
    }

    public FutureTask getTask() {
        synchronized (queue) {
            if (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            FutureTask futureTask = queue.poll();
            if (currentMax > futureTask.getTask().getPriority()) {
                currentMax = futureTask.getTask().getPriority();
            }
            return futureTask;
        }
    }

    public int getCurrentMax() {
        return currentMax;
    }
}
