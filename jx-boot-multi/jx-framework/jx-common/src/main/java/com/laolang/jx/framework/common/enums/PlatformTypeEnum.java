package com.laolang.jx.framework.common.enums;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlatformTypeEnum {
    pc_platform("01", "pc平台端"),
    pc_seller("02", "pc商家端"),
    pc_portal("03", "pc前台端"),
    mobile("11", "移动端"),
    h5("21", "h5"),
    wx_mini_app("31", "微信小程序");

    /**
     * 平台代码.
     */
    private final String code;

    /**
     * 平台描述.
     */
    private final String desc;

    private static final Map<String, PlatformTypeEnum> code_map = Maps.newHashMap();


    static {
        for (PlatformTypeEnum e : values()) {
            code_map.put(e.getCode(), e);
        }
    }

    public static PlatformTypeEnum getByCode(String code) {
        return code_map.get(code);
    }
}
