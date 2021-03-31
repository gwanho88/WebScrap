package com.wemakeprice.assignment.util;

import com.wemakeprice.assignment.exception.CustomException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.httpclient.HttpStatus;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import java.net.UnknownHostException;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Scraper {

    static final String USER_AGENT = "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36\"";
    static final int TIMEOUT = 30000;
    /**
     * 웹페이지 스크랩
     * @param url
     * @return String
     */
    public static String webScrapping(String url) {

        try {
            Connection.Response response = Jsoup
                    .connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT)
                    .method(Connection.Method.GET)
                    .followRedirects(true)
                    .execute();

            return response.body();
        }catch (HttpStatusException ex) {
            throw new CustomException(getHttpErrorMessage(ex.getStatusCode()));
        }catch(UnknownHostException ex) {
            throw new CustomException("요청하신 페이지는 등록되지 않은 주소입니다.");
        }catch (Exception ex) {
            throw new CustomException("요청하신 페이지에 접속할 수 없습니다.");
        }

    }

    public static String getHttpErrorMessage(int httpStatusCode) {
        return "요청하신 URL주소는 접속할 수 없습니다.(" + httpStatusCode + ":" + HttpStatus.getStatusText(httpStatusCode) + ")";
    }

}
