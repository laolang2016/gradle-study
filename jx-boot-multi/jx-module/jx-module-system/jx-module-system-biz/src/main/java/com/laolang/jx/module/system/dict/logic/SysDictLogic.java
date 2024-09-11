package com.laolang.jx.module.system.dict.logic;

import com.laolang.jx.framework.common.util.SpringUtils;
import com.laolang.jx.module.system.dict.req.SysDictTypeListReq;
import com.laolang.jx.module.system.dict.rsp.SysDictTypeListRsp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SysDictLogic {

    public SysDictTypeListRsp typeList(SysDictTypeListReq req) {
        log.info("sys dict type list");

        SysDictTypeListRsp rsp = new SysDictTypeListRsp();

        rsp.setId(1001L);
        rsp.setName(req.getName());
        rsp.setGroupCode(req.getGroupCode() + " rsp");
        rsp.setProfile(SpringUtils.getActiveProfile());

        return rsp;
    }
}
