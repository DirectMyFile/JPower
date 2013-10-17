package jpower.core;

public abstract class Task implements Runnable {

    private boolean canceled;

    /**
     * Used to run in Threads
     */
    @Override
    public void run() {
        execute();
    }

    /**
     * Executes this Task
     */
    public abstract void execute();

    /**
     * Is this task Cancelled
     * @return true if canceled
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Sets if the Task is Canceled
     * @param canceled task canceled
     */
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
