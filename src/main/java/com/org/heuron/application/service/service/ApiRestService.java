package com.org.heuron.application.service.service;

import com.org.heuron.application.service.model.transfer.request.DeletePatientRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientImgRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientInfoRequest;
import com.org.heuron.application.service.model.transfer.response.GetPatientInfoResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ApiRestService {
    Long savePatientInfo(SavePatientInfoRequest param);
    String savePatientImg(SavePatientImgRequest param);
    GetPatientInfoResponse getPatientInfo(HttpServletRequest req, Long param);
    ResponseEntity<Resource> getPatientImg(String param);
    String deletePatient(DeletePatientRequest param);
}
