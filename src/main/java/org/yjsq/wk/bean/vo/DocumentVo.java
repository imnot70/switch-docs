package org.yjsq.wk.bean.vo;

import lombok.Data;
import org.yjsq.wk.bean.entity.Document;

@Data
public class DocumentVo {

    private Long id;
    private Long ownerId;
    private String title;
    private Long size;
    private String extName;
    private String url;
    private Integer visible;
    private Integer downloadable;
    private Integer status;

    private String createTime;

    public DocumentVo() {
    }

    public DocumentVo(Document doc) {
        this.id = doc.getId();
        this.ownerId = doc.getOwnerId();
        this.title = doc.getTitle();
        this.size = doc.getSize();
        this.extName = doc.getExtName();
        this.visible = doc.getVisible();
        this.downloadable = doc.getDownloadable();
        this.status = doc.getStatus();
    }
}
