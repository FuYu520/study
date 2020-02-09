package lambda;

import java.util.function.Function;

/**
 * �������ʽ�������ͷ ������������
 * 
 * ���ﻯ���Ѷ�������ĺ���ת��Ϊֻ��һ�������ĺ���
 * 
 * ���ﻯĿ�ģ��������ʽ
 * 
 * �߽׺��������غ����ĺ���
 * 
 * @author Administrator
 *
 */
public class CurryDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function<Integer, Function<Integer, Integer>> fun = x -> y -> x + y;
		System.out.println(fun.apply(2).apply(3));

		Function<Integer, Function<Integer, Function<Integer, Integer>>> fun2 = x -> y -> z -> x + y + z;

		System.out.println(fun2.apply(2).apply(3).apply(4));

		int[] nums = { 1, 2, 3 };
		Function f = fun2;
		for (int i = 0; i < nums.length; i++) {
			if (f instanceof Function) {
				Object obj = f.apply(nums[i]);
				if (obj instanceof Function) {
					f = (Function) obj;
				} else {
					System.out.println("���ý���");
				}
			}
		}
	}

}
