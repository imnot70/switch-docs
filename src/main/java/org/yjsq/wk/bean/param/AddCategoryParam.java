package org.yjsq.wk.bean.param;

import lombok.Data;

@Data
public class AddCategoryParam {

    private Long parentId;
    private String categoryName;

}
