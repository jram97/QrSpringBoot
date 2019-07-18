package com.qr.code.generator.web.app.repository;

import com.qr.code.generator.web.app.domain.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAdmin, Long> {

    UserAdmin findUserAdminByUsername(String username);

    UserAdmin findUserAdminByUsernameAndPassword(String username, String password);

}
