package lambda;

/**
 * �����ƶ�
 * 
 * @author Administrator
 *
 */
public class TypeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// �������Ͷ���
		IMath iMath = (x, y) -> x + y;

		// ������
		IMath[] iMaths = { (x, y) -> x + y };

		// ǿת
		Object object = (IMath) (x, y) -> x + y;
		// ͨ����������
		IMath iMath2 = createLambda();
		
		TypeDemo typeDemo = new TypeDemo();
		//����ǿת�ᱨ��
		typeDemo.test((IMath)(x, y) -> x + y);

	}

	public void test(IMath iMath) {

	}

	public void test(IMath2 iMath) {

	}

	private static IMath createLambda() {

		return (x, y) -> x + y;
	}

}

@FunctionalInterface
interface IMath {
	int add(int x, int y);
}

@FunctionalInterface
interface IMath2 {
	int add(int x, int y);
}
