package jdk9;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {
	public static void main(String[] args) {
		//1�����巢���ߣ�����������������Integer
		//ֱ��ʹ��JDK�Դ���submissionPublisher����ʵ����Publisher�ӿ�
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		
		//2�����嶩����
		Subscriber<Integer> subscriber = new Subscriber<Integer>() {
			
			private Subscription subscription;
			
			/**
			 * �������Ĺ�ϵ
			 */
			@Override
			public void onSubscribe(Subscription subscription) {
				//���涩�Ĺ�ϵ����Ҫ��������������Ӧ
				this.subscription = subscription;
				
				//����һ������
				this.subscription.request(1);
			}

			@Override
			public void onNext(Integer item) {
				//���ܵ�һ�����ݣ�����
				System.out.println("���ܵ����ݣ�" + item);
				
				//���������request������һ������
				//�ؼ� ��Ҫ����
				this.subscription.request(1);
				
				//���� �Ѿ��ﵽ��Ŀ�꣬����cancel���߷����߲��ٽ���������
				//this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				// �������쳣(�������ݴ����ʱ��������쳣)
				throwable.printStackTrace();
				
				//���Ը��߷����ߣ����治�ڽ�������
				//this.subscription.cancel();
			}

			@Override
			public void onComplete() {
				// �������  publisher.close();�󴥷�
				System.out.println("�������ˣ�");
				
			}
		};
		
		//3�������ߺͶ����߽������Ĺ�ϵ
		publisher.subscribe(subscriber);
		
		//4���������ݣ�������
		//���������������
		int data = 111;
		publisher.submit(data);
		publisher.submit(data);
		publisher.submit(data);
		publisher.submit(data);
		
		//5��������guan'bi������
		//��ʽ������Ӧ�÷���finally��ʹ��try-resouceȷ���ر�
		publisher.close();
		
		
		try {
			//���߳��ӳ�ֹͣ����������û�����Ѿ��˳�
			Thread.currentThread().join(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
