package org.yjsq.wk.common;

import org.yjsq.wk.utils.SecurityUtil;

public class StringConstant {

    public static final String HEADER_TOKEN = "token";

    public static final String DEFAULT_PWD = "123456";
    public static final String DEFAULT_PWD_MD5 = SecurityUtil.createPwd(StringConstant.DEFAULT_PWD);

    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_ONLY_PATTERN = "yyyy-MM-dd";
    public static final String SHORT_DATE_TIME_PATTERN = "yyyyMMdd";

    public static final String STR_BLANK = "";
    public static final String STR_SPACE = " ";
    public static final String STR_DOT = ".";

}
