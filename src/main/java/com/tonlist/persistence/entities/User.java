package com.tonlist.persistence.entities;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private String resetpasswordtoken;
    private Date resetpasswordexpires;    
    private String name;
    private String email;
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    
    public String getResetpasswordtoken() {
		return resetpasswordtoken;
	}

	public void setResetpasswordtoken(String resetpasswordtoken) {
		this.resetpasswordtoken = resetpasswordtoken;
	}

	public Date getResetpasswordexpires() {
		return resetpasswordexpires;
	}

	public void setResetpasswordexpires(Date resetpasswordexpires) {
		this.resetpasswordexpires = resetpasswordexpires;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
