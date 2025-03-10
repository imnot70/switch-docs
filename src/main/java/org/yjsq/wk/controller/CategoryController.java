package org.yjsq.wk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yjsq.wk.bean.param.AddCategoryParam;
import org.yjsq.wk.bean.param.CategoryListParam;
import org.yjsq.wk.bean.vo.CategoryVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.service.CategoryService;

@RequestMapping("category")
@RestController
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    public Result<String> add(@RequestBody AddCategoryParam param){
        return categoryService.insertCategory(param);
    }

    @PostMapping("list")
    public Result<ListVo<CategoryVo>> list(@RequestBody CategoryListParam param){
        return categoryService.list(param);
    }

    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestParam("id") Long id){
        return categoryService.deleteCategory(id);
    }
}
