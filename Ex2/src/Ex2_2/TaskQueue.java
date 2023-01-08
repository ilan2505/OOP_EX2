package Ex2_2;

import java.util.PriorityQueue;
import java.util.Stack;

public class TaskQueue extends Thread {
    private PriorityQueue<FutureTask> queue;
    private boolean isBlocked;
    private volatile int currentMax;

    public TaskQueue() {
        super();
        queue = new PriorityQueue<>();
        currentMax = 10;
        isBlocked = false;
    }

    public void run() {
        while (!queue.isEmpty());
    }

    public void addTask(FutureTask futureTask) {
        if(isBlocked){
            return;
        }
        synchronized (this) {
            queue.add(futureTask);
            if (futureTask.getTask().getPriority() < currentMax) {
                currentMax = futureTask.getTask().getPriority();
            }
            notifyAll();
        }
    }

    public FutureTask getTask() {
        synchronized (this) {
            while (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            FutureTask futureTask = queue.poll();
            currentMax = futureTask.getTask().getPriority();
            return futureTask;
        }
    }

    public FutureTask getTask(int timeout) {
        synchronized (this) {
            if (queue.isEmpty()) {
                try {
                    wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (queue.isEmpty()) {
                return null;
            }
        }
        synchronized (this) {
            FutureTask futureTask = queue.poll();
            currentMax = futureTask.getTask().getPriority();
            return futureTask;
        }
    }

    public int getCurrentMax() {
        return currentMax;
    }

    public void blockQueue(){
        isBlocked = true;
    }

    public int getSize(){
        return queue.size();
    }
}
