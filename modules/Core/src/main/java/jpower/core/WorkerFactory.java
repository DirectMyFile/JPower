package jpower.core;

public class WorkerFactory implements Factory<Worker> {
    private final Worker worker;

    public WorkerFactory(Worker worker) {
        this.worker = worker;
    }

    @Override
    public Worker create() {
        return new Worker(worker.queue.size());
    }
}
