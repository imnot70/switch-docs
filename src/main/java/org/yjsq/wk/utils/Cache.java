package org.yjsq.wk.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.yjsq.wk.bean.entity.User;

public class Cache {

    private static final TimedCache<String, User> USER_CACHE;

    static {
        USER_CACHE = CacheUtil.newTimedCache(12 * 60 * 60 * 1000);
    }

    public static void cacheUser(String token, User user, long expireTime) {
        USER_CACHE.put(token, user, expireTime);
    }

    public static User getUserInfo(String token) {
        return USER_CACHE.get(token);
    }

    public static void remove(String token){
        USER_CACHE.remove(token);
    }

}
