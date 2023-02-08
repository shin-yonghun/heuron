package com.org.heuron.application.service.controller;

import com.org.heuron.application.common.model.ResponseBase;
import com.org.heuron.application.common.util.ResponseUtils;
import com.org.heuron.application.service.model.transfer.request.DeletePatientRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientImgRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientInfoRequest;
import com.org.heuron.application.service.service.ApiRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Tag(name = "REST API 목록")
@RestController
@RequestMapping("/heuron/api")
@RequiredArgsConstructor
public class ApiRestController {

    private final ApiRestService apiRestService;

    @PostMapping("/patient_info")
    @Operation(summary = "환자 정보 등록 API", description = "환자 정보를 등록한다.")
    @ApiResponse(responseCode = "200", description = "응답")
    public ResponseBase savePatientInfo(@Valid @RequestBody SavePatientInfoRequest param) {

        return ResponseUtils.onResult(apiRestService.savePatientInfo(param));
    }

    @PostMapping(value = "/patient_img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "환자 이미지 등록 API", description = "환자 이미지를 등록한다.")
    @ApiResponse(responseCode = "200", description = "응답")
    public ResponseBase savePatientImg(@Valid SavePatientImgRequest param) {

        return ResponseUtils.onResult(apiRestService.savePatientImg(param));
    }

    @GetMapping(value = "/patient_info/{psn}")
    @Operation(summary = "환자 정보 조회 API", description = "환자 정보를 조회한다.")
    @ApiResponse(responseCode = "200", description = "응답")
    public ResponseBase getPatientInfo(HttpServletRequest req,
                                       @Schema(description = "환자고유번호") @PathVariable(name = "psn") Long param) {

        return ResponseUtils.onResult(apiRestService.getPatientInfo(req,param));
    }

    @GetMapping(value = "/patient_img/{img}")
    @Operation(summary = "환자 이미지 조회 API", description = "환자 이미지를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "환자 이미지 조회 성공"),
            @ApiResponse(responseCode = "404", description = "환자 이미지가 존재하지 않음")
    })
    public ResponseEntity<Resource> getPatientImg(@Schema(description = "이미지이름") @PathVariable(name = "img") String param) {
        return apiRestService.getPatientImg(param);
    }

    @DeleteMapping(value = "/patient")
    @Operation(summary = "환자 삭제 API", description = "환자 정보/이미지를 삭제한다.")
    @ApiResponse(responseCode = "200", description = "응답")
    public ResponseBase deletePatient(@Valid @RequestBody DeletePatientRequest param) {

        return ResponseUtils.onResult(apiRestService.deletePatient(param));
    }



}
