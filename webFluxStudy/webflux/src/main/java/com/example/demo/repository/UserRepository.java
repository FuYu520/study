package com.example.demo.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

	/**
	 * 根据年龄查找用户
	 * @param start
	 * @param end
	 * @return
	 */
	Flux<User> findByAgeBetween(int start, int end);
	
	@Query("{'age':{'$gte':?0,'$lte':?1}}")
	Flux<User> oldUser(int start, int end);
}
