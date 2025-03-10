package org.yjsq.wk.service;

import org.yjsq.wk.bean.param.DocListParam;
import org.yjsq.wk.bean.param.UploadFileParam;
import org.yjsq.wk.bean.vo.DocumentVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.Result;

public interface FileService {

    Result<String> upload(UploadFileParam param);

    Result<ListVo<DocumentVo>> list(DocListParam param);

    Result<Boolean> deleted(Long docId, String token);

    Result<DocumentVo> preview(Long docId, String token);

    Result<String> download(Long docId, String token);
}
