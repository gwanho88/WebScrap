package com.wemakeprice.assignment.exception;

import com.wemakeprice.assignment.constants.Constants;
import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    String CustomExceptionHandler(Model model, CustomException ex){

        model.addAttribute("requestScrap", RequestScrap.builder().url("").unit(100).build());
        model.addAttribute("response", ResponseScrap.builder().status(Constants.SCRAP_FAIL).build());
        model.addAttribute("errorMessage", ex.getCustomMessage());
        return Constants.HOME;
    }

    @ExceptionHandler(Exception.class)
    String exceptionHandler(Model model, Exception ex){

        model.addAttribute("requestScrap", RequestScrap.builder().url("").unit(100).build());
        model.addAttribute("response", ResponseScrap.builder().status(Constants.SCRAP_FAIL).build());
        model.addAttribute("errorMessage", "요청하신 페이지에 접속할 수 없습니다.");
        return Constants.HOME;
    }
}
