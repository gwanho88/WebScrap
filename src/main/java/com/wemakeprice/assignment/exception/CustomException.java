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

public class CustomException extends RuntimeException{

    String customMessage;

    public CustomException(String msg){
        this.customMessage = msg;
    }

    public String getCustomMessage(){
        return customMessage;
    }
}
