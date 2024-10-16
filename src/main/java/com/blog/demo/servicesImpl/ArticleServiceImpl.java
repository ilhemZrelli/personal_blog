package com.blog.demo.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.demo.model.Article;
import com.blog.demo.repos.ArticleRepo;
import com.blog.demo.services.ArticleService;
import java.time.LocalDateTime;
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepo articleRepo;
    @Override
    public Article saveArticle(Article a) {
        a.setCreatedAt(LocalDateTime.now());
        return articleRepo.save(a);
    }

    @Override
    public Article updateArticle(Article a) {
        return articleRepo.save(a);
    }

    @Override
    public void deleteArticle(Article a) {
        articleRepo.delete(a);
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepo.deleteById(id);
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepo.findById(id).get();
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

}
