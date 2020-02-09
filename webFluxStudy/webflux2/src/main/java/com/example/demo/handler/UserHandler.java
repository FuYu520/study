package com.example.demo.handler;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.CheckUtil;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	private UserRepository repository;
	
	public UserHandler(UserRepository repository) {
		this.repository = repository;
	}
	/**
	 * 得到所有用户
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> getAllUser(ServerRequest request){
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(this.repository.findAll(), User.class);
	}
	
	/**
	 * 创建用户
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> createUser2(ServerRequest request){
		Mono<User> user = request.bodyToMono(User.class);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(this.repository.saveAll(user), User.class);
	}
	
	/**
	 * 创建用户,加校验
	 * @param request
	 * @return
	 * Mono<ServerResponse> 或者Flux 是发布者，任何时候都不能掉它的订阅方法，不能消费他
	 *    交给spring boot框架消费，任何时候都是返回一个流
	 *    不能调用Mono.subscribe方法
	 */
	public Mono<ServerResponse> createUser(ServerRequest request){
		Mono<User> user = request.bodyToMono(User.class);
		//User user2 = request.bodyToMono(User.class).block(); 会阻塞线程，会boot版本其他的可能报异常
		return user.flatMap(u -> {
			//检验代码
			CheckUtil.checkName(u.getName());
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(this.repository.save(u), User.class);
		});
	}
	
	/**
	 * 创建用户
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> deleteUserById(ServerRequest request){
		String id = request.pathVariable("id");
		return this.repository.findById(id).flatMap(user -> this.repository.delete(user))
		
		.then(ServerResponse.ok().build())
		
		.switchIfEmpty(ServerResponse.notFound().build());
	}
	
}
