package com.samsung.codereview.webcrawler.service;

import com.samsung.codereview.webcrawler.domain.TextContentCount;
import com.samsung.codereview.webcrawler.exception.InvalidUrlFormatException;
import com.samsung.codereview.webcrawler.exception.UrlSetSizeOverException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WebCrawlerService {
    private static final String TEXT_DELIMETER = " ";
    private TextContentService textContentService;
    private UrlAccessManageService urlAccessManageService;

    @Autowired
    public WebCrawlerService(TextContentService textContentService, UrlAccessManageService urlAccessManageService) {
        this.textContentService = textContentService;
        this.urlAccessManageService = urlAccessManageService;
    }

    public List<TextContentCount> getContentsDataFromWebDocument(String url) {
        log.info(String.format("[getContentsDataFromWebDocument] url : %s", url));
        log.info(String.format("[getContentsDataFromWebDocument] count : %d", urlAccessManageService.getSizeOfUrlSet()));
        searchContentDataAndHyperLinkWithRetrieving(url);
        return textContentService.getUsedTextContentList();
    }

    public void searchContentDataAndHyperLinkWithRetrieving(String url) {
        try {
            Document document = getDocumentFromUrl(url);
            if (urlAccessManageService.isCoverUrlSet()) {
                saveAccessedUrl(url);
                searchContentData(url, document);
                retrieveHyperLink(searchHyperLink(document));
            }
        } catch (UrlSetSizeOverException | InvalidUrlFormatException | IOException | NullPointerException | IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
    }

    public Document getDocumentFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public void searchContentData(String url, Document document) {
        String[] texts = document.body().text().split(TEXT_DELIMETER);
        for (String text : texts) {
            textContentService.addAfterCheckingValidation(url, text);
        }
    }

    public boolean saveAccessedUrl(String hyperLinkUrl) {
        return urlAccessManageService.addUrl(hyperLinkUrl);
    }

    public List<String> searchHyperLink(Document document) {
        List<String> hyperLinks = new ArrayList<>();
        Elements elements = document.select("[href]");
        for (Element element : elements) {
            hyperLinks.add(element.attr("abs:href"));
        }
        return hyperLinks;
    }

    public void retrieveHyperLink(List<String> hyperLinkUrls) {
        hyperLinkUrls.parallelStream()
                .filter(url -> urlAccessManageService.isUrlEmpty(url))
                .forEach(url -> {
                    if (!urlAccessManageService.isCoverUrlSet())
                        throw new UrlSetSizeOverException("[UrlSetSizeOverException] There are too many urls");
                    getContentsDataFromWebDocument(url);
                });
    }


}
