package jpower.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

    private boolean isWorking;
    private Thread thread;
    private boolean stop = false;

    public LinkedBlockingQueue<Task> tasks = new LinkedBlockingQueue<Task>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Task task = tasks.poll(250, TimeUnit.MILLISECONDS);
                isWorking = true;
                task.execute();
                isWorking = false;
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
}
