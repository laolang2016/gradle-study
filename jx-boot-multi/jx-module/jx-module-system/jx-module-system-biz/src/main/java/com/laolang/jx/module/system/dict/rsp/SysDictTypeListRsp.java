package com.laolang.jx.module.system.dict.rsp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "admin - 字典类型分页列表 rsp vo")
@Data
public class SysDictTypeListRsp {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "类型编码")
    private String type;

    @Schema(description = "类型")
    private String typeDesc;

    @Schema(description = "分组编码")
    private String groupCode;

    @Schema(description = "分组")
    private String groupDesc;

    @Schema(description = "状态编码")
    private String status;

    @Schema(description = "状态")
    private String statusDesc;

    private String profile;
}