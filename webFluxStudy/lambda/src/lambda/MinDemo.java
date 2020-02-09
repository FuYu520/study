package lambda;

import java.util.stream.IntStream;

public class MinDemo {
	public static void main(String[] args) {
		int[] nums = {33,34,45,76,21};
		//命令式编程
		int min = Integer.MAX_VALUE;
		for (int i : nums) {
			if(i < min) {
				min = i;
			}
		}
		System.out.println(min);
		//jdk8
		//parallel() 并行流
		int min2 = IntStream.of(nums).parallel().min().getAsInt();
		System.out.println(min2);
	}
}
