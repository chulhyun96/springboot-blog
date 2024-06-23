package me.chulhyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.chulhyeon.springbootdeveloper.domain.Article;
import me.chulhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.chulhyeon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
}
