package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream ������
 * @author Administrator
 *
 */
public class StreamDemo2 {

	public static void main(String[] args) {

		//���ϣ�Collection.stream/parallelStream
		List<String> list = new ArrayList<>();
		//�Ӽ��ϴ���
		list.stream();
		list.parallelStream();
		//���飺Arrays.stream
		Arrays.stream(new int[]{1,2,3});
		
		/**
		 * ���֣�IntStream/LongStream.range/rangeClosed ����Ҫ�Զ�װ�䣬���ܸߵ�
		 * Random.ints/longs/doubles
		 */
		IntStream.of(1,2,3);
		//һ��9
		IntStream.range(1, 10).forEach(System.out::print);
		//һ��10
		IntStream.rangeClosed(1, 10).forEach(System.out::print);;
		Random r = new Random();
		//������
		//r.ints().forEach(System.out::print);
		//�ҵ�10���������ֹͣ
		r.ints().limit(10);
		
		//�Լ�������stream.generate/iterate
		Stream.generate(() -> r.nextInt()).limit(10);
	}

}
