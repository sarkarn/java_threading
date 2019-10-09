package nns.thread.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Perform Possible asynchronous(non-blocking operation
 * and trigger dependent computations which could also be
 * asynchronous
 */
public class CompletableFutureExample {
	
	public static void main(String args[]) {
		
		ExecutorService cpuBound = Executors.newFixedThreadPool(10);
		ExecutorService ioBound = Executors.newFixedThreadPool(10);
		
		CompletableFuture.supplyAsync( () -> getOrder(),cpuBound)
		                 .thenAcceptAsync(order -> enrichOrder(),ioBound)
		                 .thenApplyAsync(order -> performPayment(),cpuBound )
		                 .thenApplyAsync(order -> dispatchOrder(), cpuBound)
		                 .thenApplyAsync(order -> sendEmail(),cpuBound);
		                         
		
		
		
	}
	
	public static Order getOrder() {
		return new Order();
	}
	
	public static Order enrichOrder() {
		return new Order();
	}
	
	public static Order performPayment() {
		return new Order();
	}
	
	public static Order dispatchOrder() {
		return new Order();
	}
	
	public static Order sendEmail() {
		return new Order();
	}
	
}
