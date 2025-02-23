package org.yjsq.wk.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yjsq.wk.bean.entity.Tag;
import org.yjsq.wk.bean.param.AddTagParam;
import org.yjsq.wk.bean.param.TagListParam;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.bean.vo.TagVo;
import org.yjsq.wk.common.CommonCode;
import org.yjsq.wk.common.Constants;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.exception.BizException;
import org.yjsq.wk.mapper.TagMapper;
import org.yjsq.wk.service.TagService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Result<String> add(AddTagParam param) {
        if(StringUtils.isEmpty(param.getTagName())){
            throw new BizException(CommonCode.PARAM_IS_NULL);
        }
        Tag tag = tagMapper.selectByName(param.getTagName());
        if(tag != null){
            throw new BizException(CommonCode.TAG_EXIST);
        }
        tag = new Tag();
        Long id = IdUtil.getSnowflake().nextId();
        tag.setId(id);
        tag.setTagName(param.getTagName());
        tag.setStatus(Tag.STATUS_NORMAL);
        tag.setCreateTime(new Date());
        tag.setCreateBy("Admin");
        tagMapper.insert(tag);
        return Result.success(String.valueOf(id));
    }

    @Override
    public Result<ListVo<TagVo>> list(TagListParam param) {
        Integer pageNum = (param.getPageNum() == null || param.getPageNum() <= 0) ? Constants.PAGE_NUM : param.getPageNum();
        Integer pageSize = (param.getPageSize() == null || param.getPageSize() <= 0) ? Constants.PAGE_SIZE : param.getPageSize();
        Integer startNum = (pageNum - 1) * pageSize;
        List<Tag> tags = tagMapper.selectList(startNum, pageSize);
        if(tags == null|| tags.isEmpty()){
            ListVo<TagVo> list = new ListVo<>();
            list.setTotalCount(0);
            list.setList(new ArrayList<>());
            return Result.success(list);
        }

        List<TagVo> list = new ArrayList<>(tags.size());
        for (Tag tag : tags) {
            list.add(new TagVo(tag));
        }
        ListVo<TagVo> result = new ListVo<>();
        result.setTotalCount(tagMapper.selectCount());
        result.setList(list);
        return Result.success(result);
    }

}
