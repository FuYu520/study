package com.example.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.example.demo.exception.CheckException;

/**
 * 异常处理切面
 * @author Administrator
 *
 */
@RestControllerAdvice
public class CheckAdvice {
	
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity handleBindException(WebExchangeBindException e) {
		//400
		return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CheckException.class)
	public ResponseEntity handleBindException(CheckException e) {
		//400
		return new ResponseEntity<String>(checkStr(e), HttpStatus.BAD_REQUEST);
	}

	/**
	 * 校验异常转换为字符串
	 * @param ex
	 * @return
	 */
	private String toStr(WebExchangeBindException ex) {
		return ex.getFieldErrors().stream().map(e -> e.getField() + ":" + e.getDefaultMessage())
		.reduce("", (s1, s2) -> s1 +"\n" +s2);
	}
	
	/**
	 *  名字检查
	 * @param ex
	 * @return
	 */
	private String checkStr(CheckException ex) {
		System.out.println("名字检查");
		return ex.getFieldName() + "错误的值" + ex.getFieldValue();
	}
}
