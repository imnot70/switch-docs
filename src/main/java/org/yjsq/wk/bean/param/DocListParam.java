package org.yjsq.wk.bean.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocListParam extends PageParam {

    private String type;
    private Long categoryId;
    private Long tagId;

    private Long ownerId;
    private String token;
}
