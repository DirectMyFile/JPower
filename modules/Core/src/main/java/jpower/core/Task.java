package jpower.core;

public abstract class Task implements Runnable {

    @Override
    public void run() {
        execute();
    }

    public abstract void execute();
}
