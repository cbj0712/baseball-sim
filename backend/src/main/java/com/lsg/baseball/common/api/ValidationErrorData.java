package com.lsg.baseball.common.api;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ValidationErrorData {
    private final List<FieldErrorItem> errors;

    @Getter
    @Builder
    public static class FieldErrorItem {
        private final String field;
        private final String reason;
    }
}
