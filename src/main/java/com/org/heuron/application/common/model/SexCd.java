package com.org.heuron.application.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;

@AllArgsConstructor
public enum SexCd {
    NONE(0,"NONE"),
    MALE(1, "M"),
    FEMALE(2, "F");

    @Getter
    private Integer code;
    @Getter
    private String text;

    private static SexCd find(Predicate<SexCd> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findAny()
                .orElse(NONE);
    }

    public static SexCd fromCode(Integer code) {
        return find(e -> e.getCode().equals(code));
    }
    public static SexCd fromText(String text) {
        return find(e -> e.getText().equals(text));
    }
}
