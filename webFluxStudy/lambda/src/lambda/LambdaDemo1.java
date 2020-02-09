package lambda;

/**
 * �����ӿ�
 * 
 * @author Administrator ��һ������
 */
@FunctionalInterface
interface Interface1 {
	int doubleNum(int i);

	default int add(int x, int y) {
		x = this.doubleNum(y);
		return x + y;
	}
}

@FunctionalInterface
interface Interface2{
	int doubleNum(int i);
	
	default int add(int x, int y) {
		x = this.doubleNum(y);
		return x + y;
	}
}

@FunctionalInterface
interface Interface3 extends Interface1, Interface2{

	@Override
	default int add(int x, int y) {
		// TODO Auto-generated method stub
		return Interface1.super.add(x, y);
	}
	
}

public class LambdaDemo1 {

	public static void main(String[] args) {
		// (i)���� i*2 ִ����
		Interface1 in = (i) -> i * 2;
		// ���
		Interface1 in2 = i -> i * 2;
		// ��֪��������
		Interface1 in3 = (int i) -> i * 2;
		// һ��Ĭ����return
		Interface1 in4 = (int i) -> {
			System.out.println("i*2");
			return i * 2;
		};
		
		int ren = in4.add(1, 5);
		System.out.println(ren);

	}

}
