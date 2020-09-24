package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

 /*   @ExceptionHandler(value=IllegalArgumentException.class  )
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>"+e.getMessage()+"</h1>";
    }*/

    @ExceptionHandler(value=Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
