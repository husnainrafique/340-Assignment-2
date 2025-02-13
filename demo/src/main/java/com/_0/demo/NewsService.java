package com._0.demo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com._0.demo.model.NewsArticle;
import com._0.demo.model.Source;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RestController
public class NewsService {

    private static final String API_KEY = "a96dc257b0c74e109517ff33e477cd78";  // Replace with your API Key
    private static final String API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;

    public List<NewsArticle> fetchNews() {
        RestTemplate restTemplate = new RestTemplate();

        // Make an API request and get response as a Map
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);

        // Extract the articles list from the response
        List<Map<String, Object>> articlesList = (List<Map<String, Object>>) response.get("articles");

        List<NewsArticle> newsArticles = new ArrayList<>();
        for (Map<String, Object> articleMap : articlesList) {
            NewsArticle article = new NewsArticle();
            article.setTitle((String) articleMap.get("title"));
            article.setDescription((String) articleMap.get("description"));
            article.setUrl((String) articleMap.get("url"));
            article.setUrlToImage((String) articleMap.get("urlToImage"));
            article.setPublishedAt((String) articleMap.get("publishedAt"));
            article.setContent((String) articleMap.get("content"));

            // Set the Source object
            Map<String, Object> sourceMap = (Map<String, Object>) articleMap.get("source");
            Source source = new Source();
            source.setId((String) sourceMap.get("id"));
            source.setName((String) sourceMap.get("name"));
            article.setSource(source);

            newsArticles.add(article);
        }

        return newsArticles;
    }

    @GetMapping("/trending")
    public List<NewsArticle> getTrendingNews() {
        return fetchNews();
    }
}
