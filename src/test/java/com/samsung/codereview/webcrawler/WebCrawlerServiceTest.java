package com.samsung.codereview.webcrawler;

import com.samsung.codereview.webcrawler.domain.TextContentCount;
import com.samsung.codereview.webcrawler.service.TextContentService;
import com.samsung.codereview.webcrawler.service.WebCrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WebCrawlerServiceTest {

    @Mock
    WebCrawlerService webCrawlerService;

    @BeforeEach
    void beforeEach() throws IOException {
        when(webCrawlerService.getDocumentFromUrl(anyString())).thenReturn(getDocumentForLocalHtml("D:/code-review-workspace/tdd-java/web-crawler/NAVER.html"));
    }

    @Test
    void getDocumentFromUrlTest() throws IOException {
        Document document = webCrawlerService.getDocumentFromUrl("http://www.naver.com");
        assertEquals(getDocumentForLocalHtml("D:/code-review-workspace/tdd-java/web-crawler/NAVER.html").toString(), document.toString());
    }


    static Document getDocumentForLocalHtml(String fileUrl) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fileUrl);
        FileReader fileReader = new FileReader(file);
        int line;
        while ((line = fileReader.read()) != -1) {
            stringBuilder.append((char) line);
        }
        fileReader.close();
        return Jsoup.parse(stringBuilder.toString());
    }

}
