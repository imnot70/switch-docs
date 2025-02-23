package org.yjsq.wk.service;

import org.yjsq.wk.bean.param.AddTagParam;
import org.yjsq.wk.bean.param.TagListParam;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.bean.vo.TagVo;
import org.yjsq.wk.common.Result;

public interface TagService {

    Result<String> add(AddTagParam param);

    Result<ListVo<TagVo>> list(TagListParam param);

}
