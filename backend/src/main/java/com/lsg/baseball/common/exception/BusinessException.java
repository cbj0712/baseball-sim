package com.lsg.baseball.common.exception;

import com.lsg.baseball.common.api.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String detailMessage;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = null;
    }

    public BusinessException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause, String detailMessage) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
