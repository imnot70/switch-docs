package org.yjsq.wk.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yjsq.wk.common.RespCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException{

    private int code;

    public BizException(RespCode code){
        this(code.getCode(), code.getMsg());
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
