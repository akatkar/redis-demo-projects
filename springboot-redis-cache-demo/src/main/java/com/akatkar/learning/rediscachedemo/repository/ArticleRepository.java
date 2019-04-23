package com.akatkar.learning.rediscachedemo.repository;

import com.akatkar.learning.rediscachedemo.model.Article;
import org.springframework.data.repository.CrudRepository;


public interface ArticleRepository extends CrudRepository<Article, Long> {
}
