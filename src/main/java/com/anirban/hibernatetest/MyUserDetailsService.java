package com.anirban.hibernatetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepo.findByName(username);
        if(user.isPresent()){
            UserDetails userDetails = User.builder()
                    .username(user.get().getName())
                    .password(user.get().getPassword())
                    .roles(user.get().getRole())
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
        return null;
    }
}
