package jpower.core;

import jpower.core.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a Collection of Workers
 */
public class WorkerPool
{
   private final List<Worker> workers = new ArrayList<>();

   private final int size;

   /**
    * Creates a Worker Pool of specified size
    *
    * @param size size of pool
    */
   public WorkerPool(int size)
   {
      this.size = size;
   }

   /**
    * Creates a Worker Pool of the Default Size (50 Workers)
    */
   public WorkerPool()
   {
      size = 50;
   }

   /**
    * Submit a Task to the Queue
    *
    * @param task Task
    * @return Was added to Queue
    */
   public boolean submit(Task task)
   {
      Worker worker = pullWorker();
      if (worker == null)
      {
         return false;
      }
      worker.addTask(task);
      return true;
   }

   /**
    * Retrieves an Open Worker, or creates one, if there is not one available.
    * It does however follow the Size Limit.
    *
    * @return A Worker if Found, else Null
    */
   Worker pullWorker()
   {
      if (workers.isEmpty())
      {
         Worker worker = newWorker();
         workers.add(worker);
         return worker;
      }
      for (Worker worker : workers)
      {
         if (!worker.isWorking())
         {
            return worker;
         }
      }
      return newWorker();
   }

   /**
    * Creates a New Worker
    *
    * @return Worker
    */
   private Worker newWorker()
   {
      if (workers.size() == size)
      {
         return null;
      }
      Worker worker = new Worker();
      worker.start();
      return worker;
   }

   /**
    * Gets a list of all Workers
    *
    * @return Worker List
    */
   public List<Worker> getWorkers()
   {
      return workers;
   }

   /**
    * Stops all Workers
    */
   public void stopWorkers()
   {
      Iterable<Worker> temp = new ArrayList<>(workers);
      temp.forEach(worker -> {
         worker.stop();
         while (worker.isWorking())
         {
            ThreadUtils.sleep(1);
         }
         workers.remove(worker);
      });
   }

   /**
    * Gets Current amount of Workers
    *
    * @return Amount of Workers
    */
   public int currentWorkers()
   {
      return workers.size();
   }

   /**
    * Waits for all Workers to be Open
    */
   public void waitForAll()
   {
      workers.forEach(Worker::waitFor);
   }
}
