package org.yjsq.wk.bean.vo;

import lombok.Data;

@Data
public class LoginVo {

    private String token;

    public LoginVo(String token) {
        this.token = token;
    }

    public LoginVo() {
    }
}
