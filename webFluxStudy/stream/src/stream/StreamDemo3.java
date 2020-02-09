package stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * stream 流编程-中间操作 都是返回stream
 * 
 * @author Administrator
 *
 */
public class StreamDemo3 {

	public static void main(String[] args) {

		String str = "my name is 007";

		// 无状态操作
		// map/mapToXXX 对象上的属性
		// 把 str 拆分成一个个的 s 取出 s的长度 过滤 进行打印
		Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(s -> s.length()).forEach(System.out::println);
		// flatMap/flatMapToXXX 对象上的属性（集合(对象)）
		//A 元素下B属性（集合） ，最终得到所有的A元素里的B元素集合
		//intStream/LongStream并不是stream的子类，要进行装箱boxed
		//字符串 数字
		Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(System.out::println);
		//字符串 字符
		Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i ->System.out.println((char)i.intValue()));
		// filter过滤器
		// peek 用于Debug 是中间操作，和foreach终止操作 参数消费者  
		Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);
		
		// unordered 并行流操作涉及

		// 有状态操作，当前结果需要依赖其他元素 ，如：排序，需要所有结果计算完毕
		// distinct 去重
		// sorted 排序
		// limit/skip limit限流/skip跳过
		//limit主要用于无限流
		new Random().ints(10).filter(s -> s >100 && s< 1000).forEach(System.out::println);
	}

}
