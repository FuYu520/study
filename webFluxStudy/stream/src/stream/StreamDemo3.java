package stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * stream �����-�м���� ���Ƿ���stream
 * 
 * @author Administrator
 *
 */
public class StreamDemo3 {

	public static void main(String[] args) {

		String str = "my name is 007";

		// ��״̬����
		// map/mapToXXX �����ϵ�����
		// �� str ��ֳ�һ������ s ȡ�� s�ĳ��� ���� ���д�ӡ
		Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(s -> s.length()).forEach(System.out::println);
		// flatMap/flatMapToXXX �����ϵ����ԣ�����(����)��
		//A Ԫ����B���ԣ����ϣ� �����յõ����е�AԪ�����BԪ�ؼ���
		//intStream/LongStream������stream�����࣬Ҫ����װ��boxed
		//�ַ��� ����
		Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(System.out::println);
		//�ַ��� �ַ�
		Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i ->System.out.println((char)i.intValue()));
		// filter������
		// peek ����Debug ���м��������foreach��ֹ���� ����������  
		Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);
		
		// unordered �����������漰

		// ��״̬��������ǰ�����Ҫ��������Ԫ�� ���磺������Ҫ���н���������
		// distinct ȥ��
		// sorted ����
		// limit/skip limit����/skip����
		//limit��Ҫ����������
		new Random().ints(10).filter(s -> s >100 && s< 1000).forEach(System.out::println);
	}

}
