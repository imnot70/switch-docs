package org.yjsq.wk.bean.vo;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.yjsq.wk.bean.entity.Tag;
import org.yjsq.wk.common.StringConstant;

@Data
public class TagVo {

    private Long id;
    private String tagName;
    private String status;

    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;

    public TagVo() {
    }

    public TagVo(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
        this.status = String.valueOf(tag.getStatus());

        this.createBy = tag.getCreateBy();
        this.updateBy = tag.getUpdateBy();
        if (tag.getCreateTime() != null) {
            this.createTime = DateUtil.format(tag.getCreateTime(), StringConstant.DATE_ONLY_PATTERN);
        }
        if (tag.getUpdateTime() != null) {
            this.updateTime = DateUtil.format(tag.getUpdateTime(), StringConstant.DATE_ONLY_PATTERN);
        }
    }
}
