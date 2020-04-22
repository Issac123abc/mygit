package com.xinjing.dxg.handler;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 * */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
	public final static String ATTRIBUTE_IGNORE_FILTER = "IgnoreFilter";
	
	@Override
	public int getOrder() {		
		return 100;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 配置了IgnoreFilter过滤器的路径跳过检测
		URI uri = exchange.getRequest().getURI();
		String path = uri.getPath();
		String token = exchange.getRequest().getQueryParams().getFirst("access_token");
		ServerHttpResponse response = exchange.getResponse();
		Map<String, String> map = new HashMap<>();
		map.put("code", "401");
		map.put("message", "没有权限");
		String returnStr = map.toString();
		System.out.println("---------token" + token);
		if (token == null || token.isEmpty()) {
			DataBuffer buffer = null;
			buffer = response.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_16));
			return exchange.getResponse().writeWith(Flux.just(buffer));
		}
		return chain.filter(exchange);
	}

}
