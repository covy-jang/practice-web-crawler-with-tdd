package com.samsung.codereview.webcrawler.service;

import com.samsung.codereview.webcrawler.exception.InvalidUrlFormatException;
import com.samsung.codereview.webcrawler.exception.UrlSetSizeOverException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@Data
public class UrlAccessManageService {
    private static final int COVERAGE_SIZE = 10;
    private static final HashSet<String> urlSet = new HashSet<>();

    public boolean isUrlEmpty(String url){
        return !urlSet.contains(url);
    }

    public synchronized boolean addUrl(String url) {
        if(!isUrlHttpFormat(url))
            throw new InvalidUrlFormatException("This is Not HyperLink Url");
        if(!isCoverUrlSet())
            throw new UrlSetSizeOverException("There are too many urls");
        if(!isUrlEmpty(url))
            return false;
        urlSet.add(url);
        return true;
    }

    public boolean isUrlHttpFormat(String url){
        return url.startsWith("http://") || url.startsWith("https://");
    }

    public int getSizeOfUrlSet(){
        return urlSet.size();
    }

    public boolean isCoverUrlSet(){
        return getSizeOfUrlSet() <= COVERAGE_SIZE;
    }
}
