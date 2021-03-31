package com.wemakeprice.assignment.scraping.service;

import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import com.wemakeprice.assignment.constants.Constants;
import com.wemakeprice.assignment.util.Scraper;
import com.wemakeprice.assignment.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScrapingService{

    public ResponseScrap getConvertToScrap(RequestScrap requestScrap){

        String scrapedBody = Scraper.webScrapping(requestScrap.getUrl());

        if(requestScrap.isUseTag()) {
            scrapedBody = StringUtil.removeTag(scrapedBody);
        }

        String sortedAlphabets = StringUtil.upperCaseLetterPriorityAndAscendingSort(StringUtil.getAlphabets(scrapedBody));
        String sortedNumbers = StringUtil.numberSort(StringUtil.getNumbers(scrapedBody));
        String combineAlphabetsAndNumbers = StringUtil.combineString(sortedAlphabets, sortedNumbers);
        String quotient = combineAlphabetsAndNumbers.substring(0,combineAlphabetsAndNumbers.length() / requestScrap.getUnit() * requestScrap.getUnit());
        String remainder = combineAlphabetsAndNumbers.substring(quotient.length());

        ResponseScrap responseScrap = ResponseScrap.builder()
                                        .status(Constants.SCRAP_SUCCESS)
                                        .quotient(quotient)
                                        .remainder(remainder)
                                        .build();
        return responseScrap;
    }
}
