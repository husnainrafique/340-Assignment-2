package com._0.demo.controller;

import com._0.demo.model.NewsArticle;
import com._0.demo.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api") // Base URL for this controller
public class NewsController {

    private final NewsService newsService;

    // Constructor for dependency injection
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news") // This will handle GET requests to "/api/news"
    public List<NewsArticle> getNews() {
        return newsService.fetchNews();
    }
}
