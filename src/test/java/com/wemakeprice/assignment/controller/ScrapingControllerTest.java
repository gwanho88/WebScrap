package com.wemakeprice.assignment.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.stream.Stream;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ScrapingControllerTest
{
    @Autowired
    MockMvc mockMvc;

    static Stream<Arguments> webScrapRequestSampleData() {
        return Stream.of(
                Arguments.of("위메프","http://www.wemakeprice.com", 1000, true),
                Arguments.of("네이버","http://www.naver.com", 1000, true),
                Arguments.of("구글","http://www.google.com", 20000, false),
                Arguments.of("http protocol 제거","www.wemakeprice.com", 1000, true),
                Arguments.of("잘못된 도메인","http://www.navem", 1000, true),
                Arguments.of("문자열","httdwww.google.com", 20000, false)
        );
    }

    @DisplayName("Web restful 스크랩 정보 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("webScrapRequestSampleData")
    public void getWebScrapTest(String name, String url, int unit, boolean useTag) throws Exception {
        mockMvc.perform(post("/getScraping")
                .param("url", url)
                .param("unit", String.valueOf(unit))
                .param("useTag", String.valueOf(useTag)))
                .andExpect(status().isOk())
                .andDo(print());

    }
}
