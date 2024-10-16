package com.blog.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Article {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
public Article(String title, String content, User author, LocalDateTime createdAt) {
    super();
    this.title = title;
    this.content = content;
    this.author = author;
    this.createdAt = createdAt;
}


private String title;
private String content;
@ManyToOne
@JoinColumn(name = "user_id",nullable = false)
private User author;

private LocalDateTime createdAt;

public Article(String title, String content, User author) {
    this.title = title;
    this.content = content;
    this.author = author;
}

public Article() {
    super();
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getContent() {
    return content;
}

public void setContent(String content) {
    this.content = content;
}

public User getAuthor() {
    return author;
}

public void setAuthor(User author) {
    this.author = author;
}

public LocalDateTime getCreatedAt() {
    return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

}
