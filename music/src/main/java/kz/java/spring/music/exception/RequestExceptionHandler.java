package kz.java.spring.music.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e){
        HttpStatus badRequset = HttpStatus.BAD_REQUEST;
        AppException appException = new AppException(
                e.getMessage(),
                e,
                badRequset,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(appException, badRequset);
    }

}
