package org.yjsq.wk.bean.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFileParam {

    private Integer visible;
    private Integer downloadable;
    private String title;
    private Long ownerId;
    private String token;
    private List<Long> categoryIds;
    private List<Long> tagIds;
    private MultipartFile file;

}
