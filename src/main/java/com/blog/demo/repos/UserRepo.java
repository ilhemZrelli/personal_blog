package com.blog.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.demo.model.User;

public interface UserRepo extends JpaRepository<User,Long>{
 User findByUsername(String username);
 boolean existsByUsername(String username);
}
