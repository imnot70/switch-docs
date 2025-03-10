package org.yjsq.wk.bean.param;

import lombok.Data;

@Data
public class DocTagRel {

    private Long docId;
    private Long tagId;

    public DocTagRel() {
    }

    public DocTagRel(Long docId, Long tagId) {
        this.docId = docId;
        this.tagId = tagId;
    }
}
