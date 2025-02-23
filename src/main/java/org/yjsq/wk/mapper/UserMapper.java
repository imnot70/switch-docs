package org.yjsq.wk.mapper;

import org.apache.ibatis.annotations.Param;
import org.yjsq.wk.bean.entity.User;

import java.util.List;

public interface UserMapper {
    User findUserById(@Param("id") Long id);

    User findUserByEmail(@Param("email") String email);

    User findUserByLoginName(@Param("loginName") String loginName);

    int insert(@Param("user") User user);

    void updatePassword(@Param("pwd") String pwd, @Param("id") Long id);

    List<User> selectList(@Param("startNum") Integer startNum, @Param("pageSize") Integer pageSize);

    Integer selectCount();
}
