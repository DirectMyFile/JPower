package jpower.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Executes Tasks in a Thread. Can be used like ThreadPools.
 */
public class Worker implements Runnable {

    private boolean isWorking;
    private Thread thread;
    private boolean stop = false;

    public LinkedBlockingQueue<Task> queue;

    /**
     * Create a Worker with the Default Queue Size
     */
    public Worker() {
        this.queue = new LinkedBlockingQueue<Task>();
    }

    /**
     * Create a Worker with the specified Task Queue Size
     * @param queueSize Task Queue Size
     */
    public Worker(int queueSize) {
        this.queue = new LinkedBlockingQueue<Task>(queueSize);
    }

    /**
     * Adds a Task to the Queue
     * @param task task to add
     */
    public void addTask(Task task) {
        queue.add(task);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Task task = queue.poll(250, TimeUnit.MILLISECONDS);
                if (task == null)
                    return;
                if (!task.isCanceled()) {
                    isWorking = true;
                    task.execute();
                    isWorking = false;
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * Gets if the Worker is currently working.
     * @return Is Worker Working
     */
    public boolean isWorking() {
        return isWorking;
    }

    /**
     * Stops the Worker (Takes no more than 250 milliseconds)
     */
    public void stop() {
        this.stop = true;
    }

    /**
     * Starts the Worker
     */
    public void start() {
        if (thread == null)
            thread = new Thread(this);
        thread.start();
    }

    /**
     * Gets the remaining capacity of the Task Queue
     * @return Remaining Capacity
     */
    public int remainingCapacity() {
        return queue.remainingCapacity();
    }

    /**
     * Remove a Task from the Queue
     * @param task task
     * @return if it was removed
     */
    public boolean removeTask(Task task) {
        return queue.remove(task);
    }

    /**
     * The current size of the Task Queue
     * @return Size
     */
    public int size() {
        return queue.size();
    }

    /**
     * Wait for the task queue to be empty
     */
    public void waitFor() {
        while (isWorking() || !queue.isEmpty());
    }
}
