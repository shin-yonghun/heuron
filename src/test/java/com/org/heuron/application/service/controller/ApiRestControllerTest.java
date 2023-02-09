package com.org.heuron.application.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.heuron.application.service.model.transfer.request.DeletePatientRequest;
import com.org.heuron.application.service.model.transfer.request.SavePatientInfoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    @DisplayName("mvc 인코딩 설정")
    void setUp(){
        this.mvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("회원정보저장 API TEST")
    void savePatientInfo() throws Exception {
        //given
        String url = "/heuron/api/patient_info";

        SavePatientInfoRequest param = new SavePatientInfoRequest();
        param.setName("테스트");
        param.setAge(30);
        param.setSex("M");
        param.setDisease("Y");
        String content = mapper.writeValueAsString(param);

        mvc.perform(MockMvcRequestBuilders.post(url).content(content).contentType(MediaType.APPLICATION_JSON)) //when
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload").exists()); //then
    }

    @Test
    @DisplayName("회원이미지저장 API TEST")
    void savePatientImgTest() throws Exception {
        //given
        String url = "/heuron/api/patient_img";

        MockMultipartFile file = new MockMultipartFile("img",
                "test.jpg",
                "image/jpg",
                new FileInputStream("업로드 할 로컬 이미지 경로"));
                //new FileInputStream("C:\\KakaoTalk_20210503_134835970.jpg"));

        mvc.perform(MockMvcRequestBuilders.multipart(url).file(file).param("psn","13")) //when
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload").value("image uploaded"));
    }

    @Test
    @DisplayName("회원정보조회 API TEST")
    void getPatientInfo() throws Exception {
        //given
        String url = "/heuron/api/patient_info/14";

        mvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)) //when
                .andExpect(status().isOk()) //then
                .andExpect(jsonPath("$.payload.name").value("테스트"));
    }

    @Test
    @DisplayName("회원이미지조회 API TEST")
    void getPatientImg() throws Exception {
        //given
        String url = "/heuron/api/patient_img/14be5dc3-aecc-4e26-870f-be8cc7703ee7.jpg";

        mvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)) //when
                .andExpect(status().isOk())
                .andReturn(); //then
    }

    @Test
    @DisplayName("회원정보삭제 API TEST")
    void deletePatient() throws Exception {
        //given
        String url = "/heuron/api/patient";

        DeletePatientRequest param = new DeletePatientRequest();
        param.setPsn(15L);
        String content = mapper.writeValueAsString(param);

        mvc.perform(MockMvcRequestBuilders.delete(url).content(content).contentType(MediaType.APPLICATION_JSON)) //when
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload").value("info deleted"));
    }
}