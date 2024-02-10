package com.attraya.userservice.controller;

import com.attraya.userservice.entity.User;
import com.attraya.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private int port;


    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        logger.info("UserController.getUser user ID : {}",userId);
        System.out.println("request is landed on port : " + port);
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}/{amount}")
    public User updateUserBalance(@PathVariable int userId, @PathVariable double amount) {
        logger.info("UserController.updateUserBalance user ID : {}, amount : {}",userId, amount);
        return userService.updateAccountStatus(userId, amount);
    }
}