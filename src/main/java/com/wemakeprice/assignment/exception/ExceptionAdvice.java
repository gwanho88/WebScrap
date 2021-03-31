package com.wemakeprice.assignment.exception;

import com.wemakeprice.assignment.constants.Constants;
import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import com.wemakeprice.assignment.util.StringUtil;
import org.jsoup.HttpStatusException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.scanner.Constant;

import java.net.UnknownHostException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(HttpStatusException.class)
    private String httpStatusExceptionHandler(Model model, HttpStatusException ex){

        model.addAttribute("requestScrap", RequestScrap.builder().url("").unit(100).build());
        model.addAttribute("response", ResponseScrap.builder().status(Constants.SCRAP_FAIL).build());
        model.addAttribute("errorMessage", StringUtil.getHttpErrorMessage(ex.getStatusCode()));
        return Constants.HOME;
    }

    @ExceptionHandler(UnknownHostException.class)
    private String unknownHostExceptionHandler(Model model, UnknownHostException ex){

        model.addAttribute("requestScrap", RequestScrap.builder().url("").unit(100).build());
        model.addAttribute("response", ResponseScrap.builder().status(Constants.SCRAP_FAIL).build());
        model.addAttribute("errorMessage", "요청하신 페이지는 등록되지 않은 주소입니다.");
        return Constants.HOME;
    }

    @ExceptionHandler(Exception.class)
    private String exceptionHandler(Model model, Exception ex){

        model.addAttribute("requestScrap", RequestScrap.builder().url("").unit(100).build());
        model.addAttribute("response", ResponseScrap.builder().status(Constants.SCRAP_FAIL).build());
        model.addAttribute("errorMessage", "요청하신 페이지에 접속할 수 없습니다.");
        return Constants.HOME;
    }
}
