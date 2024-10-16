package com.blog.demo.services;
import java.util.List;

import com.blog.demo.model.Article;

public interface ArticleService {
Article saveArticle(Article a);
Article updateArticle(Article a);
void deleteArticle(Article a);
void deleteArticleById(Long id);
Article getArticle(Long id);
List<Article> getAllArticles();

}
