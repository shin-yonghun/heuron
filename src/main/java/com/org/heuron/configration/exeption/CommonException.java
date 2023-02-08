package com.org.heuron.configration.exeption;

import com.org.heuron.configration.exeption.type.ErrorType;
import lombok.Getter;

public class CommonException extends RuntimeException{
    @Getter
    private Integer code;
    @Getter
    private String message;

    public CommonException(ErrorType errorType) {
        super();
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
    }
}
