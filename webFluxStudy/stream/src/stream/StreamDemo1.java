package stream;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * stream:高级迭代器，不会存放数据
 * 外部迭代：
 * 内部迭代：
 * @author Administrator
 *
 */
public class StreamDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3};
		//外部迭代
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		System.out.println(sum);
		
		
		//内部迭代 stream流
		//of 理解成new
		int sum2 = IntStream.of(nums).sum();
		System.out.println(sum2);
		
		
		//内部迭代，惰性求值，并行，短路操作（不需要结果处理完，取出符合条件的）
		
		
		/**
		 * 惰性求值  
		 * map:中间操作，返回stream的操作
		 * sum:终止操作
		 */
		
		int sum3 = IntStream.of(nums).map(StreamDemo1::doubleNume).sum();
		System.out.println("结果为"+sum3);
		
		System.out.println("惰性求值就是终止操作没调用，中间操作不执行");
		//doubleNume 里的输出语句不会执行
		IntStream.of(nums).map(StreamDemo1::doubleNume);
		
	}
	
	public static int doubleNume(int i) {
		System.out.println("执行了乘以2");
		return i*2;
	}

}
