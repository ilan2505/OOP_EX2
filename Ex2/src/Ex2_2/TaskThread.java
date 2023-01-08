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
            try {
                FutureTask futureTask;
                if (isIdle) {
                    futureTask = queue.getTask(300);
                }
                else{
                    futureTask = queue.getTask();
                }
                if (futureTask == null) {
                    return;
                }
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
