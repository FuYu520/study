package stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ������
 * 
 * @author Administrator
 *
 */
public class StreamDemo5 {

	public static void main(String[] args) {
		// IntStream.range(1, 100).peek(StreamDemo5::debug).count();
		// ���в���
		// IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

		// ���мӴ��в���
		// ʧ�ܣ������һ�ε���Ϊ׼
//		IntStream.range(1, 100)
//		.parallel().peek(StreamDemo5::debug)
//		.sequential().peek(StreamDemo5::debug2)
//		.count();

		// ������ ʹ�õ��̳߳� ForkJoinPool.commonPool-worker
		// Ĭ���߳����ǵ�ǰ�����ϵ�cpu����
		// ʹ�������޸�Ĭ���߳���
		//System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");

		//IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

		
		//ʹ���Լ����̳߳أ���ʹ��Ĭ���̳߳أ���ֹ��������
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
