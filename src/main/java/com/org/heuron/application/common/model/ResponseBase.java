package com.org.heuron.application.common.model;

import com.org.heuron.configration.exeption.type.ErrorType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ResponseBase<T> {
    Integer code;
    String message;
    T payload;
}
