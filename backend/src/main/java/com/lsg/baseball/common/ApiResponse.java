package com.lsg.baseball.common;

import com.lsg.baseball.common.error.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiResponse<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage(), data);
    }

    public  static <T> ApiResponse<T> ok(ErrorCode code, T data) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), data);
    }

    public static <T> ApiResponse<T> error(ErrorCode code) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(ErrorCode code, String overrideMessage) {
        return new ApiResponse<>(false, code.getCode(), overrideMessage, null);
    }
}
