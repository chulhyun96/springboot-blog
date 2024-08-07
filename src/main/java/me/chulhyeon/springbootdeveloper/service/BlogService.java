package me.chulhyeon.springbootdeveloper.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.chulhyeon.springbootdeveloper.domain.Article;
import me.chulhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.chulhyeon.springbootdeveloper.dto.UpdateArticleRequest;
import me.chulhyeon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 5; i++) {
            AddArticleRequest dummy = new AddArticleRequest("제목"+i, "내용" + i);
            blogRepository.save(dummy.toEntity());
        }
    }
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found:" + id));
    }

    public void deleteById(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));

        return article.update(request.getTitle(), request.getContent());
    }
}
