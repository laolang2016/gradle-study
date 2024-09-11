package com.laolang.jx.framework.web.util;

import cn.hutool.json.JSONUtil;
import com.laolang.jx.framework.common.domain.R;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServletUtil {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

    public static void writeJson(HttpServletResponse response, R<?> r) {
        writeJson(response, JSONUtil.toJsonStr(r));
    }

    public static void writeJson(HttpServletResponse response, String json) {
        response.reset();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(APPLICATION_JSON_UTF8);
        try (PrintWriter out = response.getWriter()) {
            out.append(json);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
