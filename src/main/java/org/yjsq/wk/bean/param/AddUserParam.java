package org.yjsq.wk.bean.param;

import lombok.Data;

@Data
public class AddUserParam {

    private String loginName;
    private String email;
    private String nickName;
    private String phone;
    private Integer gender;
    private String avatar;
    private String source;

}
