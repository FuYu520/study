package lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionDemo {
	public static void main(String[] args) {
		/**
		 * 断言函数：Predicate 输入参数 T ，返回类型boolean 判断i是否大于0
		 * IntPredicate
		 */
		Predicate<Integer> predicate = i -> i > 0;
		System.out.println(predicate.test(-9));

		/**
		 * 消费一个数据：Consumer<T> 输入参数 T，返回类型 /
		 * IntConsumer
		 */
		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("输入的数据");

		/**
		 * Function<T, R>: 输入参数类型T，返回参数类型R
		 */

		/**
		 * Supplier<T>: 输入参数 / ，输出参数 T 提供一个数据
		 */

		/**
		 * UnaryOperator<T>: 输入参数类型 T，返回参数类型 T (一元函数)
		 */

		/**
		 * BiFunction<T, U, R>: 输入参数(T, U)，返回类型参数 R
		 */

		/**
		 * BinaryOperator<T>: 输入参数(T, T)，返回类型参数 T (二元函数)
		 */
	}
}
