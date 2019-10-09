package nns.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
	
	ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
	
	
	public void readSomething() {
		
		readLock.lock();
		try {
			System.out.println("reading");
		}finally {
			readLock.unlock();
		}
	}
	
	
	public void writeSomething() {
		writeLock.lock();
		try {
			System.out.println("Writing");
		}finally {
			writeLock.unlock();
		}
	}
	
	
	public static void main(String args[]) {
		ReadWriteLock obj = new ReadWriteLock();
		new Thread(() -> obj.readSomething()).start();
		new Thread(() -> obj.readSomething()).start();
		new Thread(() -> obj.readSomething()).start();
		
		new Thread(() -> obj.writeSomething()).start();
		new Thread(() -> obj.writeSomething()).start();
		new Thread(() -> obj.writeSomething()).start();
		new Thread(() -> obj.writeSomething()).start();
	}
	

}
