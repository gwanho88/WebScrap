package com.wemakeprice.assignment.service;

import com.wemakeprice.assignment.exception.CustomException;
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
                Arguments.of("위메프", "https://front.wemakeprice.com", 1000, true),
                Arguments.of("네이버", "http://www.naver.com", 1000, true),
                Arguments.of("위메프","http://www.google.com", 20000, false)
        );
    }

    @DisplayName("스크랩 정보 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("scrapRequestSampleData")
    public void getConvertToScrapTest(String name, String url, int unit, boolean useTag) {

        RequestScrap testData = RequestScrap.builder().url(url).useTag(useTag).unit(unit).build();

        ResponseScrap responseScrap = scrapingService.getConvertToScrap(testData);
        Assertions.assertEquals(Constants.SCRAP_SUCCESS, responseScrap.getStatus());
    }

    static Stream<Arguments> scrapRequestBadCaseSampleData() {
        return Stream.of(
                Arguments.of("http protocol 제거","http://www.dkfjwlif.com", 1000, true),
                Arguments.of("잘못된 도메인","http://www.ujkkjurhhk.com", 20000, false),
                Arguments.of("문자열", "wwldmfixm.com", 20000, false)
        );
    }

    @DisplayName("스크랩 정보 조회 실패 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("scrapRequestBadCaseSampleData")
    public void getConvertToScrapBadCaseTest(String name, String url, int unit, boolean useTag) {

        RequestScrap testData = RequestScrap.builder().url(url).useTag(useTag).unit(unit).build();
        Assertions.assertThrows(CustomException.class, () -> {
            ResponseScrap responseScrap = scrapingService.getConvertToScrap(testData);
        });
    }
}
