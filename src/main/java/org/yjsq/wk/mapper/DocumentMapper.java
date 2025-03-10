package org.yjsq.wk.mapper;

import org.apache.ibatis.annotations.Param;
import org.yjsq.wk.bean.entity.Document;
import org.yjsq.wk.bean.param.DocCategoryRel;
import org.yjsq.wk.bean.param.DocTagRel;

import java.util.List;

public interface DocumentMapper {

    int insert(@Param("doc") Document doc);

    Integer selectListCount(@Param("ownerId") Long ownerId
            , @Param("type") String type
            , @Param("categoryId") Long categoryId
            , @Param("tagId") Long tagId);

    List<Document> selectList(@Param("ownerId") Long ownerId
            , @Param("type") String type
            , @Param("categoryId") Long categoryId
            , @Param("tagId") Long tagId
            , @Param("startNum") Integer startNum
            , @Param("pageSize") Integer pageSize);

    int removeFileById(@Param("id") Long id);

    int removeFileByIds(@Param("ids") List<Long> ids);

    Document selectById(@Param("id") Long id);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    int insertTags(@Param("items") List<DocTagRel> items);

    int insertCategories(@Param("items") List<DocCategoryRel> items);
}
