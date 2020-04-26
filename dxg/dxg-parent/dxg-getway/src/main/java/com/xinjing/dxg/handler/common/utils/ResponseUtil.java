package com.xinjing.dxg.handler.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xinjing.dxg.handler.common.ApiResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class ResponseUtil {
    public static Mono<Void> response(ApiResponse apiResponse, ServerWebExchange exchange) {
        String s = JSONObject.toJSONString(apiResponse, SerializerFeature.WriteMapNullValue);
        DataBuffer buffer = null;
        buffer = exchange.getResponse().bufferFactory().wrap(s.getBytes(StandardCharsets.UTF_8));

        String origin = exchange.getRequest().getHeaders().getFirst(HttpHeaders.ORIGIN);
        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        if (StringUtils.isNotBlank(origin)) {
            exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,origin);
        }
        exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS,"true");
        exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"DELETE,POST,GET,OPTIONS,PUT,HEAD");
        exchange.getResponse().getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"Content-Type,Access-Token");
        Flux<DataBuffer> just = Flux.just(buffer);

        return exchange.getResponse().writeWith(just);
    }
}
