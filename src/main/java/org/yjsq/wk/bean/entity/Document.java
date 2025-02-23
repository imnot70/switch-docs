package org.yjsq.wk.bean.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Document extends BaseEntity {

    private Long id;
    private Long ownerId;
    private String title;
    private Long size;
    private String extName;
    private String url;
    private Integer visible;
    private Integer downloadable;
    private Integer status;

}
