package com.qr.code.generator.web.app.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    private String rolename;

    @OneToMany(fetch = FetchType.LAZY)
    private List<UserAdmin> user;


    public List<UserAdmin> getUser() {
		return user;
	}

	public void setUser(List<UserAdmin> user) {
		this.user = user;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    private static final long serialVersionUID = 1L;
}