package org.yjsq.wk.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.yjsq.wk.bean.entity.Document;
import org.yjsq.wk.bean.param.DocCategoryRel;
import org.yjsq.wk.bean.param.DocListParam;
import org.yjsq.wk.bean.param.DocTagRel;
import org.yjsq.wk.bean.param.UploadFileParam;
import org.yjsq.wk.bean.vo.DocumentVo;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.common.CommonCode;
import org.yjsq.wk.common.Constants;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.common.StringConstant;
import org.yjsq.wk.exception.BizException;
import org.yjsq.wk.mapper.DocumentMapper;
import org.yjsq.wk.service.FileService;
import org.yjsq.wk.utils.SecurityUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${system.upload.dir}")
    private String updateBaseDir;

    @Autowired
    private DocumentMapper documentMapper;

    private static final String PATH_SEPARATOR = File.pathSeparator;

    @Transactional
    @Override
    public Result<String> upload(UploadFileParam param) {
        MultipartFile file = param.getFile();
        if (file == null) {
            log.info("upload, file is null");
            throw new BizException(CommonCode.PARAM_IS_NULL);
        }
        List<String> names = fileNameAndExtName(file.getOriginalFilename());
        if (names == null || names.size() < 2) {
            throw new BizException(CommonCode.ILLEGAL_FILE_NAME);
        }

        Long ownerId = param.getOwnerId();
        String title = param.getTitle();
        Date now = new Date();
        String dirName = DateUtil.format(now, StringConstant.SHORT_DATE_TIME_PATTERN);
        String uploadDirPath = createUploadDirPath(dirName, ownerId);
        File folder = new File(uploadDirPath);
        if (!folder.exists()) {
            boolean mkdirResult = folder.mkdirs();
            if (!mkdirResult) {
                throw new BizException(CommonCode.FOLDER_CREATE_FAILED);
            }
        }

        String fileName = file.getOriginalFilename();
        String fullPath = uploadDirPath + PATH_SEPARATOR + fileName;
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new BizException(CommonCode.SYSTEM_ERROR.getCode(), e.getMessage());
        }
        Document doc = new Document();
        Long id = IdUtil.getSnowflake().nextId();
        doc.setId(id);
        doc.setOwnerId(ownerId);
        doc.setTitle(StringUtils.isEmpty(title) ? names.get(0) : title);
        doc.setSize(file.getSize());
        doc.setExtName(names.get(1));
        doc.setUrl(fullPath);
        doc.setVisible(param.getVisible());
        doc.setDownloadable(param.getDownloadable());
        doc.setStatus(Document.STATUS_NORMAL);
        doc.setCreateTime(now);
        documentMapper.insert(doc);

        List<DocTagRel> tagRelList = new ArrayList<>();
        List<DocCategoryRel> categoryRelList = new ArrayList<>();
        for (Long tagId : param.getTagIds()) {
            tagRelList.add(new DocTagRel(id, tagId));
        }
        for (Long categoryId : param.getCategoryIds()) {
            categoryRelList.add(new DocCategoryRel(id, categoryId));
        }
        documentMapper.insertTags(tagRelList);
        documentMapper.insertCategories(categoryRelList);
        return Result.success(String.valueOf(id));
    }

    private List<String> fileNameAndExtName(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        int i = fileName.lastIndexOf(StringConstant.STR_DOT);
        if (i <= 0) {
            return null;
        }
        String name = fileName.substring(0, i);
        String extName = fileName.substring(i);
        List<String> names = new ArrayList<>();
        names.add(name);
        names.add(extName);
        return names;
    }

    private String createUploadDirPath(String dirName, Long ownerId) {
        return updateBaseDir + PATH_SEPARATOR + dirName + PATH_SEPARATOR + ownerId;
    }

    @Override
    public Result<ListVo<DocumentVo>> list(DocListParam param) {
        Integer totalCount = documentMapper.selectListCount(param.getOwnerId(), param.getType(), param.getCategoryId(), param.getTagId());
        ListVo<DocumentVo> result = new ListVo<>();
        if (totalCount == null || totalCount <= 0) {
            result.setTotalCount(0);
            result.setList(new ArrayList<>());
            return Result.success(result);
        }

        result.setTotalCount(totalCount);
        int pageNum = param.getPageNum() == null || param.getPageNum() <= 0 ? Constants.PAGE_NUM : param.getPageNum();
        int pageSize = param.getPageSize() == null || param.getPageSize() <= 0 ? Constants.PAGE_SIZE : param.getPageSize();
        int startNum = (pageNum - 1) * pageSize;
        List<Document> documents = documentMapper.selectList(param.getOwnerId(), param.getType(), param.getCategoryId(), param.getTagId(), startNum, pageSize);
        List<DocumentVo> resultList = new ArrayList<>();
        for (Document document : documents) {
            DocumentVo item = new DocumentVo(document);
            if(document.getCreateTime() != null){
                item.setCreateTime(DateUtil.format(document.getCreateTime(), StringConstant.DEFAULT_TIME_PATTERN));
            }
            resultList.add(item);
        }
        result.setList(resultList);
        return Result.success(result);
    }

    @Override
    public Result<Boolean> deleted(Long docId, String token) {
        Document document = documentMapper.selectById(docId);
        if(document == null){
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        if(document.getOwnerId().equals(SecurityUtil.getIdFromToken(token))){
            throw new BizException(CommonCode.PERMISSION_DENIED);
        }
        documentMapper.removeFileById(docId);
        return Result.success(true);
    }

    @Override
    public Result<DocumentVo> preview(Long docId, String token) {

        return null;
    }

    @Override
    public Result<String> download(Long docId, String token) {
        Document document = documentMapper.selectById(docId);
        if(document == null){
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        if(document.getOwnerId().equals(SecurityUtil.getIdFromToken(token))){
            throw new BizException(CommonCode.PERMISSION_DENIED);
        }
        // 直接返回文件在服务器的路径，由前端拼接ip/域名进行下载
        return Result.success(document.getUrl());
    }

}
