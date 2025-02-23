package org.yjsq.wk.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yjsq.wk.common.Result;

@ResponseBody
@ControllerAdvice
public class Handler {

    @ExceptionHandler(BizException.class)
    public Result<?> handleException(BizException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

}
