package com.laolang.jx.framework.common.domain;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laolang.jx.framework.common.consts.DefaultStatusCode;
import com.laolang.jx.framework.common.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.MDC;

@Schema(description = "通用返回结构")
@Accessors(chain = true)
@Data
public class R<T> {

    /**
     * 接口请求结果的业务状态吗.
     */
    @Schema(description = "接口请求结果的业务状态吗")
    private String code;

    /**
     * 判断接口请求是否成功的唯一标识.
     */
    @Schema(description = "判断接口请求是否成功的唯一标识")
    private Boolean success;

    /**
     * 提示信息.
     */
    @Schema(description = "提示信息")
    private String msg;

    /**
     * 数据体.
     */
    @Schema(description = "数据体")
    private T body;

    /**
     * 扩充字段,正常情况下此字段为空，当此字段有值时，意味着当前接口结构不稳定，以后会修改,即保持 extra 为空.
     */
    @Schema(description = "扩充字段,正常情况下此字段为空，当此字段有值时，意味着当前接口结构不稳定，以后会修改,即保持 extra 为空")
    private Map<String, Object> extra;

    /**
     * traceId
     */
    @Schema(description = "traceId")
    private String tid;

    /**
     * 接口返回时间
     */
    @Schema(description = "接口返回时间")
    private LocalDateTime time;

    public static <T> R<T> build(String code, boolean success, String msg, T body) {
        R<T> ajax = new R<>();
        ajax.setCode(code);
        ajax.setSuccess(success);
        ajax.setMsg(msg);
        ajax.setBody(body);
        ajax.setExtra(null);
        ajax.setTid(getTraceId());
        ajax.time = LocalDateTime.now();

        return ajax;
    }

    private static String getTraceId() {
        String tl = MDC.get("tl");
        if (StrUtil.isBlank(tl)) {
            return "";
        }

        List<String> split = StrUtil.split(tl, '<');
        if (CollUtil.isEmpty(split) || 3 != split.size()) {
            return "";
        }

        String tid = split.get(2);
        return tid.substring(0, tid.length() - 1);
    }

    @JsonIgnore
    public void setPropFromBusinessException(BusinessException e) {
        setMsg(e.getMsg());
        setCode(e.getCode());
        setSuccess(false);
    }

    public static <T> R<T> ok() {
        return ok(DefaultStatusCode.OK.getCode(), DefaultStatusCode.OK.getMsg());
    }

    public static <T> R<T> ok(String code, String msg) {
        return ok(code, msg, null);
    }

    public static <T> R<T> ok(String code, String msg, T body) {
        return build(code, true, msg, body);
    }

    public static <T> R<T> ok(T body) {
        return build(DefaultStatusCode.OK.getCode(), true, DefaultStatusCode.OK.getMsg(), body);
    }

    public static <T> R<T> failed() {
        return failed(DefaultStatusCode.FAILED.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return build(DefaultStatusCode.FAILED.getCode(), false, msg, null);
    }

    public static <T> R<T> error() {
        return error(DefaultStatusCode.ERROR.getMsg());
    }

    public static <T> R<T> error(String msg) {
        return error(DefaultStatusCode.ERROR.getCode(), msg);
    }

    public static <T> R<T> error(String code, String msg) {
        return build(code, false, msg, null);
    }

    public static <T> R<T> notFound() {
        return notFound(DefaultStatusCode.NOT_FOUND.getMsg());
    }

    public static <T> R<T> notFound(String msg) {
        return build(DefaultStatusCode.NOT_FOUND.getCode(), false, msg, null);
    }

    public static <T> R<T> unauthorized() {
        return build(DefaultStatusCode.UNAUTHORIZED.getCode(), false, DefaultStatusCode.UNAUTHORIZED.getMsg(), null);
    }
}
