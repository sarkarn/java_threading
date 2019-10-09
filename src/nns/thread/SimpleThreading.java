package nns.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService service = Executors.newFixedThreadPool(15);
		for(int i=0;i<100; i++) {
			service.execute(() -> System.out.println("Executing Task + "));
		}
		
		
	}

}
