package com.sh.common.exception;

import java.util.Map;

import static com.sh.common.exception.ErrorCode.REQUEST_VALIDATION_FAILED;

public class RequestValidationException extends AppException {
    public RequestValidationException(Map<String, Object> data) {
        super(REQUEST_VALIDATION_FAILED, data);
    }
}
