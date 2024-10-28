package me.yeunikey.contester.controller.exception;

import me.yeunikey.contester.util.ResponseBuilder;
import me.yeunikey.contester.util.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception exception) {
        throw new RuntimeException(exception);
//        return ResponseBuilder.builder()
//                .status(ResponseStatus.ERROR)
//                .message(exception.getMessage())
//                .build();
    }

}
