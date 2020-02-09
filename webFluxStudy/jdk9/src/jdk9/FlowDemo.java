package jdk9;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {
	public static void main(String[] args) {
		//1，定义发布者，发布的数据类型是Integer
		//直接使用JDK自带的submissionPublisher，它实现了Publisher接口
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		
		//2，定义订阅者
		Subscriber<Integer> subscriber = new Subscriber<Integer>() {
			
			private Subscription subscription;
			
			/**
			 * 建立订阅关系
			 */
			@Override
			public void onSubscribe(Subscription subscription) {
				//保存订阅关系，需要它来给发布者响应
				this.subscription = subscription;
				
				//请求一个数据
				this.subscription.request(1);
			}

			@Override
			public void onNext(Integer item) {
				//接受到一个数据，处理
				System.out.println("接受到数据：" + item);
				
				//处理完调用request再请求一个数据
				//关键 需要数据
				this.subscription.request(1);
				
				//或者 已经达到了目标，调用cancel告诉发布者不再接受数据了
				//this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				// 出现了异常(例如数据处理的时候产生了异常)
				throwable.printStackTrace();
				
				//可以告诉发布者，后面不在接受数据
				//this.subscription.cancel();
			}

			@Override
			public void onComplete() {
				// 处理完毕  publisher.close();后触发
				System.out.println("处理完了！");
				
			}
		};
		
		//3，发布者和订阅者建立订阅关系
		publisher.subscribe(subscriber);
		
		//4，生产数据，并发布
		//这里忽略生产过程
		int data = 111;
		publisher.submit(data);
		publisher.submit(data);
		publisher.submit(data);
		publisher.submit(data);
		
		//5，结束后，guan'bi发布者
		//正式环境下应该放入finally或使用try-resouce确保关闭
		publisher.close();
		
		
		try {
			//主线程延迟停止，否则数据没有消费就退出
			Thread.currentThread().join(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
