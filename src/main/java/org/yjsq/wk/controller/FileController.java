package org.yjsq.wk.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yjsq.wk.bean.param.DocListParam;
import org.yjsq.wk.bean.vo.DocumentVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;

import java.util.List;

@RestController
@RequestMapping("file")
public class FileController extends BaseController{

    @PostMapping("upload")
    public Result<String> upload(List<Long> categoryIds, List<Long> tagIds, MultipartFile file){
        return null;
    }

    @PostMapping("list")
    public Result<ListVo<DocumentVo>> list(DocListParam param){
        return null;
    }

    @DeleteMapping("delete")
    public Result<Boolean> deleted(Long docId){
        return null;
    }

    @GetMapping("preview")
    public Result<DocumentVo> preview(Long docId){
        return null;
    }

}
