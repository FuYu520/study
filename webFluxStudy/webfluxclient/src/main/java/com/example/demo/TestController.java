package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

	@Autowired
	IUserApi userApi;
	
	@GetMapping("/")
	public void test() {
		
		//测试信息提取
		//不订阅，不会发出实际请求，但会进入我们的代理类
//		userApi.getAllUser();
//		userApi.getUserById("11111");
//		userApi.deleteUserById("212323");
//		userApi.createUser(Mono.just(User.builder().name("fy").age(22).build()));
		
		//直接调用实现rest接口的效果
//		Flux<User> allUser = userApi.getAllUser();
//		allUser.subscribe(System.out::print);
		String id = "5e3a7ccd3f85a40dbfddfd62";
		userApi.getUserById(id).subscribe(user -> {
			System.out.println("getUserById:" + user);
		}, e ->{
			System.out.println("找不到用户");
		});
//		
//		userApi.deleteUserById(id).subscribe(v -> {
//			System.out.println("deleteById success");
//		});
		
		userApi.createUser(Mono.just(User.builder().name("fy").age(18).build())).subscribe(System.out::println);
	}
	
	
}
