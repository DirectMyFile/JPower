package jpower.core;

/**
 * Clones a Task, so it may be created multiple times.
 */
public class TaskFactory implements Factory<Task> {

    private final Task original;

    public TaskFactory(Task original) {
        this.original = original;
    }

    @Override
    public Task newInstance() {
        return original::execute;
    }
}
