package com.qr.code.generator.web.app.service;

import com.qr.code.generator.web.app.domain.Role;
import com.qr.code.generator.web.app.domain.UserAdmin;
import com.qr.code.generator.web.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl {
/*
    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //logger.info("El usuario es: "+username);
        UserAdmin user  = userRepository.findUserAdminByUsername(username);

        if(user == null){
            logger.error("Error: Usuario no encontrado");
            throw  new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role: user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        if(authorities.isEmpty()){
            logger.error("Usuario no posee roles!");
        }


        return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }*/
}