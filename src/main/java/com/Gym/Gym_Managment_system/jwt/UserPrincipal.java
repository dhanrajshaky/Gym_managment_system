package com.Gym.Gym_Managment_system.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.List;

public class UserPrincipal extends User {

    private String username;

    public UserPrincipal(String username , List<GrantedAuthority> authorityList) {
        super(username,"" ,authorityList);//use authorities dynamically
    }

}
