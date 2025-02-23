package org.yjsq.wk.bean.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends BaseEntity{

    private Long id;
    private Long parentId;
    private String categoryName;

}
