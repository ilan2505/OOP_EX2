package Ex2_2_Improved;


import java.util.concurrent.FutureTask;

public class FutureTaskAdapter<T> extends FutureTask {

    private Task<T> task;
    public FutureTaskAdapter(Task<T> task)
    {
        super(task);
        this.task = task;

    }

    public int getPriority()
    {
        return this.task.getPriority();
    }
}
