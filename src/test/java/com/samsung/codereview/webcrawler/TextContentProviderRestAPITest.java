package com.samsung.codereview.webcrawler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samsung.codereview.webcrawler.domain.TextContentCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TextContentProviderRestAPITest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void getMethodWithGetUsedTextContentListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("http://localhost:8080/textContent/getUsedTextContentList"))
                .andExpect(status().isOk())
                .andReturn();
        Gson gson = new Gson();
        List<TextContentCount> list = gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<ArrayList<TextContentCount>>(){}.getType());
        assertNotNull(list);
        assertTrue(list.stream().anyMatch(content -> content.getUrl().equals("http://www.naver.com") && content.getTextContent().equals("구독")));
    }
}
