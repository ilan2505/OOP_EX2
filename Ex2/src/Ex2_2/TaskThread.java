package Ex2_2;

import java.util.concurrent.Future;

public class TaskThread extends Thread {
    private TaskQueue queue;
    private Object result;

    private boolean isRunning;
    private boolean isIdle;

    public TaskThread(TaskQueue queue) {
        this.queue = queue;
        this.isRunning = true;
        isIdle = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (isIdle) {
                if(this.getState() == State.WAITING) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (this.getState() == State.WAITING) {
                    stopThread();
                }
            }
            try {
                FutureTask futureTask = queue.getTask();
                futureTask.setResult(futureTask.getTask().call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setIdle(){
        this.isIdle = true;
    }

    public Object getResult(int timeout) {
        try {
            this.join(timeout);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void stopThread() {
        isRunning = false;
    }
}
