package com.qr.code.generator.web.app.service;

import com.qr.code.generator.web.app.domain.UserAdmin;
import com.qr.code.generator.web.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAdminImpl implements IUserAdminService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAdmin findUserAdminByUsernameAndPassword(String username, String password) {
        return userRepository.findUserAdminByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    public UserAdmin findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
