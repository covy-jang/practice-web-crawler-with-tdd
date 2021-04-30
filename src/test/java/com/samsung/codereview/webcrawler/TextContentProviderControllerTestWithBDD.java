package com.samsung.codereview.webcrawler;

import com.samsung.codereview.webcrawler.controller.TextContentProviderController;
import com.samsung.codereview.webcrawler.domain.TextContentCount;
import com.samsung.codereview.webcrawler.service.WebCrawlerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TextContentProviderControllerTestWithBDD {

    List<TextContentCount> list;

    @Given("Request Occured")
    public void requestUrl() {
        System.out.println("Request Occured");
    }

    @When("Get Text ContentData")
    public void requestAndGetTextContentData() {
        WebCrawlerService webCrawlerService = Mockito.mock(WebCrawlerService.class);
        TextContentProviderController textContentProviderController = new TextContentProviderController(webCrawlerService);
        when(webCrawlerService.getUsedTextContentList(anyString()))
                .thenReturn(Collections.singletonList(new TextContentCount("http://www.naver.com", "네이버", 1)));
        list = webCrawlerService.getUsedTextContentList("http://www.naver.com");
    }

    @Then("Verify Text ContentData")
    public void verifyTextContentData() {
        System.out.println(list);
        assertEquals("[TextContentCount(url=http://www.naver.com, textContent=네이버, usedCount=1)]", list.toString());
    }
}
