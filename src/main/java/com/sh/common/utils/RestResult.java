package com.sh.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sh.common.logging.RequestIdAwareRepresentation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


/**
 * 所有的rest接口统一使用这个对象返回结果，统一格式
 *
 * @author huisheng.jin
 * @version 2018/09/06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult<T> extends RequestIdAwareRepresentation {
    /**
     * code码,0:成功,不为0:失败
     * 系统级别的状态，应该在全局统一定义使用。
     *
     * @see RestResultCode
     */
    private String code;
    private String msg;
    private T data;

    public static <T> RestResult<T> success(T data) {
        RestResult<T> restResult = new RestResult<>();
        restResult.setCode(RestResultCode.SUCCESS);
        restResult.setMsg("SUCCESS");
        restResult.setData(data);
        return restResult;
    }

    public static <T> RestResult<T> success() {
        RestResult<T> restResult = new RestResult<>();
        restResult.setCode(RestResultCode.SUCCESS);
        restResult.setMsg("SUCCESS");
        return restResult;
    }

    public static <T> RestResult<T> failure(String message) {
        RestResult<T> restResult = new RestResult<>();
        restResult.setCode(RestResultCode.Failure);
        restResult.setMsg(message);
        return restResult;
    }

    public static <T> RestResult<T> failure(String code, String message) {
        RestResult<T> restResult = new RestResult<>();
        restResult.setCode(code);
        restResult.setMsg(message);
        return restResult;
    }

    public static <T> RestResult<T> failure(String code, String message, T data) {
        RestResult<T> restResult = failure(code, message);
        restResult.setData(data);
        return restResult;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Objects.equals(code, RestResultCode.SUCCESS);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
