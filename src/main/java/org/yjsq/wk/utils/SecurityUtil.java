package org.yjsq.wk.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

public class SecurityUtil {

    public static String generateToken(){
        return null;
    }

    public static String createPwd(String source){
        MD5 md5 = SecureUtil.md5();
        return md5.digestHex(source);
    }

    public static String createToken(String source){
        return Base64.encode(source);
    }

    public static Long getIdFromToken(String token){
        String idStr = Base64.decodeStr(token);
        return Long.valueOf(idStr);
    }

}
