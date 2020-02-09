package lambda;

/**
 * 类型推断
 * 
 * @author Administrator
 *
 */
public class TypeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 变量类型定义
		IMath iMath = (x, y) -> x + y;

		// 数组里
		IMath[] iMaths = { (x, y) -> x + y };

		// 强转
		Object object = (IMath) (x, y) -> x + y;
		// 通过返回类型
		IMath iMath2 = createLambda();
		
		TypeDemo typeDemo = new TypeDemo();
		//不加强转会报错
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
