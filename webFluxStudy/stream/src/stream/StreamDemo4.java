package stream;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ��ֹ����
 * @author Administrator
 *
 */
public class StreamDemo4 {

	public static void main(String[] args) {
		
		String str = "my name is 007 ";
		//�Ƕ�·����
		//foreach/foreachOrdered ����
		str.chars().parallel().forEach(i -> System.out.println((char)i));
		//foreachOrdered ���ڲ�������֤˳��
		str.chars().parallel().forEachOrdered(i -> System.out.println((char)i));
		//collect/toArray
		//�ռ���list
		List<String> list = Stream.of(str).collect(Collectors.toList());
		System.out.println(list);
		//reduce:ʹ��reduceƴ���ַ���  ��|ƴ��
		//Optional:ѡ�� �����ָ��
		Optional<String> letters = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
		
		System.out.println(letters.orElse(""));
		//ֱ�ӷ��� String
		String lestr = Stream.of(str.split(" ")).reduce("",(s1, s2) -> s1 + "|" + s2);
		//�����ܳ���
		Integer length = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (s1, s2) -> s1+ s2);
		System.out.println(length);
		//min/max/count
		//��󳤶ȵ���
		Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
		System.out.println(max.get());
		//��·����
		//findFirst/findAny
		//��·������ȡ��һ�����
		OptionalInt findFirst = new Random().ints().findFirst();
		System.out.println(findFirst.getAsInt());
		//allMatch/anyMatch/noneMatch
		
		

	}

}
