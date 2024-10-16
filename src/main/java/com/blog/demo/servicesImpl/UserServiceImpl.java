package com.blog.demo.servicesImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.demo.model.Role;
import com.blog.demo.model.User;
import com.blog.demo.repos.RoleRepo;
import com.blog.demo.repos.UserRepo;
import com.blog.demo.services.UserService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRep;
    @Autowired
    RoleRepo roleRep;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRep.findByUsername(username);
        Role r = roleRep.findByName(rolename);
        usr.getRoles().add(r);
        return usr;
    }

    @Override
    public Role addRole(Role role) {
        return roleRep.save(role);
    }

    @Override
    public User findByUserName(String username) {
        return userRep.findByUsername(username);
    }
    public boolean existsUserName(String username){
        return userRep.existsByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRep.findAll();
    }

}
