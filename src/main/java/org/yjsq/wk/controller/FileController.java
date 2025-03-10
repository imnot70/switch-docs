package org.yjsq.wk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yjsq.wk.bean.param.DocListParam;
import org.yjsq.wk.bean.param.UploadFileParam;
import org.yjsq.wk.bean.vo.DocumentVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.service.FileService;
import org.yjsq.wk.utils.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("file")
public class FileController extends BaseController{

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Result<String> upload(@RequestParam("visible") Integer visible
            , @RequestParam("downloadable") Integer downloadable
            , @RequestParam("title") String title
            , @RequestParam("categoryIds") List<Long> categoryIds
            , @RequestParam("tagIds") List<Long> tagIds
            , @RequestPart("file") MultipartFile file){

        UploadFileParam param = new UploadFileParam();
        param.setVisible(visible);
        param.setDownloadable(downloadable);
        param.setTitle(title);
        param.setCategoryIds(categoryIds);
        param.setTagIds(tagIds);
        param.setFile(file);
        String token = getToken();
        param.setToken(token);
        param.setOwnerId(SecurityUtil.getIdFromToken(token));
        return fileService.upload(param);
    }

    @PostMapping("list")
    public Result<ListVo<DocumentVo>> list(@RequestBody DocListParam param){
        String token = getToken();
        param.setToken(token);
        param.setOwnerId(SecurityUtil.getIdFromToken(token));
        return fileService.list(param);
    }

    @DeleteMapping("delete")
    public Result<Boolean> deleted(@RequestParam("id") Long docId){
        return fileService.deleted(docId, getToken());
    }

    @GetMapping("preview")
    public Result<DocumentVo> preview(@RequestParam("id") Long docId){
        return null;
    }

    @GetMapping("download")
    public Result<String> download(@RequestParam("id") Long docId){
        return fileService.download(docId, getToken());
    }
}
