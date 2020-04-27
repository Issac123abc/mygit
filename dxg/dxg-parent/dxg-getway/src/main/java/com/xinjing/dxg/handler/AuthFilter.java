package com.xinjing.dxg.handler;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.xinjing.dxg.common.ApiResponse;
import com.xinjing.dxg.common.utils.StringUtil;
import com.xinjing.dxg.handler.common.AntUrlPathMatcher;
import com.xinjing.dxg.handler.common.utils.RedisUtil;
import com.xinjing.dxg.handler.common.utils.ResponseUtil;
import com.xinjing.dxg.handler.constant.FilterConstant;

import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 * */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
	public final static String ATTRIBUTE_IGNORE_FILTER = "IgnoreFilter";
	
	@Autowired
	private AntUrlPathMatcher antUrlPathMatcher;
	
	@Override
	public int getOrder() {		
		return 100;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 配置了IgnoreFilter过滤器的路径跳过检测
		URI uri = exchange.getRequest().getURI();
		String path = uri.getPath();
		if (match(path)) {
			return chain.filter(exchange);
		}
		String token = exchange.getRequest().getQueryParams().getFirst("token");
		ApiResponse<String> apiResponse = null;
		if (StringUtil.isBlank(token)) {
			apiResponse = ApiResponse.buildRep(401, "Unauthorized", "没有权限");
			return ResponseUtil.response(apiResponse, exchange);
		} else {
			String userId = RedisUtil.get(token);
			if (StringUtil.isBlank(userId)) {
				apiResponse = ApiResponse.buildRep(401, "Unauthorized", "没有权限");
				return ResponseUtil.response(apiResponse, exchange);
			} else {
				RedisUtil.set(token, userId, 60000 * 30); // 续期30min
			}
		}
		return chain.filter(exchange);
	}
	
	public boolean match(String url) {
		List<String> authNotFilter = FilterConstant.authNotFilter;
		boolean contains = authNotFilter.stream().map(s -> antUrlPathMatcher.pathMatchesUrl(s, url))
				.collect(Collectors.toSet()).contains(true);
		return contains;
	}

}
