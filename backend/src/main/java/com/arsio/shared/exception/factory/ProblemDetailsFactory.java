package com.arsio.shared.exception.factory;

import com.arsio.shared.exception.enums.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.Instant;

public final class ProblemDetailsFactory {

    private ProblemDetailsFactory() {}

    public static ProblemDetail build(
            HttpStatus status,
            String type,
            String title,
            String details,
            ErrorCode errorCode,
            HttpServletRequest request
    ) {

        ProblemDetail problem = ProblemDetail.forStatus(status);

        problem.setType(URI.create(type));
        problem.setTitle(title);
        problem.setDetail(details);
        problem.setInstance(URI.create(request.getRequestURI()));

        problem.setProperty("Timestamp", Instant.now());
        problem.setProperty("ErrorCode", errorCode);
        problem.setProperty("TraceId", MDC.get("traceId"));

        return problem;
    }
}
