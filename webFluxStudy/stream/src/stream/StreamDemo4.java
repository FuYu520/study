package stream;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 终止操作
 * @author Administrator
 *
 */
public class StreamDemo4 {

	public static void main(String[] args) {
		
		String str = "my name is 007 ";
		//非短路操作
		//foreach/foreachOrdered 区别
		str.chars().parallel().forEach(i -> System.out.println((char)i));
		//foreachOrdered 用于并行流保证顺序
		str.chars().parallel().forEachOrdered(i -> System.out.println((char)i));
		//collect/toArray
		//收集到list
		List<String> list = Stream.of(str).collect(Collectors.toList());
		System.out.println(list);
		//reduce:使用reduce拼接字符串  用|拼接
		//Optional:选项 解决空指针
		Optional<String> letters = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
		
		System.out.println(letters.orElse(""));
		//直接返回 String
		String lestr = Stream.of(str.split(" ")).reduce("",(s1, s2) -> s1 + "|" + s2);
		//单词总长度
		Integer length = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (s1, s2) -> s1+ s2);
		System.out.println(length);
		//min/max/count
		//最大长度单词
		Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
		System.out.println(max.get());
		//短路操作
		//findFirst/findAny
		//短路操作，取得一个结果
		OptionalInt findFirst = new Random().ints().findFirst();
		System.out.println(findFirst.getAsInt());
		//allMatch/anyMatch/noneMatch
		
		

	}

}
