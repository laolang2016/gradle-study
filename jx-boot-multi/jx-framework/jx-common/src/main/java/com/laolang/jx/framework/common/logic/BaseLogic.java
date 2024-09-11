package com.laolang.jx.framework.common.logic;

import cn.hutool.core.util.StrUtil;
import com.laolang.jx.framework.common.consts.GlobalConst;
import com.laolang.jx.framework.common.enums.PlatformTypeEnum;
import javax.servlet.http.HttpServletRequest;

public class BaseLogic {

    protected PlatformTypeEnum getPlatformType(HttpServletRequest request) {
        String platformTypeCode = request.getHeader(GlobalConst.PLATFORM_TYPE_HEADER_KEY);
        return StrUtil.isBlank(platformTypeCode) ? null : PlatformTypeEnum.getByCode(platformTypeCode);
    }
}
