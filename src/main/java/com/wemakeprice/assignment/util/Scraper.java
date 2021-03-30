package com.wemakeprice.assignment.util;

import com.wemakeprice.assignment.constants.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.net.UnknownHostException;
import java.util.HashMap;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Scraper {

    static final String USER_AGENT = "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36\"";
    static final int TIMEOUT = 30000;

    public static HashMap<String,String> webScrapping(String url){

        HashMap<String,String> scrapResult = new HashMap<String,String>();
        try {
            Connection.Response response = Jsoup
                    .connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT)
                    .method(Connection.Method.GET)
                    .followRedirects(true)
                    .execute();

            scrapResult.put("result", Constants.SCRAP_SUCCESS);
            scrapResult.put("scrapData", response.body());
            return scrapResult;
        }catch (HttpStatusException ex) {
            scrapResult.put("result", Constants.SCRAP_FAIL);
            scrapResult.put("message", StringUtil.getHttpErrorMessage(ex.getStatusCode()));
            return scrapResult;
        }catch(UnknownHostException ex) {
            scrapResult.put("result", Constants.SCRAP_FAIL);
            scrapResult.put("message", "요청하신 페이지는 등록되지 않은 주소입니다.");
            return scrapResult;
        }catch (Exception ex) {
            scrapResult.put("result", Constants.SCRAP_FAIL);
            scrapResult.put("message", "요청하신 페이지에 접속할 수 없습니다.");
            return scrapResult;
        }

    }

}
