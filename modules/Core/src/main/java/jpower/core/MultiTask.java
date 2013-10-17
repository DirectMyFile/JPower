package jpower.core;

/**
 * Combines multiple tasks into One
 */
public class MultiTask extends Task {

    private Task[] tasks;

    /**
     * Creates a MultiTask
     * @param tasks Tasks to Combine
     */
    public MultiTask(Task... tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        for (Task task : tasks) {
            task.execute();
        }
    }
}
