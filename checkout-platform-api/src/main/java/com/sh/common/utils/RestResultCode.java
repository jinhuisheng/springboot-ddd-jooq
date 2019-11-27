package com.sh.common.utils;

/**
 * @author huisheng.jin
 * @version 2018/9/6.
 */
public interface RestResultCode {

    /**
     * 失败
     */
    String Failure = "0";
    /**
     * 成功
     */
    String SUCCESS = "1";

    /**
     * 发送客服消息码
     */
    String SEND_KF_ERRMSG = "2";

    /**
     * 参数有误
     */
    String REQUEST_PARAM_ERROR = "400";

    /**
     * 没有身份验证信息
     */
    String UNAUTHORIZED = "401";
    /**
     * PIN码过期
     */
    String PINTIMEOUT = "402";

    /**
     * 权限不足
     */
    String FORBIDDEN = "403";

    /**
     * 资源未找到
     */
    String NOT_FOUND = "404";

    /**
     * 内部服务错误
     */
    String INTERNAL_ERROR = "500";
}