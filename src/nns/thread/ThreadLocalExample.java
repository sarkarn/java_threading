package nns.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {

	private static ExecutorService service = Executors.newFixedThreadPool(10);
	private static ThreadLocal<SimpleDateFormat>  threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
	
	public static void main(String args[]) throws Exception {
		
		for(int i=0; i<1000; i++) {
			int id =i;
			service.submit( () -> {
				String birthDate = new ThreadLocalExample().birthDate(id);
				System.out.println(birthDate);
			});
		}
	}
	
	public String birthDate(int userId) {
		
		final SimpleDateFormat  df = threadLocal.get();
		return df.format(new Date());
		
	}
}
