package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.CheckUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserRepository repository;

	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public Flux<User> getAll() {
		return repository.findAll();
	}

	/**
	 *  以SSE形式多次返回数据
	 * 
	 * @return
	 */
	@GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> streamGetAll() {
		return repository.findAll();
	}

	/**
	 * 新增数据
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/")
	public Mono<User> createUser(@RequestBody User user) {
		System.out.println(user);
		CheckUtil.checkName(user.getName());
		return this.repository.save(user);
	}


	/**
	 * 根据id删除用户 存在返回200，不存在返回404
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
		System.out.println("id");
		// deletebyID 没有返回值, 不能判断数据是否存在
		// this.repository.deleteById(id)
		// deletebyId没有返回值，不能判断数据是否存在
		return this.repository.findById(id)
				// 当要操作数据，并返回一个Mono这个时候使用flatMap
				// 如果不操作数据，只是转换数据，使用map
				.flatMap(user -> this.repository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 修改数据 成功返回200，不存在返回404
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping("/{id}")
	public Mono<ResponseEntity<User>> updateUser( @PathVariable("id") String id,@Valid @RequestBody User user) {
		CheckUtil.checkName(user.getName());
		return this.repository.findById(id)
				// flatMap操作数据
				.flatMap(u -> {
					u.setAge(user.getAge());
					u.setName(user.getName());
					u.setId(user.getId());
					return this.repository.save(u);
				})
				// map：转换数据
				.map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Mono<ResponseEntity<User>> findUser(@PathVariable("id") String id) {
		return this.repository.findById(id).map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
	 *  根据年龄查找用户
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("/age/{start}/{end}")
	public Flux<User> findByAge(@PathVariable("start")int start,@PathVariable("end")int end){
		return this.repository.findByAgeBetween(start, end);
	}
	
	/**
	 *  根据年龄查找用户,流返回
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> streamFindByAge(@PathVariable("start")int start,@PathVariable("end")int end){
		return this.repository.findByAgeBetween(start, end);
	}
	
	/**
	 *  根据年龄查找用户
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("/old")
	public Flux<User> oldUser(){
		return this.repository.oldUser(25, 30);
	}
}
