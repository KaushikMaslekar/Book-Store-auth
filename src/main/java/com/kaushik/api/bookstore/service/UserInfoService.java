package com.kaushik.api.bookstore.service;

import com.kaushik.api.bookstore.dto.UserInfoDto;

public interface UserInfoService {

    public UserInfoDto createUser(UserInfoDto userInfoDto);

    public String getUserInfo(UserInfoDto userInfoDto);
}
