package com.wemakeprice.assignment.util;

import com.wemakeprice.assignment.constants.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Scraper {

    static final String USER_AGENT = "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36\"";
    static final int TIMEOUT = 30000;

    public static String webScrapping(String url) throws Exception {

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
            throw ex;
        }catch(UnknownHostException ex) {
            throw ex;
        }catch (IOException ex) {
            throw ex;
        }

    }

}
