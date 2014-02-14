package jpower.core;

import java.util.List;

/**
 * Combines multiple tasks into One
 */
public class MultiTask extends Task {

    private Task[] tasks;

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
    public MultiTask(List<Task> tasks) {
        this.tasks = (Task[]) tasks.toArray();
    }

    @Override
    public void execute() {
        for (Task task : tasks) {
            task.execute();
        }
    }
}
