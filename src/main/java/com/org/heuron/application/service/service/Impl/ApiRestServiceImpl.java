package com.org.heuron.application.service.service.Impl;

import com.org.heuron.application.common.model.DiseaseCd;
import com.org.heuron.application.common.model.SexCd;
import com.org.heuron.application.service.ImgUtils;
import com.org.heuron.application.service.model.entity.PatientMt;
import com.org.heuron.application.service.model.repository.PatientMtRepository;
import com.org.heuron.application.service.model.transfer.request.PatientRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientImgRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientInfoRequest;
import com.org.heuron.application.service.model.transfer.response.GetPatientInfoResponse;
import com.org.heuron.application.service.service.ApiRestService;
import com.org.heuron.configration.exeption.CommonException;
import com.org.heuron.configration.exeption.type.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiRestServiceImpl implements ApiRestService {

    private final PatientMtRepository patientMtRepository;
    private final ImgUtils imgUtils;

    @Override
    public Long savePatientInfo(SavePatientInfoRequest param) {
        PatientMt patientMt = new PatientMt();
        patientMt.setName(param.getName());
        patientMt.setAge(param.getAge());
        patientMt.setSex(SexCd.fromText(param.getSex()).getCode());
        patientMt.setDisease(SexCd.fromText(param.getDisease()).getCode());
        patientMt.setImgNm("");

        return patientMtRepository.save(patientMt).getPsn();
    }

    @Override
    @Transactional
    public String savePatientImg(SavePatientImgRequest param) {
        return patientMtRepository.findById(param.getPsn()).map(info -> {
            String imgNm = "";
            if(param.getImg() == null) {
                throw new CommonException(ErrorType.NO_IMAGE);
            }

            if(!info.getImgNm().equals("")) imgUtils.deleteImage(info.getImgNm());

            try {
                imgNm = imgUtils.storeFile(param.getImg());
            } catch (IOException e) {
                throw new CommonException(ErrorType.IMAGE_UPLOAD_FAILED);
            }
            info.setImgNm(imgNm);
            return imgUtils.getFullPath(imgNm);
        }).orElseThrow(() -> new CommonException(ErrorType.INVALID_PATIENT));
    }

    @Override
    public GetPatientInfoResponse getPatientInfo(HttpServletRequest req, Long param) {
        return patientMtRepository.findById(param).map(info -> {
            if(info.getImgNm().equals("")) throw new CommonException(ErrorType.NO_IMAGE);
            GetPatientInfoResponse res = new GetPatientInfoResponse();
            res.setName(info.getName());
            res.setAge(info.getAge());
            res.setSex(SexCd.fromCode(info.getSex()).getText());
            res.setDisease(DiseaseCd.fromCode(info.getDisease()).getText());
            res.setImg(req.getRequestURL().toString().replace("patient_info","patient_img"));
            return res;
        }).orElseThrow(() -> new CommonException(ErrorType.INVALID_PATIENT));
    }

    @Override
    public ResponseEntity<Resource> getPatientImg(Long param) {
        return patientMtRepository.findById(param).map(info -> {
            if(info.getImgNm().equals("")) throw new CommonException(ErrorType.NO_IMAGE);

            String imageRoot = imgUtils.getFullPath(info.getImgNm());
            Resource resource = new FileSystemResource(imageRoot);
            HttpHeaders header = new HttpHeaders();
            Path filePath = null;
            try {
                filePath = Paths.get(imageRoot);
                // 인풋으로 들어온 파일명 .png / .jpg 에 맞게 헤더 타입 설정
                header.add("Content-Type", Files.probeContentType(filePath));
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return new ResponseEntity<>(resource,header, HttpStatus.OK);
        }).orElseThrow(() -> new CommonException(ErrorType.INVALID_PATIENT));
    }

    @Override
    public String deletePatient(PatientRequest param) {
        return patientMtRepository.findById(param.getPsn()).map(info -> {
            if(!info.getImgNm().equals("")) imgUtils.deleteImage(info.getImgNm());
            patientMtRepository.delete(info);
            return "";
        }).orElseThrow(() -> new CommonException(ErrorType.INVALID_PATIENT));
    }
}
