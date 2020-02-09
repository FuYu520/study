package com.example.demo.util;

import java.util.stream.Stream;

import com.example.demo.exception.CheckException;

public class CheckUtil {

	private static final String[] INVALID_NAMES = {"admin", "gianliyuan"};
	
	/**
	 * 校验名字，不成功抛出校验异常
	 * @param name
	 */
	public static void checkName(String value) {
		System.out.println(value);
		Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
		//找到任何一个，否则返回一个空的Optional。 
		.findAny()
		//（如果值存在则执行代码块）。如果存在就抛出异常
		.ifPresent(name ->{
			throw new CheckException("name",value);
		});
	}
}
