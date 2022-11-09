package app.prog.evv.drillang.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ApiErrorFactory {

    public static ApiError createApiError(HttpStatus httpStatus, Exception exception){
        return ApiError.builder()
                .status(httpStatus)
                .timestamp(Instant.now())
                .message(exception.getMessage())
                .build();
    }

    public static ApiError createApiErrorWithTrace(HttpStatus httpStatus, Exception exception){
        String trace = Arrays.stream(exception.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\r\n\t "));
        return ApiError.builder()
                .status(httpStatus)
                .timestamp(Instant.now())
                .message(exception.getMessage())
                .trace(trace).build();
    }

}
