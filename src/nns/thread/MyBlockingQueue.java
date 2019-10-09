package nns.thread;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class MyBlockingQueue<E> {
	Queue<E> queue;
	int max=16;
	private ReentrantLock lock = new ReentrantLock(true);
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();
	
	
	public MyBlockingQueue(int size) {
		queue = new LinkedList<>();
		this.max = size;
	}
	
	public void put(E e) {
		lock.lock();
		try {
			while(queue.size() == max) {
				notEmpty.await();
			}
			queue.add(e);
			notEmpty.signalAll();
		}catch(Exception ex) {}
		finally {
			lock.unlock();
		}
	}
	
	
	public E take() {
		lock.lock();
		E item = null;
		try {
			while(queue.size()==0) {
				notFull.await();
			}
		    item = queue.remove();
		    notFull.signalAll();
			return item;
		}catch(Exception ex){}
		finally {
			lock.unlock();
		}
		
		return item;
	}

}
