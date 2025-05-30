package com.sl.common.config;


import jakarta.servlet.Filter;      // 接口
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

// 1. 添加过滤器
@Component
@Slf4j
public class RequestLogFilter implements Filter {
    


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        
        // 包装请求使其可重复读取
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);

        // 只处理目标接口
        if (((HttpServletRequest) request).getRequestURI().contains("/plat/gast/tasks/init")) {
            // 读取请求体
            byte[] bodyBytes = wrappedRequest.getContentAsByteArray();
            String body = new String(bodyBytes, wrappedRequest.getCharacterEncoding());
            
            // 打印原始请求信息
            log.error("API请求捕获 - URL: {} | Body: {}", 
                     wrappedRequest.getRequestURL(),
                     body);
        }
        
        chain.doFilter(wrappedRequest, response);
    }
}