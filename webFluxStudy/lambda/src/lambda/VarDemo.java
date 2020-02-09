package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 变量引用
 * @author Administrator
 *
 */
public class VarDemo {

	public static void main(String[] args) {
		// 参数传的是值，会有二义性，所以用final;是一个“坑”
		final List<String> list = new ArrayList<>();
		list.add("123");//这里修改不影响list输出的值
		Consumer<String> consumer = s ->System.out.println(s + list);
		//s = 输出的值
		consumer.accept("输出的值");
		
		
		String str = "str";//默认final
		//str = "123"; 这里不准修改
		Consumer<String> consumer2 = s ->System.out.println(s + str);
		consumer2.accept("输出的值");
	}

}
