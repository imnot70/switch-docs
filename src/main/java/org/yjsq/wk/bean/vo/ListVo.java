package org.yjsq.wk.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class ListVo<T> {

    private Integer totalCount;
    private List<T> list;

}
