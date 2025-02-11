package com.bart.zamazon.controller;

import com.bart.zamazon.daos.UserDao;
import com.bart.zamazon.entitys.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userDao.findById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> getUserByEmail(@RequestParam String query){
        return  ResponseEntity.ok(Collections.singletonList(userDao.findByEmail(query)));
    }


}
