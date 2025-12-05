package com.lsg.baseball.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    OK("OK", HttpStatus.OK, "OK"),
    PLAYER_NOT_FOUND("PLAYER_NOT_FOUND", HttpStatus.NOT_FOUND, "선수를 찾을 수 없습니다"),
    INVALID_REQUEST("INVALID_REQUEST", HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "서버의 오류가 발생했습니다");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
