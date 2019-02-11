package com.retail.OrderOrchestration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public void handleBusinessException(BusinessException ex){
        log.error("Business Exception Details:" + ex.getMessage());
    }

    public void handleException (Exception e){
        log.error("Exception Details:" + e.getMessage());
    }
}
