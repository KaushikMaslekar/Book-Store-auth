package com.kaushik.api.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaushik.api.bookstore.dto.UserInfoDto;
import com.kaushik.api.bookstore.entity.UserInfo;
import com.kaushik.api.bookstore.mapper.UserInfoMapper;
import com.kaushik.api.bookstore.repository.UserInfoRepository;
import com.kaushik.api.bookstore.service.JWTService;
import com.kaushik.api.bookstore.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public JWTService jwtService;

    @Override
    public UserInfoDto createUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = UserInfoMapper.toEntity(userInfoDto);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        // Set default role to ADMIN if no role is provided
        if (userInfo.getRoles() == null || userInfo.getRoles().isEmpty()) {
            userInfo.setRoles("ADMIN");
        }
        userInfoRepository.save(userInfo);
        return UserInfoMapper.toDto(userInfo);
    }

    @Override
    public String getUserInfo(UserInfoDto userInfoDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userInfoDto.userName(), userInfoDto.password()));
        System.out.println("Auth Object = " + authentication.isAuthenticated());
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userInfoDto.userName());
        }
        return "Failure";
    }
}
