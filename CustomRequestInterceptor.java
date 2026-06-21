package com.fresco.codelab.logging;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        StringBuilder log = new StringBuilder();
        log.append(method).append("::").append(url).append("::");

        if ("POST".equals(method)) {
            StringBuilder body = new StringBuilder("{ ");
            Map<String, String[]> params = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                body.append(entry.getKey()).append(":").append(entry.getValue()[0]).append(", ");
            }
            body.append(" }");
            log.append("Body::").append(body);
        }

        log.append("clientIpAddr/").append(ip);
        logger.info(log.toString());

        return true;
    }
}
