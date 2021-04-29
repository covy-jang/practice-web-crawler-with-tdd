package com.samsung.codereview.webcrawler.service;

import com.samsung.codereview.webcrawler.exception.InvalidUrlFormatException;
import com.samsung.codereview.webcrawler.exception.UrlSetSizeOverException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@Data
public class UrlAccessManageService {
    private static final int COVERAGE_SIZE = 100;
    private static final HashSet<String> urlSet = new HashSet<>();

    public boolean isUrlEmpty(String url){
        if(urlSet.contains(url))
            return false;
        return true;
    }

    public synchronized boolean addUrl(String url) throws InvalidUrlFormatException {
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
        if(!url.startsWith("http://") && !url.startsWith("https://"))
            return false;
        return true;
    }

    public int getSizeOfUrlSet(){
        return urlSet.size();
    }

    public boolean isCoverUrlSet(){
        if(getSizeOfUrlSet() > COVERAGE_SIZE)
            return false;
        return true;
    }
}
