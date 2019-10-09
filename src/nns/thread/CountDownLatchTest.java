package nns.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 * CountDownLatch in Java is a type of synchronizer which allows one Thread to wait for one or more
 *  Thread s before it starts processing. CountDownLatch works on latch principle,
 *  thread will wait until gate is open. One thread waits for n number of 
 *  threads specified while creating CountDownLatch 
 */
public class CountDownLatchTest {
	
	public static void main(String args[]) throws Exception{
		
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		CountDownLatch latch = new CountDownLatch(3);
		service.submit(new DependentService(latch));
		service.submit(new DependentService(latch));
		service.submit(new DependentService(latch));
		
		latch.await();
		
		System.out.println("All Dependent Service initiated");
		
		//Program initialized performed other operations
	}
	
	
	public static class DependentService implements Runnable {

		private CountDownLatch latch = null;
		
		public DependentService(CountDownLatch latch) {
			this.latch = latch;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//Start up task do here
			latch.countDown();
			//continue with other work
		}
		
	}

}
