package lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * ��������
 * 
 * @author Administrator
 *
 */
public class MethodRefrenceDemo {
	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.eat(1);

		/**
		 * �������� ��� �������� �ұ�ִ���壬����ִ����ֻ��һ���������ã� ��������(s)�ͼ�ͷ���һ������д�ɷ������÷�ʽ
		 * 
		 */
		Consumer<String> consumer = s -> System.out.println(s);
		// ��������
		Consumer<String> consumer2 = System.out::println;
		consumer2.accept("�����ַ���");

		// ��̬�����ķ�������
		Consumer<Dog> dogConsumer = Dog::bark;
		dogConsumer.accept(dog);

		// �Ǿ�̬����
		Function<Integer, Integer> function = dog::eat;//1����ֵ ����
		dog = null;//2������dog ��Ӱ����
		System.out.println("��ʣ�£�" + function.apply(5));
		Function<Integer, Integer> function2 = new Dog()::eat;

		// һԪ�����ӿ�
		UnaryOperator<Integer> un = dog::eat;
		IntUnaryOperator intun = dog::eat;
		System.out.println(un.apply(6));
		System.out.println(intun.applyAsInt(4));

		// Dog::eat
		BiFunction<Dog, Integer, Integer> biFunction = Dog::eat;
		System.out.println("��ʣ��" + biFunction.apply(dog, 4));
		
		
		//���캯����������
		Supplier<Dog> supplier = Dog::new;
		System.out.println("�������¶���" + supplier.get());
		
		//�������Ĺ��캯������
		Function<String, Dog> function3 = Dog::new;
		System.out.println("�������¶���" + function3.apply("����"));
		
		
		BiFunction<String, Integer, Dog> biFunction2 = Dog::new;
		System.out.println(biFunction2.apply("����", 4));

	}
}

class Dog {
	private String name = "����Ȯ";

	private int sum = 10;
	
	
	public Dog(String name) {
		this.name = name;
	}
	
	

	public Dog(String name, int sum) {
		super();
		this.name = name;
		this.sum = sum;
	}



	public Dog() {
		super();
	}



	/**
	 * ��̬���� ������������void��������
	 * 
	 * @param dog
	 */
	public static void bark(Dog dog) {
		System.out.println(dog + "����");
	}

	/**
	 * �Ǿ�̬���� �������T�����ز���T �Ǿ�̬��this ָ�� JDKĬ�ϻ�ѵ�ǰʵ�����뵽�Ǿ�̬������������Ϊthis,λ���ǵ�һ��
	 * 
	 * @param num
	 * @return
	 */
	public int eat(int num) {
		// public int eat(Dog this, int num) {
		System.out.println("����" + num);
		return this.sum - num;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
