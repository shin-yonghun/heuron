package com.org.heuron.application.service.service;

import com.org.heuron.application.service.model.transfer.request.PatientRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientImgRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientInfoRequest;
import com.org.heuron.application.service.model.transfer.response.GetPatientInfoResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

public interface ApiRestService {
    Long savePatientInfo(SavePatientInfoRequest param);
    String savePatientImg(SavePatientImgRequest param);
    GetPatientInfoResponse getPatientInfo(HttpServletRequest req, Long param);
    ResponseEntity<Resource> getPatientImg(Long param);
    String deletePatient(PatientRequest param);
}
