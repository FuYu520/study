package com.example.demo;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class TestController {
	
	@GetMapping("/1")
	private String get1() {
		log.info("get1 start");
		String createStr = createStr();
		log.info("get1 end");
		return createStr;
	}
	
	private String createStr() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "some string";
	}
	
	@GetMapping("/2")
	private Mono<String> get2() {
		log.info("get1 start");
		//惰性
		Mono<String> result = Mono.fromSupplier(() -> createStr());
		log.info("get1 end");
		return result;
	}
	
	
	/**
	 * Flux 
	 *  MediaType.TEXT_EVENT_STREAM_VALUE text/event-stream
	 * @return
	 */
	@GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	private Flux<String> get3() {
		//1-5转对象
		Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "flxu data --" + i;
		}));
		return result;
	}
}
