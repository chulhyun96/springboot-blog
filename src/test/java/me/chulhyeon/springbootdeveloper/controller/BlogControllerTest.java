package me.chulhyeon.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.chulhyeon.springbootdeveloper.domain.Article;
import me.chulhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.chulhyeon.springbootdeveloper.dto.UpdateArticleRequest;
import me.chulhyeon.springbootdeveloper.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("addArticle : 블로그 글 추가에 성공")
    @Test
    void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "이것은 제목입니다";
        final String content = "이것은 내용입니다";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        final String requestBody = mapper.writeValueAsString(userRequest);
        // when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        Assertions.assertThat(articles.size()).isEqualTo(1);
        Assertions.assertThat(articles.get(0).getTitle()).isEqualTo(userRequest.getTitle());
        Assertions.assertThat(articles.get(0).getContent()).isEqualTo(userRequest.getContent());
    }

    @DisplayName("findAllArticles : 블로그 글 전체목록 조회에 성공한다.")
    @Test
    void findAllArticles() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        // when
        final ResultActions perform = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));


        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }
    @DisplayName("getArticle: 블로그 글 조회에 성공한다")
    @Test
    void getArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "getArticle title 조회";
        final String content = "getArticle Content 조회";

        Article saved = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions result = mockMvc.perform(get(url, saved.getId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));

    }
    @DisplayName("deleteArticle : 블로그 글 삭제")
    @Test
    void deleteArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "Delete Article title";
        final String content = "Delete Article content";

        Article deleteArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        // when
        mockMvc.perform(delete(url, deleteArticle.getId()));
        List<Article> articles = blogRepository.findAll();


        // then
        Assertions.assertThat(articles).isEmpty();
    }
    @DisplayName("updateArticle : 블로그 글 수정하기")
    @Test
    void updateArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "제목1";
        final String content = "내용1";
        Article saved = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final String putTittle = "수정1";
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(putTittle, content);
        // when
        final ResultActions result = mockMvc.perform(put(url, saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateArticleRequest)));

        // then
        result.andExpect(status().isOk());

        Article article = blogRepository.findById(saved.getId()).get();

        Assertions.assertThat(article.getTitle()).isEqualTo(putTittle);


    }
    @AfterEach
    void deleteData() {
        blogRepository.deleteAll();
    }
}