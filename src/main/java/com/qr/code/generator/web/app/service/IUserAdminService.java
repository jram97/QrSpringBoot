package com.qr.code.generator.web.app.service;

import com.qr.code.generator.web.app.domain.UserAdmin;

public interface IUserAdminService {

    UserAdmin findUserAdminByUsernameAndPassword(String username, String password);

    UserAdmin findOne(Long id);

}
