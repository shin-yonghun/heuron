package com.org.heuron.application.common.util;

import com.org.heuron.application.common.model.ResponseBase;
import com.org.heuron.configration.exeption.type.ErrorType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ResponseUtils {
    public static <T> ResponseBase onResult(T data) {
        if (null == data) {
            return ResponseBase.of(ErrorType.FAIL.getCode(), ErrorType.FAIL.getMessage(), null);
        }
        return ResponseBase.of(ErrorType.SUCCESS.getCode(), ErrorType.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseBase onError(ErrorType type) {
        return ResponseBase.of(type.SUCCESS.getCode(), type.getMessage(), null);
    }
}
