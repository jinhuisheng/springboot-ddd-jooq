package com.sh.common.exception;

import com.sh.common.logging.AutoNamingLoggerFactory;
import com.sh.common.utils.ResponseUtils;
import com.sh.common.utils.RestResult;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huisheng.jin
 * @version 2019/04/12
 */
@ControllerAdvice
public class GlobalExceptionInterceptor {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private static final String DEFAULT_ERROR_VIEW = "error/error";
    private static final String API_CONTAINS_PATH = "/api/";

    /**
     * 统一处理请求参数无效的情况
     * 针对情况：@Valid 验证入参对象字段不符合条件 获取BindingResult错误信息
     *
     * @param ex 方法参数无效异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult handleArgumentInvalidException(MethodArgumentNotValidException ex) {
        logger.error("------handleArgumentInvalidException ,error:\r\n {}", ex);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        //取第一个错误信息
        return RestResult.failure(errorMessages.get(0));
    }

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error("------defaultErrorHandler ,url={},error:\r\n {}", request.getRequestURL(), ex);

        if (isRestApiRequest(request)) {
            restApiErrorHandler(response, ex);
            return null;
        }
        return viewErrorHandler(request, ex);
    }

    private ModelAndView viewErrorHandler(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    private void restApiErrorHandler(HttpServletResponse response, Exception ex) {
        ResponseUtils.setResponse(response
                , HttpStatus.OK.value()
                , RestResult.failure(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", ex.getMessage()));
    }

    /**
     * 判断请求是否为RestApi请求.
     *
     * @param request 请求对象
     * @return boolean
     */
    private boolean isRestApiRequest(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url.contains(API_CONTAINS_PATH);
    }
}
