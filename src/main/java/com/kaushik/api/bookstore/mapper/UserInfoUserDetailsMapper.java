package com.kaushik.api.bookstore.mapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kaushik.api.bookstore.entity.UserInfo;

public class UserInfoUserDetailsMapper implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;

    public UserInfoUserDetailsMapper(UserInfo userInfo) {
        userName = userInfo.getUserName();
        password = userInfo.getPassword();
        grantedAuthorities = Arrays.stream(userInfo.getRoles().split(",")) // Split roles by comma
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Add ROLE_ prefix
                .collect(Collectors.toList()); // Collect the authorities into a list
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
