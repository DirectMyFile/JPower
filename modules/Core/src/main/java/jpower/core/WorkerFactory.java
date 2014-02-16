package jpower.core;

public class WorkerFactory implements Factory<Worker> {
    private final int size;

    public WorkerFactory(int size) {
        this.size = size;
    }

    public WorkerFactory(Worker worker) {
        this(worker.queue.size());
    }

    @Override
    public Worker create() {
        return new Worker(size);
    }
}
