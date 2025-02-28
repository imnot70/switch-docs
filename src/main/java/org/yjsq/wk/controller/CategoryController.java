package org.yjsq.wk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yjsq.wk.bean.param.AddCategoryParam;
import org.yjsq.wk.bean.vo.CategoryVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;

@RequestMapping("category")
@RestController
public class CategoryController extends BaseController{

    @PostMapping("add")
    public Result<String> add(@RequestBody AddCategoryParam param){
        return null;
    }

    @PostMapping("list")
    public Result<ListVo<CategoryVo>> list(){
        return null;
    }
}
