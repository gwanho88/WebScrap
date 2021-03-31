package com.wemakeprice.assignment.scraping.service;

import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import com.wemakeprice.assignment.constants.Constants;
import com.wemakeprice.assignment.util.Scraper;
import com.wemakeprice.assignment.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.HttpStatusException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ScrapingService{

    public ResponseScrap getConvertToScrap(RequestScrap requestScrap) throws Exception {

        String scrapedBody = Scraper.webScrapping(requestScrap.getUrl());

        if(requestScrap.isUseTag()) {
            scrapedBody = StringUtil.removeTag(scrapedBody);
        }

        String sortedAlpabets = StringUtil.priorityLetterSort(StringUtil.getAlphabets(scrapedBody));
        String sortedNumbers = StringUtil.numberSort(StringUtil.getNumbers(scrapedBody));
        String combineAlpabetsAndNumbers = StringUtil.combineString(sortedAlpabets, sortedNumbers);
        String quotient = combineAlpabetsAndNumbers.substring(0,combineAlpabetsAndNumbers.length() / requestScrap.getUnit() * requestScrap.getUnit());
        String remainder = combineAlpabetsAndNumbers.substring(quotient.length());

        ResponseScrap responseScrap = ResponseScrap.builder()
                                        .status(Constants.SCRAP_SUCCESS)
                                        .quotient(quotient)
                                        .remainder(remainder)
                                        .build();
        return responseScrap;
    }
}
