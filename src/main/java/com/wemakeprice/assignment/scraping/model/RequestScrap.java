package com.wemakeprice.assignment.scraping.model;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
public class RequestScrap {

    @URL(message = "형식에 맞지 않은 URL입니다.")
    @NotEmpty(message = "URL을 입력해주세요.")
    private String url;

    private boolean useTag;

    @Min(1)
    private int unit;

    @Builder
    public RequestScrap(String url, boolean useTag, int unit) {
        this.url = url;
        this.useTag = useTag;
        this.unit = unit;
    }


}
