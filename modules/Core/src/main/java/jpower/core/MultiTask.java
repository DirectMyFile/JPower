package jpower.core;

import java.util.Collection;

/**
 * Combines multiple tasks into One
 */
public class MultiTask extends Task {

    private final Task[] tasks;

    /**
     * Creates a MultiTask
     *
     * @param tasks Tasks to Combine
     */
    public MultiTask(Task... tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a MultiTask
     *
     * @param tasks Tasks to Combine
     */
    public MultiTask(Collection<Task> tasks) {
        this.tasks = tasks.toArray(new Task[tasks.size()]);
    }

    @Override
    public void execute() {
        for (Task task : tasks) {
            task.execute();
        }
    }
}
