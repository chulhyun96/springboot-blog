package me.chulhyeon.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chulhyeon.springbootdeveloper.domain.Article;
import me.chulhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.chulhyeon.springbootdeveloper.dto.ArticleListViewResponse;
import me.chulhyeon.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        log.info("Get Article 호출");
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleListViewResponse(article));
        return "article";
    }
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(name = "id",required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new AddArticleRequest());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", article);
        }
        return "new-article";
    }
}
