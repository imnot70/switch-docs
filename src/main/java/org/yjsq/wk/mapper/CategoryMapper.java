package org.yjsq.wk.mapper;

import org.apache.ibatis.annotations.Param;
import org.yjsq.wk.bean.entity.Category;

import java.util.List;

public interface CategoryMapper {

    int insert(@Param("category") Category category);

    List<Category> selectList();

    List<Category> selectTopLevelList();

    Category selectById(@Param("id") Long id);

    List<Category> selectListByParentId(@Param("parentId") Long parentId);

    Category selectByParentIdAndName(@Param("parentId") Long parentId, @Param("name") String name);

    void deleteById(@Param("id") Long id);

    void deleteByIds(@Param("ids") List<Long> id);
}
