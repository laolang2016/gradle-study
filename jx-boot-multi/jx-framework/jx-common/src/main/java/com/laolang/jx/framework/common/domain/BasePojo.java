package com.laolang.jx.framework.common.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BasePojo {
    private Long id;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String remark;
    private Integer version;
}
