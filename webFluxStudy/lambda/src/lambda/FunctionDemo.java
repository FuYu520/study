package lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionDemo {
	public static void main(String[] args) {
		/**
		 * ���Ժ�����Predicate ������� T ����������boolean �ж�i�Ƿ����0
		 * IntPredicate
		 */
		Predicate<Integer> predicate = i -> i > 0;
		System.out.println(predicate.test(-9));

		/**
		 * ����һ�����ݣ�Consumer<T> ������� T���������� /
		 * IntConsumer
		 */
		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("���������");

		/**
		 * Function<T, R>: �����������T�����ز�������R
		 */

		/**
		 * Supplier<T>: ������� / ��������� T �ṩһ������
		 */

		/**
		 * UnaryOperator<T>: ����������� T�����ز������� T (һԪ����)
		 */

		/**
		 * BiFunction<T, U, R>: �������(T, U)���������Ͳ��� R
		 */

		/**
		 * BinaryOperator<T>: �������(T, T)���������Ͳ��� T (��Ԫ����)
		 */
	}
}
