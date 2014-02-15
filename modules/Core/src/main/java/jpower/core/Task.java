package jpower.core;

public interface Task extends Runnable {

    Wrapper<Boolean> canceled = new Wrapper<>(false);

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
        return canceled.get();
    }

    /**
     * Cancels the Task
     */
    public default void cancel() {
        canceled.set(true);
    }
}
