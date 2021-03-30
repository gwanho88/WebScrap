package com.wemakeprice.assignment.scraping.service;

import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import com.wemakeprice.assignment.constants.Constants;
import com.wemakeprice.assignment.util.Scraper;
import com.wemakeprice.assignment.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ScrapingService{

    public ResponseScrap getConvertToScrap(RequestScrap param){

        HashMap<String,String> scrapedResult = Scraper.webScrapping(param.getUrl());
        String scrapedBody = "";
        if(Constants.SCRAP_FAIL.equals(scrapedResult.get("result"))){
            return ResponseScrap.builder().status(Constants.SCRAP_FAIL).errorMessage(scrapedResult.get("message")).build();
        }else{
            scrapedBody = scrapedResult.get("scrapData");
        }

        if(param.isUseTag()) {
            scrapedBody = StringUtil.removeTag(scrapedBody);
        }

        String sortedAlpabets = StringUtil.priorityLetterSort(StringUtil.getAlphabets(scrapedBody));
        log.info("string length :{}, sortedAlpabets : {}", sortedAlpabets.length(), sortedAlpabets.toString());
        String sortedNumbers = StringUtil.numberSort(StringUtil.getNumbers(scrapedBody));
        log.info("string length :{}, sortedNumbers : {}", sortedNumbers.length(), sortedNumbers);
        String combineAlpabetsAndNumbers = StringUtil.combineString(sortedAlpabets, sortedNumbers);
        log.info("string length :{},mergeAlpabetsAndNumbers : {}",combineAlpabetsAndNumbers.length(), combineAlpabetsAndNumbers);
        String quotient = combineAlpabetsAndNumbers.substring(0,combineAlpabetsAndNumbers.length() / param.getUnit() * param.getUnit());
        String remainder = combineAlpabetsAndNumbers.substring(quotient.length());

        ResponseScrap responseScrap = ResponseScrap.builder()
                                        .status(Constants.SCRAP_SUCCESS)
                                        .quotient(quotient)
                                        .remainder(remainder)
                                        .build();
        return responseScrap;
    }
}
