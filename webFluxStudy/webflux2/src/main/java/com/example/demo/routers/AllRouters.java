package com.example.demo.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.handler.UserHandler;

@Configuration
public class AllRouters {

	@Bean
	RouterFunction<ServerResponse> userRouter(UserHandler handler) {
		// predicate满足条件的url进来，
		// routerFunction路由函数
		// RequestPredicates.path 类似controller @RequestMapping("/user")
		// RouterFunctions.route 相当于类里的@RequestMapping
		return RouterFunctions.nest(RequestPredicates.path("/user"),
				//得到所有用户
				RouterFunctions.route(RequestPredicates.GET("/"), handler::getAllUser)
				//创建用户
				.andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), handler::createUser)
				.andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUserById));
				//删除用户
				
	}	
}
