package com.laolang.jx.framework.common.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {

    public static String toJson(Object o){
        JSONConfig jsonConfig = JSONConfig.create();
        jsonConfig.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        return JSONUtil.toJsonStr(o,jsonConfig);
    }
}
