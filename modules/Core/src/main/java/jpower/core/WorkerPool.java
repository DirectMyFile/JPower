package jpower.core;

import java.util.ArrayList;
import java.util.List;

public class WorkerPool {
    private List<Worker> workers = new ArrayList<Worker>();

    private int size;

    public WorkerPool(int size) {
        this.size = size;
    }

    public WorkerPool() {
        this.size = 50;
    }

    public boolean submit(Task task) {
        Worker worker = pullWorker();
        if (worker==null) {
            return false;
        }
        worker.addTask(task);
        return true;
    }

    public Worker pullWorker() {
        if (workers.isEmpty()) {
            Worker worker = newWorker();
            workers.add(worker);
            return worker;
        }
        for (Worker worker : workers) {
            if (!worker.isWorking()) {
                return worker;
            }
        }
        return newWorker();
    }

    private Worker newWorker() {
        if (workers.size()==size) {
            return null;
        }
        Worker worker = new Worker();
        worker.start();
        return worker;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void stopWorkers() {
        List<Worker> temp = new ArrayList<Worker>(workers);
        for (Worker worker : temp) {
            worker.stop();
            while (worker.isWorking());
            workers.remove(worker);
        }
    }

    public int currentWorkers() {
        return workers.size();
    }
}
