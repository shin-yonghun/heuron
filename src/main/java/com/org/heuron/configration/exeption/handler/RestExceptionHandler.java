package com.org.heuron.configration.exeption.handler;

import com.org.heuron.application.common.model.ResponseBase;
import com.org.heuron.configration.exeption.CommonException;
import com.org.heuron.configration.exeption.type.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class RestExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ResponseBase handleCommonException(CommonException ex) {
        log.error("Common Exception: {}", ex.getMessage());
        return ResponseBase.of(ex.getCode(), ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBase handleException(Exception ex) {
        log.error("Exception: {} ", ex.getMessage());
        return ResponseBase.of(ErrorType.INTERNAL_SERVER.getCode(), ErrorType.INTERNAL_SERVER.getMessage(), null);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseBase handleBindException(BindException ex) {
        log.error("Bind Exception: {} ", ex.getMessage());
        return ResponseBase.of(ErrorType.INVALID_PARAM.getCode(), ErrorType.INVALID_PARAM.getMessage(), null);
    }

}
