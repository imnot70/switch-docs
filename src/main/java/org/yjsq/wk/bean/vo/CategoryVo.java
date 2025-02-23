package org.yjsq.wk.bean.vo;

import lombok.Data;
import org.yjsq.wk.bean.entity.Category;

import java.util.List;

@Data
public class CategoryVo {

    private String id;
    private String categoryName;
    private List<Category> list;

}
