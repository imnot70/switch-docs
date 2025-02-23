package org.yjsq.wk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yjsq.wk.bean.param.AddTagParam;
import org.yjsq.wk.bean.param.TagListParam;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.bean.vo.TagVo;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.service.TagService;

/**
 * TODO 权限没做
 */
@RequestMapping("tag")
@RestController
public class TagController extends BaseController{

    @Autowired
    private TagService tagService;

    @PostMapping("add")
    public Result<String> add(@RequestBody AddTagParam param){
        return tagService.add(param);
    }

    @PostMapping("list")
    public Result<ListVo<TagVo>> tagList(@RequestBody TagListParam param){
        return tagService.list(param);
    }

}
