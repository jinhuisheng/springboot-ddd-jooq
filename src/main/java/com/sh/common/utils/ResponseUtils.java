package com.sh.common.utils;

import com.alibaba.fastjson.JSON;
import com.sh.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author huisheng.jin
 * @version 2019/5/31.
 */
public class ResponseUtils {
    private static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();


    public static void setResponse(HttpServletResponse response, Integer httpStatus, RestResult restResult) {
        response.setContentType(JSON_CONTENT_TYPE);
        response.setStatus(httpStatus);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(restResult));
            writer.close();
        } catch (IOException e) {
            logger.error("--- setResponse---,writer print error :{}", e);
        } finally {
            if (null != writer) {
                writer.flush();
                writer.close();
            }
        }
    }
}
