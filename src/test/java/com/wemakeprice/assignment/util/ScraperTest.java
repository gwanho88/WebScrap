package com.wemakeprice.assignment.util;

import com.wemakeprice.assignment.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ScraperTest {

    static Stream<Arguments> scrapSampleData() {
        return Stream.of(
                Arguments.of("위메프","http://www.wemakeprice.com"),
                Arguments.of("네이버","http://www.naver.com"),
                Arguments.of("구글","http://www.google.com")
        );
    }

    @DisplayName("스크랩 테스트")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("scrapSampleData")
    public void scrapTest(String name, String url) {
        String scrapBody = Scraper.webScrapping(url);
        Assertions.assertTrue(scrapBody.length() > 0);
    }

    static Stream<Arguments> scrapFailSampleData() {
        return Stream.of(
                Arguments.of("http protocol 제거","www.wemakeprice.com"),
                Arguments.of("잘못된도메인","http://www.navom"),
                Arguments.of("문자열(특수문자포함)","http/www.godogle.com")
        );
    }

    @DisplayName("스크랩 정보 조회 실패 테스트")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("scrapFailSampleData")
    public void scrapFailTest(String url) {
        Assertions.assertThrows(CustomException.class, () -> {
            Scraper.webScrapping(url);
        });
    }
}
