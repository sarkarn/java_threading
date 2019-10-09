package nns.thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * A semaphore controls access to a shared resource through the use of a counter. 
 * If the counter is greater than zero, then access is allowed. If it is zero, 
 * then access is denied. What the counter is counting are permits that allow access to the shared resource. Thus, to access the resource, a thread must be granted a permit from the semaphore.
 * Working of semaphore.
 * In general, to use a semaphore, the thread that wants access to the shared resource tries to acquire a permit.
 * If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s count to be decremented.
 * Otherwise, the thread will be blocked until a permit can be acquired.
 * When the thread no longer needs an access to the shared resource, it releases the permit, which causes the semaphore’s count to be incremented.
 * If there is another thread waiting for a permit, then that thread will acquire a permit at that time
 * @author naren
 *
 */

public class SemaPhoreExample {
	
	public static void main(String args[]) throws InterruptedException{
		
		Semaphore semaphore = new Semaphore(3);
		ExecutorService service = Executors.newFixedThreadPool(50);
		
		IntStream.of(1000).forEach(i -> service.execute(new Task(semaphore)));
		
	}
	
	static class Task implements Runnable {

		Semaphore semaphore;
		public Task(Semaphore semaphore) {
			this.semaphore = semaphore;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//some processing
			try {
			semaphore.acquire();
			}catch(Exception ex) {}
			
			//IO call to some slow service
			
			semaphore.release();
			
			
		}
		
	}

}
