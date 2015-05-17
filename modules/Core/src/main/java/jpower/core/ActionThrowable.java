package jpower.core;

@SuppressWarnings("RedundantThrows")
@FunctionalInterface
public interface ActionThrowable {
   public void run() throws Throwable;
}
