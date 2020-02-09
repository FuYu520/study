package com.example.demo.handlers;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.example.demo.beans.MethodInfo;
import com.example.demo.beans.ServerInfo;
import com.example.demo.interfaces.RestHandler;

import reactor.core.publisher.Mono;

public class WebClientRestHandler implements RestHandler {

	private WebClient client;
	/**
	 * 初始化wenclient
	 */
	@Override
	public void init(ServerInfo serverInfo) {
		this.client = WebClient.create(serverInfo.getUrl());
	}

	/**
	 * 处理rest请求
	 */
	@Override
	public Object invokeRest(MethodInfo methodInfo) {
		Object result = null;
		//返回结果
		 RequestBodySpec request = this.client
		//请求方法
		.method(methodInfo.getMethod())
		//请求url
		.uri(methodInfo.getUrl(), methodInfo.getParams())
		//请求参数类型
		.accept(MediaType.APPLICATION_JSON);
		 ResponseSpec retrieve = null;
		//判断是否带了body
		if (methodInfo.getBody()!=null) {
			//发出请求
			retrieve = request.body(methodInfo.getBody(), methodInfo.getBodyElement()).retrieve();;
		}else {
			retrieve = request.retrieve();
		}
		
		//处理异常
		retrieve.onStatus(status -> status.value() ==404, response -> Mono.just(new RuntimeException()));
		
		//处理body
		if (methodInfo.isReturnFlux()) {
			result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
		}else {
			result = retrieve.bodyToMono(methodInfo.getReturnElementType());
		}
		return result;
	}

}
