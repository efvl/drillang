package app.prog.evv.drillang.exception.handler;

import app.prog.evv.drillang.exception.ApiError;
import app.prog.evv.drillang.exception.ApiErrorFactory;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException exception){
        ApiError apiError = ApiErrorFactory.createApiErrorWithTrace(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
