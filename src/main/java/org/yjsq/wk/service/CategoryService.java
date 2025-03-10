package org.yjsq.wk.service;

import org.yjsq.wk.bean.param.AddCategoryParam;
import org.yjsq.wk.bean.param.CategoryListParam;
import org.yjsq.wk.bean.vo.CategoryVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;

import java.util.List;

public interface CategoryService {

    Result<ListVo<CategoryVo>> list(CategoryListParam param);

    Result<CategoryVo> selectById(Long Id);

    Result<List<CategoryVo>> selectTopLevelCategory();

    Result<String> insertCategory(AddCategoryParam param);

    Result<Boolean> deleteCategory(Long id);

}
