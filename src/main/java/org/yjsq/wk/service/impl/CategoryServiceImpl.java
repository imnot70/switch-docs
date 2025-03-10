package org.yjsq.wk.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yjsq.wk.bean.entity.Category;
import org.yjsq.wk.bean.param.AddCategoryParam;
import org.yjsq.wk.bean.param.CategoryListParam;
import org.yjsq.wk.bean.vo.CategoryVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.CommonCode;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.exception.BizException;
import org.yjsq.wk.mapper.CategoryMapper;
import org.yjsq.wk.service.CategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result<ListVo<CategoryVo>> list(CategoryListParam param) {
        List<Category> categories;
        if(param.getLevel() != 0 && param.getParentId() != null){
            categories = categoryMapper.selectListByParentId(param.getParentId());
        }else{
            categories = categoryMapper.selectTopLevelList();
        }
        ListVo<CategoryVo> result = new ListVo<>();
        if(CollectionUtils.isEmpty(categories)){
            result.setTotalCount(0);
            result.setList(new ArrayList<>());
            return Result.success(result);
        }
        List<CategoryVo> resultList = new ArrayList<>(categories.size());
        for (Category category : categories) {
            resultList.add(new CategoryVo(category));
        }
        result.setTotalCount(categories.size());
        result.setList(resultList);
        return Result.success(result);
    }

    @Override
    public Result<CategoryVo> selectById(Long id) {
        Category category = categoryMapper.selectById(id);
        if (category == null) {
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        CategoryVo resultCategory = new CategoryVo(category);
        List<Category> categories = categoryMapper.selectListByParentId(id);
        if (CollectionUtils.isNotEmpty(categories)) {
            List<CategoryVo> subCategories = new ArrayList<>(categories.size());
            for (Category subCategory : categories) {
                subCategories.add(new CategoryVo(subCategory));
            }
            resultCategory.setList(subCategories);
        }
        return Result.success(resultCategory);
    }

    @Override
    public Result<List<CategoryVo>> selectTopLevelCategory() {
        List<Category> categories = categoryMapper.selectTopLevelList();
        if (CollectionUtils.isEmpty(categories)) {
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        List<CategoryVo> resultList = new ArrayList<>(categories.size());
        for (Category category : categories) {
            resultList.add(new CategoryVo(category));
        }
        return Result.success(resultList);
    }

    @Override
    public Result<String> insertCategory(AddCategoryParam param) {
        Category parent = categoryMapper.selectById(param.getParentId());
        if (parent == null) {
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        Category dataInDb = categoryMapper.selectByParentIdAndName(param.getParentId(), parent.getCategoryName());
        if (dataInDb != null) {
            throw new BizException(CommonCode.CATEGORY_EXIST);
        }
        Integer parentLevel = parent.getLevel();
        Category category = new Category();
        long id = IdUtil.getSnowflakeNextId();
        category.setId(id);
        category.setCategoryName(param.getCategoryName());
        category.setLevel(parentLevel + 1);
        category.setCreateTime(new Date());
        categoryMapper.insert(category);
        return Result.success(String.valueOf(id));
    }

    @Override
    public Result<Boolean> deleteCategory(Long id) {
        categoryMapper.deleteById(id);
        return Result.success(true);
    }
}
