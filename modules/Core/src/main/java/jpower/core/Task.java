package jpower.core;

public abstract class Task implements Runnable {

    private boolean canceled;

    @Override
    public void run() {
        execute();
    }

    public abstract void execute();

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
