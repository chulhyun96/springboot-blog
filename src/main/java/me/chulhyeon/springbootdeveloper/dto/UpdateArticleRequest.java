package me.chulhyeon.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class UpdateArticleRequest {
    private String title;
    private String content;


}
