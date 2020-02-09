package com.example.demo.beans;

import java.util.Map;

import org.springframework.http.HttpMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * 方法调用信息类
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {

	/**
	 * 请求url
	 */
	private String url;
	
	/**
	 * 请求方法
	 */
	private HttpMethod method;
	
	/**
	 * 请求参数(url)
	 */
	private Map<String, Object> params;
	
	/**
	 * 请求body
	 */
	private Mono body;
	
	/**
	 * 请求body类型
	 */
	private Class<?> bodyElement;
	
	/**
	 * 返回flux还是mono
	 */
	private boolean returnFlux;
	
	/**
	 * 返回对象的类型
	 */
	private Class<?> returnElementType;
	
}
