package com.xinjing.dxg.handler;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.xinjing.dxg.handler.common.ApiResponse;
import com.xinjing.dxg.handler.common.utils.ResponseUtil;
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
//		URI uri = exchange.getRequest().getURI();
//		String path = uri.getPath();
		String token = exchange.getRequest().getQueryParams().getFirst("access_token");
		ApiResponse<String> apiResponse = null;
		if (token == null || token.isEmpty()) {
			apiResponse = ApiResponse.buildRep(401, "Unauthorized", "没有权限");
			return ResponseUtil.response(apiResponse,exchange);
		}
		return chain.filter(exchange);
	}

}
