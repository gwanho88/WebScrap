package com.wemakeprice.assignment.service;

import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import com.wemakeprice.assignment.scraping.service.ScrapingService;
import com.wemakeprice.assignment.constants.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest(classes = ScrapingService.class)
class ScrapingServiceTest {

    @Autowired
    ScrapingService scrapingService;


    static Stream<Arguments> scrapRequestSampleData() {
        return Stream.of(
                Arguments.of("https://front.wemakeprice.com", 1000, true),
                Arguments.of("http://www.naver.com", 1000, true),
                Arguments.of("http://www.google.com", 20000, false)
        );
    }

    @DisplayName("스크랩 정보 테스트")
    @ParameterizedTest
    @MethodSource("scrapRequestSampleData")
    public void getConvertToScrapTest(String url, int unit, boolean useTag) throws Exception {

        RequestScrap testData = RequestScrap.builder().url(url).useTag(useTag).unit(unit).build();

        ResponseScrap responseScrap = scrapingService.getConvertToScrap(testData);
        Assertions.assertEquals(Constants.SCRAP_SUCCESS, responseScrap.getStatus());
    }

    static Stream<Arguments> scrapRequestBadCaseSampleData() {
        return Stream.of(
                Arguments.of("http://www.dkfjwlif.com", 1000, true),
                Arguments.of("www.ujkkjurhhk.com", 20000, false),
                Arguments.of("www.ekwldmfixm.com", 20000, false)
        );
    }

    @DisplayName("스크랩 정보 조회 실패 테스트")
    @ParameterizedTest
    @MethodSource("scrapRequestBadCaseSampleData")
    public void getConvertToScrapBadCaseTest(String url, int unit, boolean useTag) throws Exception {

        RequestScrap testData = RequestScrap.builder().url(url).useTag(useTag).unit(unit).build();
        ResponseScrap responseScrap = scrapingService.getConvertToScrap(testData);
        Assertions.assertEquals(Constants.SCRAP_SUCCESS, responseScrap.getStatus());
    }
}
