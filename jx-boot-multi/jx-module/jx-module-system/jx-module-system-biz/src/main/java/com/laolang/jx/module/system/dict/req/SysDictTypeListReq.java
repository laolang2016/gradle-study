package com.laolang.jx.module.system.dict.req;

import com.laolang.jx.framework.common.domain.BasePageReq;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictTypeListReq extends BasePageReq {
    private String name;
    private String type;
    private String groupCode;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
}
