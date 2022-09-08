package com.BridgeLabz.FundooApp.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


/*
methods to handle exceptions
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ExceptionMessage.class)
    public ResponseEntity<CustomExceptionDTO> handleCustomException(ExceptionMessage exception)
    {
        CustomExceptionDTO message = new CustomExceptionDTO(
                new Date(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }


}
