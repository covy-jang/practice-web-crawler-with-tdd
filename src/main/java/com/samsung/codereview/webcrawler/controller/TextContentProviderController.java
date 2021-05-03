package com.samsung.codereview.webcrawler.controller;

import com.samsung.codereview.webcrawler.domain.TextContentCount;
import com.samsung.codereview.webcrawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/textContent")
public class TextContentProviderController {
    private WebCrawlerService webCrawlerService;

    @Autowired
    public  TextContentProviderController(WebCrawlerService webCrawlerService){
        this.webCrawlerService = webCrawlerService;
    }

    @GetMapping("/getUsedTextContentList")
    public List<TextContentCount> getUsedTextContentList(){
        return webCrawlerService.getContentsDataFromWebDocument("http://www.naver.com");
    }

}
