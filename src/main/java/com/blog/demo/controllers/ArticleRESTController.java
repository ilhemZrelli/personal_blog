package com.blog.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.model.Article;
import com.blog.demo.model.User;
import com.blog.demo.repos.ArticleRepo;
import com.blog.demo.services.ArticleService;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;


@RestController
@RequestMapping("/articles")
@CrossOrigin
public class ArticleRESTController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(path = "all",method=RequestMethod.GET)
    public List<Article> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();

        if (!articles.isEmpty()) {
            return articles;  // Renvoie la liste d'articles avec un statut 200 OK
        } else {
            return (List<Article>) ResponseEntity.noContent().build();  // Renvoie un 204 No Content si la liste est vide
        }
    }
    
   
    @RequestMapping(path="addArticle",method = RequestMethod.POST)
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }
    
    @RequestMapping(path = "updateArticle",method = RequestMethod.PUT)
    public Article updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }
    
    @RequestMapping(value = "delprod/{id}", method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticleById(id);
    }
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public void getArticleById(@PathVariable("id") Long id) {
        articleService.getArticle(id);
    }



    private final ArticleRepo articleRepo;

    public ArticleRESTController(ArticleRepo articleRepository) {
        this.articleRepo = articleRepository;
    }

   /*  @PostMapping("/create")
    public Article createArticle(@RequestBody Article article, @AuthenticationPrincipal User currentUser) {
        article.setAuthor(currentUser);
        return articleRepo.save(article);
    }

    @GetMapping
    public List<Article> listArticles() {
        return articleRepo.findAll();
    }*/
    
}
