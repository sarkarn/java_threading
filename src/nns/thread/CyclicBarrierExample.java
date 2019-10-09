package nns.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier is used to make threads wait for each other. 
 * It is used when different threads process a part of computation
 *  and when all threads have completed the execution, 
 *  the result needs to be combined in the parent thread
 * @author naren
 *
 */

public class CyclicBarrierExample {
	
	public static void main(String args[]) {
		
		ExecutorService service = Executors.newFixedThreadPool(4);
		CyclicBarrier barrier = new CyclicBarrier(3);
		
		new Thread(new MyCyClickBarrier(barrier)).start();
		new Thread(new MyCyClickBarrier(barrier)).start();
		new Thread(new MyCyClickBarrier(barrier)).start();
		
	    
		
		
	}
	
	public static class MyCyClickBarrier implements Runnable {

		CyclicBarrier barrier;
		
		public MyCyClickBarrier(CyclicBarrier barrier) {
			this.barrier = barrier;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					barrier.await();
				}catch(Exception ex) {}
			}
			
			//Send message to corresponding system
			
		}
		
	}

}
