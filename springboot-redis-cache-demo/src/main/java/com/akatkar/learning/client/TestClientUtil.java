package com.akatkar.learning.client;


import com.akatkar.learning.rediscachedemo.model.Article;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class TestClientUtil {
    public void getArticleByIdDemo(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/user/article/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article.class, id);
        Article article = responseEntity.getBody();
        System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
                +", Category:"+article.getCategory());
    }
    public void getAllArticlesDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/user/articles";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article[].class);
        Article[] articles = responseEntity.getBody();
        for(Article article : articles) {
            System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
                    +", Category: "+article.getCategory());
        }
    }
    public void addArticleDemo(Article objArticle) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/user/article";
        HttpEntity<Article> requestEntity = new HttpEntity<>(objArticle, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }
    public void updateArticleDemo(Article objArticle) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/user/article";
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteArticleDemo(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/user/article/{id}";
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, id);
    }
    public static void main(String args[]) {
        TestClientUtil util = new TestClientUtil();
        //Add article
        Article objArticle = new Article();
        objArticle.setTitle("Spring REST Security");
        objArticle.setCategory("Spring");
        util.addArticleDemo(objArticle);

        //Update article
        objArticle.setArticleId(1);
        objArticle.setTitle("Java Concurrency");
        objArticle.setCategory("Java");
        util.updateArticleDemo(objArticle);



        //util.deleteArticleDemo(2);
//        util.getArticleByIdDemo(1);
        System.out.println("---- All articles ----");
        util.getAllArticlesDemo();    }
}
