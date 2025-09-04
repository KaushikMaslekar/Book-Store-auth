package com.kaushik.api.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaushik.api.bookstore.dto.UserInfoDto;
import com.kaushik.api.bookstore.service.UserInfoService;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/register")
    public ResponseEntity<String> createUserInfo(@RequestBody UserInfoDto userInfoDto) {
        UserInfoDto userInfoDto1 = userInfoService.createUser(userInfoDto);
        System.out.println("User " + userInfoDto1.userName() + " is created");
        return new ResponseEntity<>("User " + userInfoDto1.userName()
                + " is created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getUserInfo(@RequestBody UserInfoDto userInfoDto) {
        return new ResponseEntity<>(userInfoService.getUserInfo(userInfoDto), HttpStatus.OK);
    }
}
