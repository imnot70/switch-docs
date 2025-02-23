package org.yjsq.wk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.common.StringConstant;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public <T> Result<T> success(T data) {
        return Result.success(data);
    }

    public String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            log.info("getToken, servletRequestAttributes is null");
            return null;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(StringConstant.HEADER_TOKEN);
        log.info("getToken, token:{}", token);
        return token;
    }
}
