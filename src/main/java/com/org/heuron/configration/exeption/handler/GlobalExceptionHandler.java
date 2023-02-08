package com.org.heuron.configration.exeption.handler;

import com.org.heuron.application.common.model.ResponseBase;
import com.org.heuron.configration.exeption.CommonException;
import com.org.heuron.configration.exeption.type.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
@Order(Ordered.LOWEST_PRECEDENCE)
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseBase handleCommonException(HttpServletRequest request, CommonException ex) {
        log.error("Common Exception: request: {}, error-code: {}, error-msg: {}", request, ex.getCode(), ex.getMessage());
        return ResponseBase.of(ex.getCode(), ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseBase handleException(HttpServletRequest request, Exception ex) {
        log.error("Common Exception: request: {}, error-msg: {}", request, ex.getMessage());
        return ResponseBase.of(ErrorType.INTERNAL_SERVER.getCode(), ErrorType.INTERNAL_SERVER.getMessage(), null);
    }
}
