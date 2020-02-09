package stream;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * stream:�߼�������������������
 * �ⲿ������
 * �ڲ�������
 * @author Administrator
 *
 */
public class StreamDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3};
		//�ⲿ����
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		System.out.println(sum);
		
		
		//�ڲ����� stream��
		//of ����new
		int sum2 = IntStream.of(nums).sum();
		System.out.println(sum2);
		
		
		//�ڲ�������������ֵ�����У���·����������Ҫ��������꣬ȡ�����������ģ�
		
		
		/**
		 * ������ֵ  
		 * map:�м����������stream�Ĳ���
		 * sum:��ֹ����
		 */
		
		int sum3 = IntStream.of(nums).map(StreamDemo1::doubleNume).sum();
		System.out.println("���Ϊ"+sum3);
		
		System.out.println("������ֵ������ֹ����û���ã��м������ִ��");
		//doubleNume ��������䲻��ִ��
		IntStream.of(nums).map(StreamDemo1::doubleNume);
		
	}
	
	public static int doubleNume(int i) {
		System.out.println("ִ���˳���2");
		return i*2;
	}

}
