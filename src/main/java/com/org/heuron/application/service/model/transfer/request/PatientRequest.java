package com.org.heuron.application.service.model.transfer.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientRequest {
    @NotNull
    @NumberFormat
    @Schema(description = "환자고유번호")
    Long psn;
}
