package Ex2_2;

import java.util.PriorityQueue;
import java.util.Stack;

public class TaskQueue extends Thread {
    private PriorityQueue<FutureTask> queue;
    private boolean isBlocked;
    private Stack<Integer> currentMax;

    public TaskQueue() {
        super();
        queue = new PriorityQueue<>();
        currentMax = new Stack<>();
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
            if (currentMax.isEmpty()) {
                currentMax.push(futureTask.getTask().getPriority());
            }
            else if (futureTask.getTask().getPriority() < currentMax.peek()) {
                currentMax.push(futureTask.getTask().getPriority());
            }
            notify();
        }
    }

    public FutureTask getTask() {
        synchronized (this) {
            if (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (this) {
            FutureTask futureTask = queue.poll();
            if (currentMax.size() > 1) {
                currentMax.pop();
            }
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
            if (currentMax.size() > 1) {
                currentMax.pop();
            }
            return futureTask;
        }
    }

    public int getCurrentMax() {
        return currentMax.peek();
    }

    public void blockQueue(){
        isBlocked = true;
    }

    public int getSize(){
        return queue.size();
    }
}
