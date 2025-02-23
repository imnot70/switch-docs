package org.yjsq.wk.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yjsq.wk.bean.param.DocListParam;
import org.yjsq.wk.bean.vo.DocumentVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.service.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public Result<String> upload(List<Long> categoryIds, List<Long> tagIds, MultipartFile file) {
        return null;
    }

    @Override
    public Result<ListVo<DocumentVo>> list(DocListParam param) {
        return null;
    }

    @Override
    public Result<Boolean> deleted(Long docId) {
        return null;
    }

    @Override
    public Result<DocumentVo> preview(Long docId) {
        return null;
    }
}
