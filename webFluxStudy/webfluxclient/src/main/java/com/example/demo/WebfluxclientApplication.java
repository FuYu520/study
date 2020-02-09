package com.example.demo;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.interfaces.ProxyCreator;
import com.example.demo.proxys.JDKProxyCreator;

@SpringBootApplication
public class WebfluxclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxclientApplication.class, args);
	}
	
	@Bean
	ProxyCreator jdkProxyCreator() {
		return new JDKProxyCreator();
	}
	
	@Bean
	FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator){
		return new FactoryBean<IUserApi>() {
			
			/**
			 * 得到代理对象
			 */
			@Override
			public IUserApi getObject() throws Exception {
				return (IUserApi) proxyCreator.createProxy(this.getObjectType());
			}
			
			/**
			 * 得到类
			 */
			@Override
			public Class<?> getObjectType() {
				return IUserApi.class;
			}
		};
	}
}
