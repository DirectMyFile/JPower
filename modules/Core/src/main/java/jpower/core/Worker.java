package jpower.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

    private boolean isWorking;
    private Thread thread;
    private boolean stop = false;

    public LinkedBlockingQueue<Task> queue;

    public Worker() {
        this.queue = new LinkedBlockingQueue<Task>();
    }

    public Worker(int queueSize) {
        this.queue = new LinkedBlockingQueue<Task>(queueSize);
    }

    public void addTask(Task task) {
        queue.add(task);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Task task = queue.poll(250, TimeUnit.MILLISECONDS);
                if (!task.isCanceled()) {
                    isWorking = true;
                    task.execute();
                    isWorking = false;
                }
            } catch (InterruptedException ignored) {

            }
        }
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void stop() {
        this.stop = true;
    }

    public void start() {
        if (thread==null) thread = new Thread(this);
        thread.start();
    }

    public int remainingCapacity() {
        return queue.remainingCapacity();
    }

    public boolean remove(Task task) {
        return queue.remove(task);
    }

    public int size() {
        return queue.size();
    }
}
