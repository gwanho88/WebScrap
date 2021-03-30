package com.wemakeprice.assignment.scraping.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseScrap {

    private String status;

    private String quotient;

    private String remainder;

    private String errorMessage;

    @Builder
    public ResponseScrap(String status, String quotient, String remainder, String errorMessage) {
        this.status = status;
        this.quotient = quotient;
        this.remainder = remainder;
        this.errorMessage = errorMessage;
    }
}
