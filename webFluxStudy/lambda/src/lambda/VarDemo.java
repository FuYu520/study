package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * ��������
 * @author Administrator
 *
 */
public class VarDemo {

	public static void main(String[] args) {
		// ����������ֵ�����ж����ԣ�������final;��һ�����ӡ�
		final List<String> list = new ArrayList<>();
		list.add("123");//�����޸Ĳ�Ӱ��list�����ֵ
		Consumer<String> consumer = s ->System.out.println(s + list);
		//s = �����ֵ
		consumer.accept("�����ֵ");
		
		
		String str = "str";//Ĭ��final
		//str = "123"; ���ﲻ׼�޸�
		Consumer<String> consumer2 = s ->System.out.println(s + str);
		consumer2.accept("�����ֵ");
	}

}
