package org.yjsq.wk.common;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<>(CommonCode.SUCCESS, data);
    }

    public static <T> Result<T> fail(RespCode code){
        return new Result<>(code.getCode(), code.getMsg(), null);
    }

    public static <T> Result<T> fail(int code, String msg){
        return new Result<>(code, msg, null);
    }

    public Result() {
    }

    public Result(RespCode code, T data){
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
