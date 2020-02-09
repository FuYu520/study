package stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 并行流
 * 
 * @author Administrator
 *
 */
public class StreamDemo5 {

	public static void main(String[] args) {
		// IntStream.range(1, 100).peek(StreamDemo5::debug).count();
		// 并行操作
		// IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

		// 并行加串行操作
		// 失败：以最后一次调用为准
//		IntStream.range(1, 100)
//		.parallel().peek(StreamDemo5::debug)
//		.sequential().peek(StreamDemo5::debug2)
//		.count();

		// 并行流 使用的线程池 ForkJoinPool.commonPool-worker
		// 默认线程数是当前机器上的cpu个数
		// 使用属性修改默认线程数
		//System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");

		//IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

		
		//使用自己的线程池，不使用默认线程池，防止任务被阻塞
		//ForkJoinPool-1
		ForkJoinPool pool = new ForkJoinPool(20);
		pool.submit(() -> IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count());
		pool.shutdown();
		
		
		synchronized (pool) {
			try {
				pool.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void debug(int i) {
		System.out.println(Thread.currentThread().getName() + "   debug    " + i);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void debug2(int i) {
		System.err.println("debug2" + i);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
