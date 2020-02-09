package jdk9;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * Processor 需要继承SubmissionPublisher并实现Processor接口
 * 
 * 输入源数据integer，过滤掉小于0的，然后转换成字符串发布出去
 * 
 * @author Administrator
 *
 */

class MyProcessor extends SubmissionPublisher<String> implements Processor<Integer, String> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		// 保存订阅关系，需要用它来给发布者响应
		this.subscription = subscription;

		// 请求一个数据
		this.subscription.request(1);
	}

	@Override
	public void onNext(Integer item) {
		// 接受到一个数据，处理
		System.out.println("处理器接受到数据：" + item);

		// 过滤掉小于0的，然后发布出去
		if (item > 0) {
			this.submit("转换后的数据：" + item);
		}

		// 处理完调用request再请求一个数据
		// 关键 需要数据
		this.subscription.request(1);

		// 或者 已经达到了目标，调用cancel告诉发布者不再接受数据了
		// this.subscription.cancel();

	}

	@Override
	public void onError(Throwable throwable) {
		// 出现了异常(例如数据处理的时候产生了异常)
		throwable.printStackTrace();

		// 可以告诉发布者，后面不在接受数据
		this.subscription.cancel();

	}

	@Override
	public void onComplete() {
		// 处理完毕 publisher.close();后触发
		System.out.println("处理完了！");
		// 关闭发布者
		this.close();

	}

}

public class FlowDemo2 {

	public static void main(String[] args) {
		// 1，定义发布者，发布的数据类型是Integer
		// 直接使用jdk自带的submissionPublisher
		SubmissionPublisher<Integer> publiser = new SubmissionPublisher<>();

		// 2，定义处理器，对数据进行过滤，并转换为String类型
		MyProcessor processor = new MyProcessor();

		// 3，发布者和处理器 建立订阅关系
		publiser.subscribe(processor);

		// 4，定义最终订阅者，消费string类型数据
		Subscriber<String> subscriber = new Subscriber<String>() {

			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription subscription) {
				// 保存订阅关系, 需要用它来给发布者响应
				this.subscription = subscription;

				// 请求一个数据
				this.subscription.request(1);
			}

			@Override
			public void onNext(String item) {
				// 接受到一个数据, 处理
				System.out.println("接受到数据: " + item);

				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 处理完调用request再请求一个数据
				this.subscription.request(1);

				// 或者 已经达到了目标, 调用cancel告诉发布者不再接受数据了
				// this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				// 出现了异常(例如处理数据的时候产生了异常)
				throwable.printStackTrace();

				// 我们可以告诉发布者, 后面不接受数据了
				this.subscription.cancel();
			}

			@Override
			public void onComplete() {
				// 全部数据处理完了(发布者关闭了)
				System.out.println("处理完了!");
			}
		};

		// 5，处理器和最终订阅者建立订阅关系
		processor.subscribe(subscriber);

		// 6，生产数据，并发布
		// 这里忽略数据生产过程
		//submit 是一个缓冲方法
		//Subscriber 缓冲池满了（256），就会停下来，不会去生成数据
		for (int i = 0; i < 1000; i++) {
			System.out.println("生成数据：" + i);
			publiser.submit(i);
		}

		// 7，结束后，关闭f发布者
		// 正式环境，应该放finally 或者使用try-resouce确保关闭
		publiser.close();

		// 主线程延迟停止，否则数据m没有消费就退出
		try {
			Thread.currentThread().join(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
