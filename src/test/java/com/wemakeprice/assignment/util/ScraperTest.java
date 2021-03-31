package com.wemakeprice.assignment.util;

import com.wemakeprice.assignment.constants.Constants;
import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.stream.Stream;

@SpringBootTest(classes = Scraper.class)
public class ScraperTest {

    static Stream<Arguments> scrapSampleData() {
        return Stream.of(
                Arguments.of("http://www.wemakeprice.com"),
                Arguments.of("http://www.naver.com"),
                Arguments.of("http://www.google.com")
        );
    }

    @DisplayName("스크랩 테스트")
    @ParameterizedTest
    @MethodSource("scrapSampleData")
    public void scrapTest(String url) throws Exception {
        String scrapBody = Scraper.webScrapping(url);
        Assertions.assertTrue(scrapBody.length() > 0);
    }

    static Stream<Arguments> scrapFailSampleData() {
        return Stream.of(
                Arguments.of("www.wemakeprice.com"),
                Arguments.of("http://www.navom"),
                Arguments.of("http/www.godogle.com")
        );
    }

    @DisplayName("스크랩 정보 조회 실패 테스트")
    @ParameterizedTest
    @MethodSource("scrapFailSampleData")
    public void scrapFailTest(String url) throws Exception {
        String scrapBody = Scraper.webScrapping(url);
        Assertions.assertTrue(scrapBody.length() > 0);
    }
}
