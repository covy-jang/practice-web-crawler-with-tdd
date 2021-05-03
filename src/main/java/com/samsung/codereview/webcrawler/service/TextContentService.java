package com.samsung.codereview.webcrawler.service;

import com.samsung.codereview.webcrawler.domain.TextContentCount;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TextContentService {
    private static final int INIT_COUNT = 1;
    private static final String REGEXP_STRING = "[-/~!@#$%^&*()_+|<>?:{}a-zA-Z0-9]";
    private static final Pattern pattern = Pattern.compile(REGEXP_STRING);
    private static final HashMap<String, Integer> usedTextCountMap = new HashMap<>();
    private static final Map<String, Map<String, Integer>> usedTextCountByUrlMap = new HashMap<>();

    public void addAfterCheckingValidation(String textContent) {
        if (!validateTextContent(textContent))
            return;
        if (!usedTextCountMap.containsKey(textContent)) {
            usedTextCountMap.put(textContent, INIT_COUNT);
        } else {
            usedTextCountMap.put(textContent, usedTextCountMap.get(textContent) + 1);
        }
    }

    public void addAfterCheckingValidation(String url, String textContent){
        if(!validateTextContent(textContent))
            return;
        if(!usedTextCountByUrlMap.containsKey(url)) {
            Map<String, Integer> map = new HashMap<>();
            map.put(textContent, INIT_COUNT);
            usedTextCountByUrlMap.put(url, map);
        }else{
            Map<String, Integer> map = usedTextCountByUrlMap.get(url);
            if(!map.containsKey(textContent))
                map.put(textContent, INIT_COUNT);
            else
                map.put(textContent, map.get(textContent) + 1);
        }
    }

    public boolean validateTextContent(String textContent) {
        return !pattern.matcher(textContent).find();
    }

    public List<TextContentCount> getUsedTextContentList() {
        return usedTextCountByUrlMap.entrySet()
                .parallelStream()
                .map(outEntry -> outEntry.getValue().entrySet()
                        .parallelStream()
                        .map(inEntry -> new TextContentCount(outEntry.getKey(), inEntry.getKey(), inEntry.getValue()))
                        .sorted(Comparator.comparing(TextContentCount::getUsedCount).reversed())
                        .limit(5)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList())
                .parallelStream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
