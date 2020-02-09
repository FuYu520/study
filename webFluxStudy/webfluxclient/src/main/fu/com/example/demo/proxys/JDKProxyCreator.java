package com.example.demo.proxys;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.ApiServer;
import com.example.demo.beans.MethodInfo;
import com.example.demo.beans.ServerInfo;
import com.example.demo.handlers.WebClientRestHandler;
import com.example.demo.interfaces.ProxyCreator;
import com.example.demo.interfaces.RestHandler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 使用JDK动态代理实现代理类
 * 
 * @author Administrator
 *
 */
@Slf4j
public class JDKProxyCreator implements ProxyCreator {

	@Override
	public Object createProxy(Class<?> type) {
		log.info("createProxy:" + type);
		// 根据接口得到api服务器信息
		ServerInfo serverInfo = extractServerInfo(type);

		log.info("serverInfo:" + serverInfo);
		// 给每一个代理类创建一个实例
		RestHandler handler = new WebClientRestHandler();

		/**
		 * 初始化服务器信息（初始化webClient）
		 */
		handler.init(serverInfo);

		return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { type }, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 根据方法和参数得到调用信息
				MethodInfo methodInfo = extractMethodInfo(method, args);
				log.info("methodInfo:" + methodInfo);
				// 调用rest
				return handler.invokeRest(methodInfo);
			}

			/**
			 * 根据方法定义和参数得到调用的相关信息
			 * 
			 * @param method
			 * @param args
			 * @return
			 */
			private MethodInfo extractMethodInfo(Method method, Object[] args) {
				MethodInfo methodInfo = new MethodInfo();
				extractUrlAndMethod(method, methodInfo);

				extractRequestParamAndBody(method, args, methodInfo);

				// 提取返回对象信息
				extractReturnIndo(method, methodInfo);
				return methodInfo;
			}

			/**
			 * 
			 * @param method
			 * @param methodInfo
			 */
			private void extractReturnIndo(Method method, MethodInfo methodInfo) {
				// 返回flux还是mono
				// isAssignableFrom 判断类型
				// instanceof 判断实例
				boolean isFlux = method.getReturnType().isAssignableFrom(Flux.class);
				methodInfo.setReturnFlux(isFlux);
				
				//得到返回对象的实际类型
				Class<?> elementType = extractElementType(method.getGenericReturnType()); 
				methodInfo.setReturnElementType(elementType);
			}

			/**
			 * 得到泛型的实际类型
			 * @param genericReturnType
			 * @return
			 */
			private Class<?> extractElementType(Type genericReturnType) {
				Type[] types = ((ParameterizedType)genericReturnType).getActualTypeArguments();
				return (Class<?>) types[0];
			}

			/**
			 * 调用得到请求的参数和body
			 * 
			 * @param method
			 * @param args
			 * @param methodInfo
			 */
			private void extractRequestParamAndBody(Method method, Object[] args, MethodInfo methodInfo) {

				Parameter[] parameters = method.getParameters();
				// 参数和值对应的map
				Map<String, Object> params = new LinkedHashMap<>();
				methodInfo.setParams(params);
				for (int i = 0; i < parameters.length; i++) {
					// 是否带@PathVariable注解
					PathVariable annPath = parameters[i].getAnnotation(PathVariable.class);
					if (annPath != null) {
						params.put(annPath.value(), args[i]);
					}
					// 是否带@RequestBody
					RequestBody annBody = parameters[i].getAnnotation(RequestBody.class);
					if (annBody != null) {
						methodInfo.setBody((Mono<?>) args[i]);
						//请求对象实际类型
						methodInfo.setBodyElement(extractElementType(parameters[i].getParameterizedType()));
					}
				}
			}

			/**
			 * 得到请求url和请求方法
			 * 
			 * @param method
			 * @param methodInfo
			 */
			private void extractUrlAndMethod(Method method, MethodInfo methodInfo) {

				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					// GET
					if (annotation instanceof GetMapping) {
						GetMapping a = (GetMapping) annotation;
						methodInfo.setUrl(a.value()[0]);
						methodInfo.setMethod(HttpMethod.GET);
					}
					// POST
					else if (annotation instanceof PostMapping) {
						PostMapping a = (PostMapping) annotation;
						methodInfo.setUrl(a.value()[0]);
						methodInfo.setMethod(HttpMethod.POST);
					}
					// DELETE
					else if (annotation instanceof DeleteMapping) {
						DeleteMapping a = (DeleteMapping) annotation;
						methodInfo.setUrl(a.value()[0]);
						methodInfo.setMethod(HttpMethod.DELETE);
					}
					// PUT
					else if (annotation instanceof PutMapping) {
						PutMapping a = (PutMapping) annotation;
						methodInfo.setUrl(a.value()[0]);
						methodInfo.setMethod(HttpMethod.PUT);
					}
				}
			}
		});
	}

	/**
	 * 提取服务器信息
	 * 
	 * @param type
	 * @return
	 */
	private ServerInfo extractServerInfo(Class<?> type) {
		ServerInfo serverInfo = new ServerInfo();
		ApiServer anno = type.getAnnotation(ApiServer.class);
		serverInfo.setUrl(anno.value());
		return serverInfo;
	}

}
