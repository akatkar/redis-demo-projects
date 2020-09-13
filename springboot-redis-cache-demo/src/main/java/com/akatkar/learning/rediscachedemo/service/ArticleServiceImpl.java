package com.akatkar.learning.rediscachedemo.service;

import com.akatkar.learning.rediscachedemo.model.Article;
import com.akatkar.learning.rediscachedemo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Cacheable(value= "articleCache", key= "#articleId")
    public Article getArticleById(long articleId) {
        System.out.println("--- Inside getArticleById() ---");
        return  articleRepository.findById(articleId).get();
    }
    @Override
    @Cacheable(value= "allArticlesCache", unless= "#result.size() == 0")
    public List<Article> getAllArticles(){
        System.out.println("--- Inside getAllArticles() ---");
        List<Article> list = new ArrayList<>();
        articleRepository.findAll().forEach(list::add);
        return list;
    }
    @Override
    @Caching(
            put= { @CachePut(value= "articleCache", key= "#article.articleId") },
            evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
    )
    public Article addArticle(Article article){
        System.out.println("--- Inside addArticle() ---");
        return articleRepository.save(article);
    }
    @Override
    @Caching(
            put= { @CachePut(value= "articleCache", key= "#article.articleId") },
            evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
    )
    public Article updateArticle(Article article) {
        System.out.println("--- Inside updateArticle() ---");
        return articleRepository.save(article);
    }
    @Override
    @Caching(
            evict= {
                    @CacheEvict(value= "articleCache", key= "#articleId"),
                    @CacheEvict(value= "allArticlesCache", allEntries= true)
            }
    )
    public void deleteArticle(long articleId) {
        System.out.println("--- Inside deleteArticle() ---");
        articleRepository.delete(articleRepository.findById(articleId).get());
    }
}
