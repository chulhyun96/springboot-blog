package me.chulhyeon.springbootdeveloper.dto;

import lombok.Getter;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;

    public  ArticleResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
