package org.yjsq.wk.bean.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {

    public static final Integer STATUS_BLOCKED = 1;

    public static final Integer GENDER_MALE = 1;
    public static final Integer GENDER_FEMALE = 2;

    public static final String SOURCE_ADD = "ADMIN_ADD";
    public static final String SOURCE_REG = "REGISTER";

    public static String convertGender(Integer gender) {
        if (GENDER_MALE.equals(gender)) {
            return "男";
        } else if (GENDER_FEMALE.equals(gender)) {
            return "女";
        } else {
            return "保密";
        }
    }

    private Long id;
    private String loginName;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private Integer gender;
    private String avatar;
    private String source;
    private Integer status;

}
