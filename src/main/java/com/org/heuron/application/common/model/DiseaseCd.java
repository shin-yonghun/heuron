package com.org.heuron.application.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;

@AllArgsConstructor
public enum DiseaseCd {
    NO(0,"N"),
    YES(1, "Y"),
    NONE(2, "NONE");

    @Getter
    private Integer code;
    @Getter
    private String text;

    private static DiseaseCd find(Predicate<DiseaseCd> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findAny()
                .orElse(NONE);
    }

    public static DiseaseCd fromCode(Integer code) {
        return find(e -> e.getCode().equals(code));
    }
    public static DiseaseCd fromText(String text) {
        return find(e -> e.getText().equals(text));
    }
}
