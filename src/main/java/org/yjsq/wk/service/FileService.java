package org.yjsq.wk.service;

import org.springframework.web.multipart.MultipartFile;
import org.yjsq.wk.bean.param.DocListParam;
import org.yjsq.wk.bean.vo.DocumentVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;

import java.util.List;

public interface FileService {

    Result<String> upload(List<Long> categoryIds, List<Long> tagIds, MultipartFile file);

    Result<ListVo<DocumentVo>> list(DocListParam param);

    Result<Boolean> deleted(Long docId);

    Result<DocumentVo> preview(Long docId);

}
