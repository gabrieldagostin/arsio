package com.arsio.shared.exception;

import com.arsio.shared.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {

    private HttpStatus status;
    private String type;
    private ErrorCode errorCode;

    protected BusinessException(String message, HttpStatus status, String type, ErrorCode errorCode) {
        super(message);
        this.status = status;
        this.type = type;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
