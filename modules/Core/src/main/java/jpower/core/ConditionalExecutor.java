package jpower.core;

public class ConditionalExecutor {

    private Runnable task;

    public ConditionalExecutor(Runnable task) {
        this.task = task;
    }

    public void until(Condition condition) {
        while (!condition.check()) {
            task.run();
        }
    }

    public void when(Condition condition) {
        while (condition.check()) {
            task.run();
        }
    }
}
