package nns.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Phaser can act either as a cyclicbarrier or countdownlatch.
 * 
 * @author naren
 *
 */

public class PhaserExample {

public static void main(String args[]) {
		
		ExecutorService service = Executors.newFixedThreadPool(4);
		Phaser phaser = new Phaser(3);
		
		new Thread(new MyPhaser(phaser)).start();
		new Thread(new MyPhaser(phaser)).start();
		new Thread(new MyPhaser(phaser)).start();
		
		phaser.awaitAdvance(1);
	    
		System.out.println("All Dependent Service initiated");
		
	}
	
	public static class MyPhaser implements Runnable {

		Phaser phaser;
		
		public MyPhaser(Phaser phaser) {
			this.phaser = phaser;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					//start up task
					phaser.arrive();
					
					//continue with other task
				}catch(Exception ex) {}
			}
			
		}
		
	}

}
