package com.org.heuron.application.service;

import com.org.heuron.configration.exeption.CommonException;
import com.org.heuron.configration.exeption.type.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImgUtils {
    // 루트 경로 불러오기
    private final String rootPath = System.getProperty("user.dir");
    // 프로젝트 루트 경로에 있는 images 디렉토리
    private final String fileDir = "\\src\\main\\resources\\images\\";

    public String storeFile(MultipartFile multipartFile) throws IOException {
        File folder = new File(rootPath+fileDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        if(multipartFile.isEmpty()) {
            throw new CommonException(ErrorType.NO_IMAGE);
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String ext = extractExt(originalFilename);

        if(!ext.equals("png")&&!ext.equals("jpg")){
            throw new CommonException(ErrorType.INVALID_IMAGE);
        }

        // 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
        // 파일명을 중복되지 않게끔 UUID로 정하고 ".확장자"는 그대로
        String storeFilename = UUID.randomUUID() + "." + ext;

        // 파일을 저장하는 부분 -> 파일경로 + storeFilename 에 저장
        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return storeFilename;
    }

    public String getFullPath(String filename) { return rootPath + fileDir + filename; }

    // 확장자 추출
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public void deleteImage(String filename) {
        File file = new File(getFullPath(filename));
        if(file.exists()){
            file.delete();
        }
    }
}
