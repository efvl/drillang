package app.prog.evv.drillang.exception.handler;

import app.prog.evv.drillang.exception.ApiError;
import app.prog.evv.drillang.exception.ApiErrorFactory;
import app.prog.evv.drillang.exception.auth.TokenExpiredException;
import app.prog.evv.drillang.exception.auth.TokenValidationException;
import app.prog.evv.drillang.exception.entity.AppUserValidationException;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException exception){
        ApiError apiError = ApiErrorFactory.createApiError(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = AppUserValidationException.class)
    public ResponseEntity<Object> handleValidationException(AppUserValidationException exception){
        ApiError apiError = ApiErrorFactory.createApiError(HttpStatus.BAD_REQUEST, exception);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenException(TokenExpiredException exception){
        ApiError apiError = ApiErrorFactory.createApiError(HttpStatus.UNAUTHORIZED, exception);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = TokenValidationException.class)
    public ResponseEntity<Object> handleTokenValidationException(TokenValidationException exception){
        ApiError apiError = ApiErrorFactory.createApiError(HttpStatus.UNAUTHORIZED, exception);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
