package lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 方法引用
 * 
 * @author Administrator
 *
 */
public class MethodRefrenceDemo {
	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.eat(1);

		/**
		 * 匿名函数 左边 函数参数 右边执行体，函数执行体只有一个函数调用， 函数参数(s)和箭头左边一样，缩写成方法引用方式
		 * 
		 */
		Consumer<String> consumer = s -> System.out.println(s);
		// 方法引用
		Consumer<String> consumer2 = System.out::println;
		consumer2.accept("接收字符串");

		// 静态方法的方法引用
		Consumer<Dog> dogConsumer = Dog::bark;
		dogConsumer.accept(dog);

		// 非静态方法
		Function<Integer, Integer> function = dog::eat;//1，传值 引用
		dog = null;//2，所以dog 不影响结果
		System.out.println("还剩下：" + function.apply(5));
		Function<Integer, Integer> function2 = new Dog()::eat;

		// 一元函数接口
		UnaryOperator<Integer> un = dog::eat;
		IntUnaryOperator intun = dog::eat;
		System.out.println(un.apply(6));
		System.out.println(intun.applyAsInt(4));

		// Dog::eat
		BiFunction<Dog, Integer, Integer> biFunction = Dog::eat;
		System.out.println("还剩：" + biFunction.apply(dog, 4));
		
		
		//构造函数方法引用
		Supplier<Dog> supplier = Dog::new;
		System.out.println("创建了新对象" + supplier.get());
		
		//带参数的构造函数引用
		Function<String, Dog> function3 = Dog::new;
		System.out.println("创建了新对象" + function3.apply("旺财"));
		
		
		BiFunction<String, Integer, Dog> biFunction2 = Dog::new;
		System.out.println(biFunction2.apply("旺财", 4));

	}
}

class Dog {
	private String name = "哮天犬";

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
	 * 静态方法 输入参数，输出void，消费者
	 * 
	 * @param dog
	 */
	public static void bark(Dog dog) {
		System.out.println(dog + "叫了");
	}

	/**
	 * 非静态方法 输入参数T，返回参数T 非静态有this 指针 JDK默认会把当前实例传入到非静态方法，参数名为this,位置是第一个
	 * 
	 * @param num
	 * @return
	 */
	public int eat(int num) {
		// public int eat(Dog this, int num) {
		System.out.println("吃了" + num);
		return this.sum - num;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
