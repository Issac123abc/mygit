package com.xinjing.dxg.handler;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class IgnoreFilter extends AbstractGatewayFilterFactory<IgnoreFilter.Config> {
	public IgnoreFilter(){
		super(Config.class);
	}
	
	 public static class Config {}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			exchange.getAttributes().put(AuthFilter.ATTRIBUTE_IGNORE_FILTER, true);
	        return chain.filter(exchange);
        };
	}
	
	@Override
    public String name() {
        return "IgnoreFilter";
    }
}
