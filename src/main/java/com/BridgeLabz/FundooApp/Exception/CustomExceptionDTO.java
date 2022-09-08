package com.BridgeLabz.FundooApp.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

/*
Exception model class
 */
@Data
@AllArgsConstructor
public class CustomExceptionDTO {
    private Date timestamp;
    private String message;
    private HttpStatus statusCode;

}
