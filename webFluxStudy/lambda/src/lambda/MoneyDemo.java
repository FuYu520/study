package lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

interface IMoneyFormat{
	String format(int i);
}

class MyMoney{
	private final int money;

	public MyMoney(int money) {
		this.money = money;
	}
	
	public void printMoney(Function<Integer, String> imoneyFormat){
		System.out.println("我的存款：" + imoneyFormat.apply(this.money));
	}
}

public class MoneyDemo {
	public static void main(String[] args) {
		MyMoney money = new MyMoney(999999);
		Function<Integer, String> imoneyFormat = i -> new DecimalFormat("#,####").format(i);
		//函数接口链式操作
		money.printMoney(imoneyFormat.andThen(s -> "人民币" + s));
		
		
	}
}
