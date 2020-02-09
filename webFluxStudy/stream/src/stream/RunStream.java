package stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 所有操作是链式调用，一个元素只迭代一次 每一个中间操作返回一个新的流，流里有一个属性sourceStage 
 * 
 * 指向同一个地方就是Head
 * 
 * Head指向nextStage -> nextStage -> nextStage -> ... -> null
 * 
 * 有状态操作会把无状态操作截断，单独处理
 * 
 * 并行环境下，有状态的中间操作不一定能并行操作
 * 
 * parallel/sequetial 这2个操作也在中间操作（也是返回stream）
 * 不创建流，只修改Head的并行标志
 * 
 * @author Administrator
 *
 */

public class RunStream {
	public static void main(String[] args) {
		Random random = new Random();
		// 随机产生数据
		Stream<Integer> stream = Stream.generate(() -> random.nextInt())
				// 产生500个随机数
				.limit(500)
				// 第一个无状态操作
				.peek(s -> print("peek：" + s))
				// 第二个无状态操作
				.filter(s -> {
					print("filter：" + s);
					return s > 10000;
				})
				//有状态操作
				.sorted((i1, i2) -> {
					print("排序：" + i1 + "," + i2);
					return i1.compareTo(i2);
				})
				//无状态操作
				.peek(s -> print("peek2：" + s)).parallel();
		// 终止操作
		stream.count();

	}

	public static void print(String str) {
		//System.out.println(str);
		System.out.println(Thread.currentThread().getName() + ">" + str);
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
