package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream 流创建
 * @author Administrator
 *
 */
public class StreamDemo2 {

	public static void main(String[] args) {

		//集合：Collection.stream/parallelStream
		List<String> list = new ArrayList<>();
		//从集合创建
		list.stream();
		list.parallelStream();
		//数组：Arrays.stream
		Arrays.stream(new int[]{1,2,3});
		
		/**
		 * 数字：IntStream/LongStream.range/rangeClosed 不需要自动装箱，性能高点
		 * Random.ints/longs/doubles
		 */
		IntStream.of(1,2,3);
		//一到9
		IntStream.range(1, 10).forEach(System.out::print);
		//一到10
		IntStream.rangeClosed(1, 10).forEach(System.out::print);;
		Random r = new Random();
		//无限流
		//r.ints().forEach(System.out::print);
		//找到10个随机数就停止
		r.ints().limit(10);
		
		//自己创建：stream.generate/iterate
		Stream.generate(() -> r.nextInt()).limit(10);
	}

}
