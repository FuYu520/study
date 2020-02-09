package jdk9;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * Processor ��Ҫ�̳�SubmissionPublisher��ʵ��Processor�ӿ�
 * 
 * ����Դ����integer�����˵�С��0�ģ�Ȼ��ת�����ַ���������ȥ
 * 
 * @author Administrator
 *
 */

class MyProcessor extends SubmissionPublisher<String> implements Processor<Integer, String> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		// ���涩�Ĺ�ϵ����Ҫ����������������Ӧ
		this.subscription = subscription;

		// ����һ������
		this.subscription.request(1);
	}

	@Override
	public void onNext(Integer item) {
		// ���ܵ�һ�����ݣ�����
		System.out.println("���������ܵ����ݣ�" + item);

		// ���˵�С��0�ģ�Ȼ�󷢲���ȥ
		if (item > 0) {
			this.submit("ת��������ݣ�" + item);
		}

		// ���������request������һ������
		// �ؼ� ��Ҫ����
		this.subscription.request(1);

		// ���� �Ѿ��ﵽ��Ŀ�꣬����cancel���߷����߲��ٽ���������
		// this.subscription.cancel();

	}

	@Override
	public void onError(Throwable throwable) {
		// �������쳣(�������ݴ����ʱ��������쳣)
		throwable.printStackTrace();

		// ���Ը��߷����ߣ����治�ڽ�������
		this.subscription.cancel();

	}

	@Override
	public void onComplete() {
		// ������� publisher.close();�󴥷�
		System.out.println("�������ˣ�");
		// �رշ�����
		this.close();

	}

}

public class FlowDemo2 {

	public static void main(String[] args) {
		// 1�����巢���ߣ�����������������Integer
		// ֱ��ʹ��jdk�Դ���submissionPublisher
		SubmissionPublisher<Integer> publiser = new SubmissionPublisher<>();

		// 2�����崦�����������ݽ��й��ˣ���ת��ΪString����
		MyProcessor processor = new MyProcessor();

		// 3�������ߺʹ����� �������Ĺ�ϵ
		publiser.subscribe(processor);

		// 4���������ն����ߣ�����string��������
		Subscriber<String> subscriber = new Subscriber<String>() {

			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription subscription) {
				// ���涩�Ĺ�ϵ, ��Ҫ����������������Ӧ
				this.subscription = subscription;

				// ����һ������
				this.subscription.request(1);
			}

			@Override
			public void onNext(String item) {
				// ���ܵ�һ������, ����
				System.out.println("���ܵ�����: " + item);

				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// ���������request������һ������
				this.subscription.request(1);

				// ���� �Ѿ��ﵽ��Ŀ��, ����cancel���߷����߲��ٽ���������
				// this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				// �������쳣(���紦�����ݵ�ʱ��������쳣)
				throwable.printStackTrace();

				// ���ǿ��Ը��߷�����, ���治����������
				this.subscription.cancel();
			}

			@Override
			public void onComplete() {
				// ȫ�����ݴ�������(�����߹ر���)
				System.out.println("��������!");
			}
		};

		// 5�������������ն����߽������Ĺ�ϵ
		processor.subscribe(subscriber);

		// 6���������ݣ�������
		// �������������������
		//submit ��һ�����巽��
		//Subscriber ��������ˣ�256�����ͻ�ͣ����������ȥ��������
		for (int i = 0; i < 1000; i++) {
			System.out.println("�������ݣ�" + i);
			publiser.submit(i);
		}

		// 7�������󣬹ر�f������
		// ��ʽ������Ӧ�÷�finally ����ʹ��try-resouceȷ���ر�
		publiser.close();

		// ���߳��ӳ�ֹͣ����������mû�����Ѿ��˳�
		try {
			Thread.currentThread().join(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
