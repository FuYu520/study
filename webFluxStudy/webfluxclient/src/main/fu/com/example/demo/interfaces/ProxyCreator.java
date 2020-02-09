package com.example.demo.interfaces;

/**
 * 创建代理类接口
 * @author Administrator
 *
 */
public interface ProxyCreator {
	/**
	 * 创建代理类
	 * @param type
	 * @return
	 */
	Object createProxy(Class<?> type);
}
