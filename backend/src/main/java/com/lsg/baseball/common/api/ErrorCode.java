package com.lsg.baseball.common.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    SUCCESS("SUCCESS", "요청을 성공했습니다", HttpStatus.OK),
    COMMON_BAD_REQUEST("COMMON_BAD_REQUEST", "요청이 올바르지 않습니다", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버에 문제가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    PLAYER_NOT_FOUND("PLAYER_NOT_FOUND", "선수를 찾을 수 없습니다", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
