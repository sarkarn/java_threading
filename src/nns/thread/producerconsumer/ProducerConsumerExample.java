package nns.thread.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample {

	public static void main(String[] args) throws Exception {
		
		BlockingQueue<Item> bqueue = new ArrayBlockingQueue<>(10);
		// TODO Auto-generated method stub
		
		Runnable producer = () -> {
			while(true) {
				try {
					bqueue.put(createItem());
					
				}catch(Exception ex) {}
			}
		};
		
		new Thread(producer).start();
		new Thread(producer).start();
		
		
		Runnable consumer = () -> {
			while(true) {
				try {
					bqueue.take();
				}catch(Exception ex) {}
			}
		};
		
		
		new Thread(consumer).start();
		new Thread(consumer).start();
		
		Thread.sleep(10000);
		

	}
	
	
	public static Item createItem() {
		return new Item();
	}

}
