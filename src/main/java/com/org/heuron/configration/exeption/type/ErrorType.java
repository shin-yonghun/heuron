package com.org.heuron.configration.exeption.type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.function.Predicate;

@FieldDefaults(level = AccessLevel.PACKAGE)
public enum ErrorType {
    UNKNOWN(-1, "unknown"),
    SUCCESS(0,  "success"),
    FAIL(1, "failed"),
    NOT_FOUND(2, "not found"),
    INVALID_PARAM(3, "invalid parameters"),
    INTERNAL_SERVER(4, "Internal server error"),

    NO_IMAGE(5,"no image"),
    INVALID_IMAGE(6,"invalid image type"),
    IMAGE_UPLOAD_FAILED(7,"failed upload image"),
    INVALID_PATIENT(8,"invalid_patient");


    @Getter
    Integer code;

    @Getter
    String message;

    ErrorType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private static ErrorType find(Predicate<ErrorType> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findAny()
                .orElse(UNKNOWN);
    }

    public static ErrorType from(Integer code) {
        return find(e -> e.getCode().equals(code));
    }
}
