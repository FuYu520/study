package com.example.demo.interfaces;

import com.example.demo.beans.MethodInfo;
import com.example.demo.beans.ServerInfo;

/**
 * rest请求调用handler
 * @author Administrator
 *
 */
public interface RestHandler {

	/**
	 *  
	 *  初始化服务器信息
	 * 
	 * @param serverInfo
	 */
	void init(ServerInfo serverInfo);

	/**
	 * 调用rest请求，返回接口
	 * @param methodInfo
	 * @return
	 */
	Object invokeRest(MethodInfo methodInfo);

}
