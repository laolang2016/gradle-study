package com.laolang.jx;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laolang.jx.framework.common.core.R;
import com.laolang.jx.framework.common.util.JacksonUtil;
import com.laolang.jx.module.system.dict.rsp.SysDictTypeListRsp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonTest {

    @Test
    public void testOne() throws JsonProcessingException {
        SysDictTypeListRsp rsp = new SysDictTypeListRsp();
        rsp.setGroupCode("system");
        R<SysDictTypeListRsp> r = R.ok(rsp);
        System.out.println(JacksonUtil.toJson(r, true));
    }

}
