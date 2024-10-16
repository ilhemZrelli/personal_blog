package com.blog.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.blog.demo.model.Article;
import com.blog.demo.model.Role;
import com.blog.demo.model.User;
import com.blog.demo.repos.ArticleRepo;
import com.blog.demo.repos.RoleRepo;
import com.blog.demo.repos.UserRepo;
import com.blog.demo.services.ArticleService;
import com.blog.demo.servicesImpl.ArticleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	/*@Autowired
	private ArticleRepo articlerepo;*/
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserRepo userRepo;
	@MockBean
    private ArticleRepo articleRepo;
	@Autowired
    private ArticleServiceImpl articleService;
	@Test
	public void testCreateRole(){
		Role role=new Role("Admin");
		roleRepo.save(role);
	}
	@Test
	public void testCreateUser(){
		User user=new User("Ilhem","ilhem99","ilhem.99@gmail.com");
		userRepo.save(user);
	}
	@Test
	public void testCreateArticle(){
		User user1=new User("user","password","email");
		Article article=new Article("Les compléments alimentaire","shdjsjdskdjskdjk",user1);
		userRepo.save(user1);
		articleRepo.save(article);
	}
	@Test
    public void testFindAllArticles() {
       List<Article> mockArticles = new ArrayList<>();
        // Simule des données fictives à retourner lors de l'appel à articleRepo.findAll()

        // Crée un utilisateur fictif pour être l'auteur de l'article
        User mockUser = new User("username", "email@example.com", "password");
        
        // Crée un article fictif avec l'auteur
        mockArticles.add(new Article("Test Title", "Test Content", mockUser));

        // Simule le retour des articles par le repo
        Mockito.when(articleRepo.findAll()).thenReturn(mockArticles);

        // Appelle la méthode du service
        List<Article> articles = articleService.getAllArticles();
		// Vérifie que la liste des articles n'est pas vide et contient bien les informations attendues
        Assertions.assertNotNull(articles);
        Assertions.assertFalse(articles.isEmpty());
        Assertions.assertEquals("Test Title", articles.get(0).getTitle());
        Assertions.assertEquals("Test Content", articles.get(0).getContent());
        Assertions.assertEquals("username", articles.get(0).getAuthor().getUsername());
    }


}
