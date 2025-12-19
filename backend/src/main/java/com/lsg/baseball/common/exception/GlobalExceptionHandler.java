package com.lsg.baseball.common.exception;

import com.lsg.baseball.common.api.ApiResponse;
import com.lsg.baseball.common.api.ErrorCode;
import com.lsg.baseball.common.api.ValidationErrorData;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        log.warn("[BusinessException] code={}, message={}, detail={}",
                errorCode.getCode(), errorCode.getMessage(), ex.getDetailMessage(), ex);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        log.error("[UnexpectedException] {}", ex.getMessage(), ex);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<ValidationErrorData>> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        var items = ex.getConstraintViolations()
                .stream()
                .map(v -> {
                        String path = String.valueOf(v.getPropertyPath());
                        String field = extractLastPathToken(path);

                        return ValidationErrorData.FieldErrorItem.builder()
                                .field(field)
                                .reason(v.getMessage())
                                .build();
                    }
                )
                .toList();

        var data = ValidationErrorData.builder()
                .errors(items)
                .build();

        log.warn("[ConstraintViolationException] count={}", items.size(), ex);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode, data));
    }

    private String extractLastPathToken(String path) {
        if (path == null || path.isBlank()) {
            return "unknown";
        }

        int idx = path.lastIndexOf('.');

        return (idx >= 0 && idx < path.length() - 1) ? path.substring(idx + 1) : path;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("[HttpMessageNotReadableException] {}", ex.getMessage(), ex);

        ErrorCode errorCode = ErrorCode.COMMON_BAD_REQUEST;

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ValidationErrorData>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        var items = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> ValidationErrorData.FieldErrorItem.builder()
                        .field(fe.getField())
                        .reason(fe.getDefaultMessage())
                        .build()
                )
                .toList();

        var data = ValidationErrorData.builder()
                .errors(items)
                .build();

        log.warn("[ValidationError] count={}", items.size(), ex);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode, data));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorCode errorCode = ErrorCode.COMMON_BAD_REQUEST;

        log.warn("[TypeMismatch] name={}, value={}, requiredType={}",
                ex.getName(), ex.getValue(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown",
                ex);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String rootMsg = getRootMessage(ex).toLowerCase();

        log.warn("[DataIntegrityViolationException] rootMessage={}", rootMsg, ex);

        ErrorCode errorcode = mapDataIntegrityErrorCode(rootMsg);

        return ResponseEntity
                .status(errorcode.getHttpStatus())
                .body(ApiResponse.error(errorcode));
    }

    private String getRootMessage(Throwable ex) {
        Throwable root = ex;

        while (root.getCause() != null && root.getCause() != root) {
            root = root.getCause();
        }

        return String.valueOf(root.getMessage());
    }

    private ErrorCode mapDataIntegrityErrorCode(String rootMsg) {
        if (rootMsg.contains("uk_players_team_uniform")) {
            return ErrorCode.DUPLICATE_UNIFORM_NUMBER_IN_TEAM;
        }

        return ErrorCode.INTERNAL_SERVER_ERROR;
    }
}
