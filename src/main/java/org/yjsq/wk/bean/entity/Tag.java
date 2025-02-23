package org.yjsq.wk.bean.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Tag extends BaseEntity{

    private Long id;
    private String tagName;
    private int status;

}
