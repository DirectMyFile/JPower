package jpower.core;

/**
 * Clones a Task, so it may be created multiple times.
 */
public class TaskFactory extends Factory<Task> {

    private final Task original;

    public TaskFactory(Task original) {
        this.original = original;
    }

    @Override
    public Task newInstance() {
        return new Task() {
            @Override
            public void execute() {
                original.execute();
            }
        };
    }
}
