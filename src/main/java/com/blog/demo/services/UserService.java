package com.blog.demo.services;



import java.util.List;

import com.blog.demo.model.Role;
import com.blog.demo.model.User;

public interface UserService {
    public User registerUser(User user);
    public User findByUserName(String username);
    Role addRole(Role role);
   User addRoleToUser(String username, String rolename);
   public boolean existsUserName(String username);
   List<User> findAllUsers();


}
