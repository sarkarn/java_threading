package nns.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class StoppingThreadAfterTemMinute {

	public static void main(String args[]) throws Exception {
		Task1 task1 = new Task1();
		Thread t1 = new Thread(task1);
		t1.start();
		//After 10 minutes call
		task1.keepRunning = false;
		
		
		Task2 task2 = new Task2();
		Thread t2 = new Thread(task2);
		t2.start();
		//After 10 minutes call
		task2.keepRunning.set(false);
		
		Task3 task3 = new Task3();
		Thread t3 = new Thread(task3);
		t3.start();
		//After 10 minutes using Scheduler Servive
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.schedule(() -> t3.interrupt(), 10, TimeUnit.MINUTES);

	}

	public static class Task1 implements Runnable {

		public volatile boolean keepRunning = true;

		public void run() {
			while (keepRunning) {
				System.out.println("I am running");
			}
		}
	}
	
	
	public static class Task2 implements Runnable {

		AtomicBoolean keepRunning = new AtomicBoolean(true);

		public void run() {
			while (keepRunning.get()==true) {
				System.out.println("I am running");
			}
		}
	}
	
	
	public static class Task3 implements Runnable {

		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("I am running");
			}
		}
	}

}
