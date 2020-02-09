package com.example.demo.handler;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.example.demo.exception.CheckException;

import reactor.core.publisher.Mono;

/**
 * handler抛出异常，就会进入这里面
 * @author Administrator
 *
 */
@Component
//WebExceptionHandler 里面含有多个异常处理handle，把handler调高
//优先级，不设置优先级，不会执行
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ServerHttpResponse response = exchange.getResponse();
		//设置响应头
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		//这是返回类型
		response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
		
		//异常信息
		String errorMsg = toStr(ex);
		
		DataBuffer db = response.bufferFactory().wrap(errorMsg.getBytes());
		return response.writeWith(Mono.just(db));
	}

	private String toStr(Throwable ex) {
		//已知异常
		if (ex instanceof CheckException) {
			CheckException e = (CheckException) ex;
			return e.getFieldName() + ":invalid value" + e.getFieldValue();
		}
		//未知异常,需要打印堆栈，方便定位异常
		else {
			ex.printStackTrace();
			return ex.toString();
		}
	}

}
