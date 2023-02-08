package com.org.heuron.application.service.model.transfer.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "환자 정보 조회 API RESPONSE")
public class GetPatientInfoResponse {
    @Schema(description = "이름")
    String name;
    @NumberFormat
    @Schema(description = "나이")
    Integer age;
    @Pattern(regexp = "^(M|F)$")
    @Schema(description = "성별(M|F)", allowableValues = {"M", "F"})
    String sex;
    @Pattern(regexp = "^(Y|N)$")
    @Schema(description = "질병여부(Y|N)", allowableValues = {"Y", "N"})
    String disease;
    @Schema(description = "이미지경로")
    String img;
}
