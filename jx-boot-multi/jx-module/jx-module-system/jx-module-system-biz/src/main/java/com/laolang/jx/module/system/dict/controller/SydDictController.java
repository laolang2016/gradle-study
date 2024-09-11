package com.laolang.jx.module.system.dict.controller;

import com.laolang.jx.framework.common.domain.R;
import com.laolang.jx.module.system.dict.logic.SysDictLogic;
import com.laolang.jx.module.system.dict.req.SysDictTypeListReq;
import com.laolang.jx.module.system.dict.rsp.SysDictTypeListRsp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin - 字典管理")
@RequiredArgsConstructor
@RequestMapping("admin/system/dict")
@RestController
public class SydDictController {

    private final SysDictLogic sysDictLogic;

    @PostMapping("type/list")
    public R<SysDictTypeListRsp> typeList(@RequestBody SysDictTypeListReq req) {
        return R.ok(sysDictLogic.typeList(req));
    }

}
