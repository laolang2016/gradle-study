package com.laolang.jx.framework.common.consts;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalConst {
    /**
     * 管理员用户ID
     */
    public static final Long ADMIN_ID = 1L;

    /**
     * 超级管理员账号
     */
    public static final String ADMIN_ACCOUNT = "superAdmin";

    /**
     * platform-type: 访问者所在平台
     * 01:pc平台端 02:pc商家端 03:pc前台端 11:移动端 21:h5 31:微信小程序
     */
    public static final String PLATFORM_TYPE_HEADER_KEY = "platform-type";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * jwt token claims 中的 user key
     */
    public static final String LOGIN_USER_KEY = "login_user_key";
}
