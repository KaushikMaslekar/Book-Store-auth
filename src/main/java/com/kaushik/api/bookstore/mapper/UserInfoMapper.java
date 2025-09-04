package com.kaushik.api.bookstore.mapper;

import com.kaushik.api.bookstore.dto.UserInfoDto;
import com.kaushik.api.bookstore.entity.UserInfo;

public class UserInfoMapper {

    public static UserInfoDto toDto(UserInfo userInfo) {
        return new UserInfoDto(userInfo.getUserName(), userInfo.getPassword(),
                userInfo.getRoles());
    }

    public static UserInfo toEntity(UserInfoDto userInfoDto) {
        return new UserInfo(userInfoDto.userName(), userInfoDto.password(),
                userInfoDto.roles());
    }
}
