package com.blog.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.model.Article;
import com.blog.demo.model.User;
import java.util.List;
public interface ArticleRepo extends JpaRepository<Article,Long> {
List<Article> findByAuthor(User author);
}
