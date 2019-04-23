package com.akatkar.learning.rediscachedemo.service;

import com.akatkar.learning.rediscachedemo.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    Article getArticleById(long articleId);

    Article addArticle(Article article);

    Article updateArticle(Article article);

    void deleteArticle(long articleId);
}
