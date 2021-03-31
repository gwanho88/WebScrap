package com.wemakeprice.assignment.scraping.contoller;

import com.wemakeprice.assignment.scraping.model.RequestScrap;
import com.wemakeprice.assignment.scraping.model.ResponseScrap;
import com.wemakeprice.assignment.scraping.service.ScrapingService;
import com.wemakeprice.assignment.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ScrapingController {

    private final ScrapingService scrapingService;

    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute("requestScrap", RequestScrap.builder().url("https://front.wemakeprice.com").unit(100).build());
        model.addAttribute("response", ResponseScrap.builder().build());
        return Constants.HOME;
    }

    @PostMapping("/getScraping")
    public String getScraping(Model model, @Valid RequestScrap requestScrap, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("response", ResponseScrap.builder().status(Constants.SCRAP_FAIL).quotient("").remainder("").build());
            return Constants.HOME;
        }
        model.addAttribute("response", scrapingService.getConvertToScrap(requestScrap));

        return Constants.HOME;
    }

}
