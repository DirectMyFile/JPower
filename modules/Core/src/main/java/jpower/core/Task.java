package jpower.core;

import jpower.core.internal.CancelStateTracker;

public interface Task extends Runnable {

    /**
     * Used to run in Threads
     */
    @Override
    public default void run() {
        execute();
    }

    /**
     * Executes this Task
     */
    public void execute();

    /**
     * Checks if this task is canceled
     *
     * @return Task is Canceled
     */
    public default boolean isCanceled() {
        return CancelStateTracker.isCanceled(this);
    }

    /**
     * Cancels the Task
     */
    public default void cancel() {
        CancelStateTracker.setCanceled(this, true);
    }

    /**
     * Actives the Task if it is canceled
     */
    public default void activate() {
        CancelStateTracker.setCanceled(this, false);
    }
}
