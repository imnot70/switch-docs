package org.yjsq.wk.bean.vo;

import lombok.Data;
import org.yjsq.wk.bean.entity.Category;

import java.util.List;

@Data
public class CategoryVo {

    private String id;
    private String categoryName;
    private Integer level;
    private List<CategoryVo> list;

    public CategoryVo() {
    }

    public CategoryVo(Category category) {
        this.id = String.valueOf(category.getId());
        this.categoryName = category.getCategoryName();
        this.level = category.getLevel();
    }
}
