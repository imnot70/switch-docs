package org.yjsq.wk.bean.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocSearchParam extends DocListParam{

    private String keyword;

}
