package com.samsung.codereview.webcrawler;

import com.samsung.codereview.webcrawler.controller.TextContentProviderController;
import com.samsung.codereview.webcrawler.domain.TextContentCount;
import com.samsung.codereview.webcrawler.service.TextContentService;
import com.samsung.codereview.webcrawler.service.WebCrawlerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TextContentProviderControllerTest {

    @Mock
    WebCrawlerService webCrawlerService;

    @Mock
    TextContentService textContentService;

    @InjectMocks
    TextContentProviderController textContentProviderController;

    @BeforeEach
    void beforeEach(){
        when(textContentService.getUsedTextContentList()).thenReturn(Arrays.asList(new TextContentCount("http://www.naver.com", "네이버", 1)));
    }

    @Test
    void getTextContentTest(){
        List<TextContentCount> textContentCountList = textContentProviderController.getUsedTextContentList();
        Assertions.assertNotNull(textContentCountList);
        verify(webCrawlerService).getContentsDataFromWebDocument(anyString());
        verify(textContentService).getUsedTextContentList();
    }

}
