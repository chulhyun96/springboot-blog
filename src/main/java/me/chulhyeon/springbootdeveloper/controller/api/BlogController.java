package me.chulhyeon.springbootdeveloper.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chulhyeon.springbootdeveloper.domain.Article;
import me.chulhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.chulhyeon.springbootdeveloper.dto.ArticleResponse;
import me.chulhyeon.springbootdeveloper.dto.UpdateArticleRequest;
import me.chulhyeon.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        log.info("api findAll 호출");
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        Article findArticle = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(findArticle));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        blogService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);
        return ResponseEntity.ok()
                .body(updateArticle);
    }

}
