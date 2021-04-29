package com.samsung.codereview.webcrawler;

import com.samsung.codereview.webcrawler.service.WebCrawlerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebCrawlerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerServiceApplication.class, args);
    }

    /*
    @Bean
    public CommandLineRunner commandLineRunner(WebCrawlerService webCrawlerService){
        return args -> webCrawlerService.getContentsDataFromWebDocument("https://www.naver.com");
    }
    */
}
