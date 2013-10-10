package jpower.core;

public class MultiTask extends Task {

    private Task[] tasks;

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
