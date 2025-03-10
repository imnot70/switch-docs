package org.yjsq.wk.bean.param;

import lombok.Data;

@Data
public class DocCategoryRel {

    private Long docId;
    private Long categoryId;

    public DocCategoryRel() {
    }

    public DocCategoryRel(Long docId, Long categoryId) {
        this.docId = docId;
        this.categoryId = categoryId;
    }
}
