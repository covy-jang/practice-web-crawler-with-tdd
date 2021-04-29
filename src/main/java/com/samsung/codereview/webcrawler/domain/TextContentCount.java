package com.samsung.codereview.webcrawler.domain;

import lombok.Data;

@Data
public class TextContentCount {
    private String url;
    private String textContent;
    private int usedCount;

    public TextContentCount(String url, String textContent, int usedCount){
        this.url = url;
        this.textContent = textContent;
        this.usedCount = usedCount;
    }
}
