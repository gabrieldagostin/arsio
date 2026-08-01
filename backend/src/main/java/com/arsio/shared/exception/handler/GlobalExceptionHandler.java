package com.arsio.shared.exception.handler;

import com.arsio.shared.exception.*;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;
import com.arsio.shared.exception.factory.ProblemDetailsFactory;
import com.arsio.shared.exception.mapper.ValidationErrorMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final ValidationErrorMapper validationErrorMapper;

    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflict(ConflictException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                ex.getStatus(),
                ex.getType(),
                "Resource Conflict",
                ex.getMessage(),
                ex.getErrorCode(),
                request
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ProblemDetail handleForbidden(ForbiddenException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                ex.getStatus(),
                ex.getType(),
                "Forbidden",
                ex.getMessage(),
                ex.getErrorCode(),
                request
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(NotFoundException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                ex.getStatus(),
                ex.getType(),
                "Resource Not Found",
                ex.getMessage(),
                ex.getErrorCode(),
                request
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail handleUnauthorized(UnauthorizedException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                ex.getStatus(),
                ex.getType(),
                "Unauthorized",
                ex.getMessage(),
                ex.getErrorCode(),
                request
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ProblemDetail handleValidatio(ValidationException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                ex.getStatus(),
                ex.getType(),
                "Validation Failed",
                ex.getMessage(),
                ex.getErrorCode(),
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {

        ProblemDetail problem = ProblemDetailsFactory.build(
                HttpStatus.BAD_REQUEST,
                ErrorTypes.GLOBAL_VALIDATION_ERROR,
                "Validation Failed",
                "One or more fields contain invalid values.",
                ErrorCode.GLOBAL_VALIDATION_ERROR,
                request
        );

        problem.setProperty(
                "errors",
                validationErrorMapper.map(ex)
        );

        return problem;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {

        ProblemDetail problem = ProblemDetailsFactory.build(
                HttpStatus.BAD_REQUEST,
                ErrorTypes.VALIDATION_ERROR,
                "Validation Failed",
                "Ono or more parameters are invalid.",
                ErrorCode.VALIDATION_ERROR,
                request
        );

        problem.setProperty(
                "errors",
                validationErrorMapper.map(ex)
        );

        return problem;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                HttpStatus.BAD_REQUEST,
                ErrorTypes.BAD_REQUEST,
                "Bad Request",
                "The request body is malformed",
                ErrorCode.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                HttpStatus.METHOD_NOT_ALLOWED,
                ErrorTypes.METHOD_NOT_ALLOWED,
                "Method Not Allowed",
                ex.getMessage(),
                ErrorCode.METHOD_NOT_ALLOWED,
                request
        );
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ProblemDetail handleHttpMediaType(HttpMediaTypeException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ErrorTypes.UNSUPPORTED_MEDIA_TYPE,
                "Http Media Type Not Supported",
                ex.getMessage(),
                ErrorCode.UNSUPPORTED_MEDIA_TYPE,
                request
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                HttpStatus.FORBIDDEN,
                ErrorTypes.FORBIDDEN,
                "Access Denied",
                "You do not have permission to access this resource.",
                ErrorCode.FORBIDDEN,
                request
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusiness(BusinessException ex, HttpServletRequest request) {
        return ProblemDetailsFactory.build(
                ex.getStatus(),
                ex.getType(),
                "Business Rule Violation",
                ex.getMessage(),
                ex.getErrorCode(),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex, HttpServletRequest request) {

        log.error(ex.getMessage(), ex);

        return ProblemDetailsFactory.build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorTypes.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "An unexpected error occurred.",
                ErrorCode.INTERNAL_SERVER_ERROR,
                request
        );
    }
}
