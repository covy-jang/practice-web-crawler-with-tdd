package com.samsung.codereview.webcrawler.controller;

import com.samsung.codereview.webcrawler.domain.TextContentCount;
import com.samsung.codereview.webcrawler.service.TextContentService;
import com.samsung.codereview.webcrawler.service.WebCrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/textContent")
public class TextContentProviderController {
    public WebCrawlerService webCrawlerService;

    @Autowired
    public TextContentProviderController(WebCrawlerService webCrawlerService){
        this.webCrawlerService = webCrawlerService;
    }

    @GetMapping("/getUsedTextContentList")
    public List<TextContentCount> getUsedTextContentList(){
        return webCrawlerService.getUsedTextContentList("http://www.naver.com");
    }

}
