package com.employee.app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.employee.app.vo.Result;
import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
	
	

    /*@ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        if(ex instanceof ServiceException) {
            errorResponse.setDetails(((ServiceException)ex).getDetails());
        }
        if(ex instanceof ServiceHttpException) {
            return new ResponseEntity<Object>(errorResponse,((ServiceHttpException)ex).getStatus());
        } else {
            return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("path",request.getContextPath());
        responseBody.put("message","The URL you have reached is not in service at this time (404).");
        return new ResponseEntity<Object>(responseBody,HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception exception) throws JsonProcessingException {
        return null;
    }
    
    
    
	
	
	

}
