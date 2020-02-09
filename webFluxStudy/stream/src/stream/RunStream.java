package stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * ���в�������ʽ���ã�һ��Ԫ��ֻ����һ�� ÿһ���м��������һ���µ�����������һ������sourceStage 
 * 
 * ָ��ͬһ���ط�����Head
 * 
 * Headָ��nextStage -> nextStage -> nextStage -> ... -> null
 * 
 * ��״̬���������״̬�����ضϣ���������
 * 
 * ���л����£���״̬���м������һ���ܲ��в���
 * 
 * parallel/sequetial ��2������Ҳ���м������Ҳ�Ƿ���stream��
 * ����������ֻ�޸�Head�Ĳ��б�־
 * 
 * @author Administrator
 *
 */

public class RunStream {
	public static void main(String[] args) {
		Random random = new Random();
		// �����������
		Stream<Integer> stream = Stream.generate(() -> random.nextInt())
				// ����500�������
				.limit(500)
				// ��һ����״̬����
				.peek(s -> print("peek��" + s))
				// �ڶ�����״̬����
				.filter(s -> {
					print("filter��" + s);
					return s > 10000;
				})
				//��״̬����
				.sorted((i1, i2) -> {
					print("����" + i1 + "," + i2);
					return i1.compareTo(i2);
				})
				//��״̬����
				.peek(s -> print("peek2��" + s)).parallel();
		// ��ֹ����
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
