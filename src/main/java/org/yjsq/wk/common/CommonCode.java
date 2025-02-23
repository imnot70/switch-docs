package org.yjsq.wk.common;

import lombok.Getter;

@Getter
public enum CommonCode implements RespCode{
    SUCCESS(1000000, "ok"),
    DATA_NOT_FOUNT(1100000, "data not found"),
    PARAM_IS_NULL(1100001, "param is null"),
    PERMISSION_DENIED(1100002, "permission denied"),
    USER_BLOCKED(1200000, "user is blocked"),
    OLD_PWD_EMPTY(1200100, "old password is empty"),
    NEW_PWD_EMPTY(1200101, "new password is empty"),
    OLD_PWD_WRONG(1200102, "old password is wrong"),
    TWO_PWD_WRONG(1200103, "old password and new password are same"),
    USER_EXIST(1200104, "user already exist"),
    TAG_EXIST(1200200, "tag already exist");

    private final int code;
    private final String msg;

    CommonCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
