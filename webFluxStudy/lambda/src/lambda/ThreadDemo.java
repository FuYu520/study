package lambda;

public class ThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object target = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("ok");
			}
		};
		
		new Thread((Runnable) target).start();
		
		//jdk8 lambda ������ָ���ӿ�ʵ��
		//()��������        sout:ִ����
		Object target2 = (Runnable)() -> System.out.println("ok");
		Object target3 = (Runnable)() -> System.out.println("ok");
		new Thread((Runnable)target2).start();
		
		System.out.println(target2 == target3);
	}

}
