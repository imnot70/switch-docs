package org.yjsq.wk.mapper;

import org.apache.ibatis.annotations.Param;
import org.yjsq.wk.bean.entity.Tag;

import java.util.List;

public interface TagMapper {

    int insert(@Param("tag") Tag tag);

    Tag selectByName(@Param("tagName") String tagName);

    List<Tag> selectList(@Param("startNum") Integer startNum, @Param("pageSize") Integer pageSize);

    Integer selectCount();

}
