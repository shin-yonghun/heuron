package com.org.heuron.application.service.model.transfer.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavePatientInfoRequest {
    @NotNull
    @Schema(description = "이름")
    String name;
    @NotNull
    @NumberFormat
    @Schema(description = "나이")
    Integer age;
    @NotNull
    @Pattern(regexp = "^(M|F)$")
    @Schema(description = "성별(M|F)", allowableValues = {"M", "F"})
    String sex;
    @NotNull
    @Pattern(regexp = "^(Y|N)$")
    @Schema(description = "질병여부(Y|N)", allowableValues = {"Y", "N"})
    String disease;
}
